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
import com.dao.KechenDAO;
import com.dao.MemberDAO;
import com.dao.MovieDAO;
import com.dao.PinlunDAO;
import com.bean.Kechen;
import com.bean.Member;
import com.bean.Movie;
import com.bean.Pinlun;
import com.bean.Sysuser;

import java.util.*;

@Controller
public class PinlunController{
	@Resource
	MemberDAO memberDAO;
	@Resource
	PinlunDAO pinlunDAO;
	@Resource
	KechenDAO kechenDAO;
	@Resource
	MovieDAO movieDAO;
	
	
	//评论添加
	@RequestMapping("pinlunAdd")
	public String pinlunAdd(Pinlun pinlun, HttpServletRequest request,RedirectAttributes redirectAttributes){
		Member member  = (Member)request.getSession().getAttribute("sessionmember");
		HashMap map = new HashMap();
		map.put("stid", member.getId());
		map.put("kcid", pinlun.getKcid());
		map.put("flag", pinlun.getFlag());
		List<Pinlun> list = pinlunDAO.selectAll(map);
		if(list.size()==0){
		Kechen kechen = kechenDAO.findById(Integer.parseInt(pinlun.getKcid()));
		pinlun.setStid(String.valueOf(member.getId()));
		pinlun.setSavetime(Info.getDateStr());
		pinlunDAO.add(pinlun);
		redirectAttributes.addFlashAttribute("msg","评论成功");
		}else{
			redirectAttributes.addFlashAttribute("msg","不能重复评论");
		}
		if(pinlun.getFlag().equals("课程")){
		return "redirect:kechenDetails.do?id="+pinlun.getKcid();	
		}else{
			return "redirect:movieDetails.do?id="+pinlun.getKcid();
		}
	}
	
	
	
	//后台评论列表
	@RequestMapping("admin/pinlunList")
	public String studentList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		Sysuser teacher = (Sysuser)request.getSession().getAttribute("admin");
		String key = request.getParameter("key");
		PageHelper.startPage(pageNum,10);
		HashMap map = new HashMap();
		map.put("key", key);
		map.put("tid", teacher.getId());
		List<Pinlun> list = pinlunDAO.selectAll(map);
		for(Pinlun pinlun:list){
			Member member = memberDAO.findById(Integer.parseInt(pinlun.getStid()));
			if(pinlun.getFlag().equals("课程")){
			Kechen kechen = kechenDAO.findById(Integer.parseInt(pinlun.getKcid()));
			pinlun.setKechen(kechen);
			}else{
				Movie movie = movieDAO.findById(Integer.parseInt(pinlun.getKcid()));
				pinlun.setMovie(movie);
			}
			pinlun.setMember(member);
		}
	   	PageInfo<Pinlun> pageInfo = new PageInfo<Pinlun>(list);
		request.setAttribute("key", key);
		request.setAttribute("pageInfo", pageInfo);
		return "admin/pinlunlist";
	}
	
	
	
	//评论删除
	@RequestMapping("admin/pinlunDel")
	public String pinlunDel(int id,HttpServletRequest request){
			pinlunDAO.delete(id);
		return "redirect:pinlunList.do";
	}
	
	//评论回复页面
	@RequestMapping("admin/pinlunShow")
	public String pinlunShow(int id,HttpServletRequest request){
		Pinlun pinlun = pinlunDAO.findById(id);
		request.setAttribute("pinlun", pinlun);
		return "admin/pinlunhf";
	}
	
	//评论回复
	@RequestMapping("admin/pinlunEdit")
	public String pinlunEdit(Pinlun pinlun,HttpServletRequest request){
		pinlunDAO.update(pinlun);
		return "redirect:pinlunList.do";
	}
	
	
	
	

}
