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
import com.bean.Member;
import com.bean.Sysuser;
import com.dao.ActivityDAO;

import java.util.*;

@Controller
public class ActivityController{
	@Resource
	ActivityDAO activityDAO;
	
	
	
	//后台新闻列表
	@RequestMapping("admin/activityList")
	public String userList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String key = request.getParameter("key");
		HashMap map = new HashMap();
		map.put("key", key);
		PageHelper.startPage(pageNum,10);
		List<Activity> list = activityDAO.selectAll(map);
		for(Activity activity:list){
			HashMap mmm = new HashMap();
			mmm.put("activityid", activity.getId());
		}
	   	PageInfo<Activity> pageInfo = new PageInfo<Activity>(list);
	   	request.setAttribute("key", key);
		request.setAttribute("pageInfo", pageInfo);
		return "admin/activitylist";
	}
	
	//前台新闻列表
		@RequestMapping("activityLb")
		public String activityLb(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
			String key = request.getParameter("key");
			HashMap map = new HashMap();
			map.put("flag", "恋爱知识");
			map.put("key", key);
			PageHelper.startPage(pageNum,10);
			List<Activity> list = activityDAO.selectAll(map);
			for(Activity activity:list){
				String content = Info.delHTMLTag(activity.getContent());
				activity.setContent(content);
			}
		   	PageInfo<Activity> pageInfo = new PageInfo<Activity>(list);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("key", key);
			return "activitylb";
		}
		
		
		//前台新闻列表
		@RequestMapping("storyLb")
		public String storyLb(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
			HashMap map = new HashMap();
			map.put("flag", "恋爱故事");
			PageHelper.startPage(pageNum,10);
			List<Activity> list = activityDAO.selectAll(map);
			for(Activity activity:list){
				String content = Info.delHTMLTag(activity.getContent());
				activity.setContent(content);
			}
		   	PageInfo<Activity> pageInfo = new PageInfo<Activity>(list);
			request.setAttribute("pageInfo", pageInfo);
			return "storylb";
		}
	
	
	//新闻添加
	@RequestMapping("admin/activityAdd")
	public String teahcerAdd(Activity activity,HttpServletRequest request) {
		activity.setSavetime(Info.getDateStr());
		activityDAO.add(activity);
		return "redirect:activityList.do";
	}
	
	
	//编辑页面
	@RequestMapping("admin/activityShow")
	public String activityShow(int id,HttpServletRequest request) {
		Activity activity = activityDAO.findById(id);
		request.setAttribute("activity", activity);
		return "admin/activityedit";
	}
	
	
	//新闻编辑
	@RequestMapping("admin/activityEdit")
	public String activityEdit(Activity activity,HttpServletRequest request) {
		activityDAO.update(activity);
		return "redirect:activityList.do";
	}
	
	//新闻删除
	@RequestMapping("admin/activityDel")
	public String activityDel(int id,HttpServletRequest request) {
		activityDAO.delete(id);
		return "redirect:activityList.do";
	}
	
	//恋爱知识详情页面
	@RequestMapping("activityx")
	public String activityx(int id,HttpServletRequest request) {
		Activity activity = activityDAO.findById(id);
		request.setAttribute("activity", activity);
		return "activity";
	}
	
	
	//恋爱知识详情页面
   @RequestMapping("storyx")
	public String storyx(int id,HttpServletRequest request) {
		Activity activity = activityDAO.findById(id);
		request.setAttribute("story", activity);
		return "story";
	}
	
}
