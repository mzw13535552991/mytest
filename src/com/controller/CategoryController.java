package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.Info;
import com.bean.Activity;
import com.bean.Category;
import com.bean.Member;
import com.bean.Sysuser;
import com.dao.ActivityDAO;
import com.dao.CategoryDAO;

import java.util.*;

@Controller
public class CategoryController{
	@Resource
	CategoryDAO categoryDAO;
	
	
	
	//后台分类列表
	@RequestMapping("admin/categoryList")
	public String userList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String key = request.getParameter("key");
		HashMap map = new HashMap();
		map.put("key", key);
		PageHelper.startPage(pageNum,10);
		List<Category> list = categoryDAO.selectAll(map);
	   	PageInfo<Category> pageInfo = new PageInfo<Category>(list);
	   	request.setAttribute("key", key);
		request.setAttribute("pageInfo", pageInfo);
		return "admin/categorylist";
	}
	
	
	
	//分类添加
	@RequestMapping("admin/categoryAdd")
	public String teahcerAdd(Category category,HttpServletRequest request) {
		categoryDAO.add(category);
		return "redirect:categoryList.do";
	}
	
	
	//编辑页面
	@RequestMapping("admin/categoryShow")
	public String categoryShow(int id,HttpServletRequest request) {
		Category category = categoryDAO.findById(id);
		request.setAttribute("category", category);
		return "admin/categoryedit";
	}
	
	
	//分类编辑
	@RequestMapping("admin/categoryEdit")
	public String categoryEdit(Category category,HttpServletRequest request) {
		categoryDAO.update(category);
		return "redirect:categoryList.do";
	}
	
	//分类删除
	@RequestMapping("admin/categoryDel")
	public String categoryDel(int id,HttpServletRequest request) {
		Category category = categoryDAO.findById(id);
		category.setDelstatus("1");
		categoryDAO.update(category);
		return "redirect:categoryList.do";
	}
	
	

}
