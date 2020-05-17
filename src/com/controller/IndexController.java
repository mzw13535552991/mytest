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


import com.dao.ActivityDAO;
import com.dao.ImgadvDAO;
import com.dao.KechenDAO;
import com.dao.SysuserDAO;
import com.bean.Activity;
import com.bean.Imgadv;
import com.bean.Kechen;
import com.bean.Sysuser;

import java.util.*;

@Controller
public class IndexController{
	@Resource
	ImgadvDAO imgadvDAO;
	@Resource
	SysuserDAO sysuserDAO;
	@Resource
	ActivityDAO activityDAO;
	@Resource
	KechenDAO kechenDAO;
	
	
	
	
	
	//首页
	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		List<Imgadv> imglist = imgadvDAO.selectAll();
		HashMap map = new HashMap();
		map.put("flag", "恋爱知识");
		List<Activity> aclist = activityDAO.selectAll(map);
		map.put("istj", "yes");
		List<Kechen> kclist = kechenDAO.selectAll(map);
		request.setAttribute("kclist", kclist);
		request.setAttribute("aclist", aclist);
		request.setAttribute("imglist", imglist);
		return "index";
	}
	
	

}
