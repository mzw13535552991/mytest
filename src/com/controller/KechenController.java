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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.Info;
import com.bean.Category;
import com.bean.Kechen;
import com.bean.Member;
import com.bean.Pinlun;
import com.bean.Sysuser;
import com.dao.CategoryDAO;
import com.dao.KechenDAO;
import com.dao.MemberDAO;
import com.dao.PinlunDAO;

import java.util.*;

@Controller
public class KechenController{
	@Resource
	KechenDAO kechenDAO;
	@Resource
	CategoryDAO categoryDAO;
	@Resource
	Saveobject saveobject;
	@Resource
	MemberDAO memberDAO;
	@Resource
	PinlunDAO pinlunDAO;
	
	
	
	//课程详情
	@RequestMapping("kechenDetails")
	public String kechenDetails(int id,HttpServletRequest request) {
		Kechen kechen= kechenDAO.findById(id);
		Category category = categoryDAO.findById(Integer.parseInt(kechen.getCategoryid()));
		kechen.setCategory(category);
		request.setAttribute("kechen", kechen);
		
		HashMap map = new HashMap();
		map.put("kcid", id);
		map.put("px", "px");
		
		
		HashMap ppp = new HashMap();
		ppp.put("kcid", id);
		List<Pinlun> pllist = pinlunDAO.selectAll(ppp);
		for(Pinlun pinlun:pllist){
			Member mmm = memberDAO.findById(Integer.parseInt(pinlun.getStid()));
			pinlun.setMember(mmm);
		}
		request.setAttribute("pllist", pllist);
		return "kechendetails";
	}
	
	
	//后台课程列表
	@RequestMapping("admin/kechenList")
	public String userList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String key = request.getParameter("key");
		String categoryid = request.getParameter("categoryid");
		HashMap map = new HashMap();
		map.put("key", key);
		map.put("categoryid", categoryid);
		PageHelper.startPage(pageNum,10);
		List<Kechen> list = kechenDAO.selectAll(map);
		for(Kechen kechen:list){
			Category category = categoryDAO.findById(Integer.parseInt(kechen.getCategoryid()));
			kechen.setCategory(category);
		}
	   	PageInfo<Kechen> pageInfo = new PageInfo<Kechen>(list);
	   	request.setAttribute("key", key);
	   	request.setAttribute("categoryid", categoryid);
		request.setAttribute("pageInfo", pageInfo);
		saveobject.getCategoryObject(request);
		return "admin/kechenlist";
	}
	
	//前台课程列表
		@RequestMapping("kechenLb")
		public String kechenLb(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
			HashMap map = new HashMap();
			PageHelper.startPage(pageNum,10);
			List<Kechen> list = kechenDAO.selectAll(map);
			for(Kechen kechen:list){
				Category category = categoryDAO.findById(Integer.parseInt(kechen.getCategoryid()));
				kechen.setCategory(category);
			}
		   	PageInfo<Kechen> pageInfo = new PageInfo<Kechen>(list);
			request.setAttribute("pageInfo", pageInfo);
			return "kechenlb";
		}
		
		
		//恋爱课程
		@RequestMapping("kechenMsg")
		public String kechenMsg(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
			String categoryid = request.getParameter("categoryid");
			String key = request.getParameter("key");
			HashMap map = new HashMap();
			map.put("categoryid", categoryid);
			map.put("key", key);
			PageHelper.startPage(pageNum,10);
			List<Kechen> list = kechenDAO.selectAll(map);
			for(Kechen kechen:list){
				Category category = categoryDAO.findById(Integer.parseInt(kechen.getCategoryid()));
				kechen.setCategory(category);
			}
		   	PageInfo<Kechen> pageInfo = new PageInfo<Kechen>(list);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("categoryid", categoryid);
			request.setAttribute("key", key);
			saveobject.getCategoryObject(request);
			return "kechenmsg";
		}
	
	//课程添加
	@RequestMapping("admin/kechenAdd")
	public String kechenAdd(Kechen kechen,HttpServletRequest request) {
		kechen.setIstj("no");
		kechenDAO.add(kechen);
		return "redirect:kechenList.do";
	}
	
	
	//编辑页面
	@RequestMapping("admin/kechenShow")
	public String kechenShow(int id,HttpServletRequest request) {
		Kechen kechen = kechenDAO.findById(id);
		request.setAttribute("kechen", kechen);
		return "admin/kechenedit";
	}
	
	
	//课程编辑
	@RequestMapping("admin/kechenEdit")
	public String kechenEdit(Kechen kechen,HttpServletRequest request) {
		kechenDAO.update(kechen);
		return "redirect:kechenList.do";
	}
	
	//课程删除
	@RequestMapping("admin/kechenDel")
	public String kechenDel(int id,HttpServletRequest request) {
		Kechen kechen = kechenDAO.findById(id);
		kechen.setDelstatus("1");
		kechenDAO.update(kechen);
		return "redirect:kechenList.do";
	}
	
	//跳转页面
	@RequestMapping("admin/skipKechen")
	public String skipKechen(HttpServletRequest request) {
		String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
		List<Category> ctlist = categoryDAO.selectAll(null);
		request.setAttribute("ctlist", ctlist);
		if(flag.equals("kechenadd")){
			return "admin/kechenadd";
		}else{
			String id = request.getParameter("id");
			Kechen kechen = kechenDAO.findById(Integer.parseInt(id));
			request.setAttribute("kechen", kechen);
			return "admin/kechenedit";
		}
	}
	
	//推荐
	@RequestMapping("admin/updateIstj")
	public String updateIstj(int id,HttpServletRequest request) {
		Kechen kechen = kechenDAO.findById(id);
		if(kechen.getIstj().equals("no")){
		kechen.setIstj("yes");
		}else{
			kechen.setIstj("no");
		}
		kechenDAO.update(kechen);
		return "redirect:kechenList.do";
	}
	
	

}
