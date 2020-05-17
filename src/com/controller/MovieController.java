package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import com.bean.Category;
import com.bean.Movie;
import com.bean.Member;
import com.bean.Pinlun;


import com.dao.CategoryDAO;
import com.dao.MovieDAO;
import com.dao.MemberDAO;
import com.dao.PinlunDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.Info;

import java.util.*;

@Controller
public class MovieController extends BaseController {
	@Resource
	MovieDAO movieDAO;
	@Resource
	CategoryDAO categoryDAO;
	@Resource
	PinlunDAO pinlunDAO;
	@Resource
	MemberDAO memberDAO;
	@Resource
	Saveobject saveobject;
	
	
	
	//视频详情
	@RequestMapping("movieDetails")
	public String movieDetails(int id,HttpServletRequest request){
		Member sessionmember = (Member)request.getSession().getAttribute("sessionmember");
		String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
		Movie movie = movieDAO.findById(id);
		request.setAttribute("movie", movie);
		
		HashMap ppp = new HashMap();
		ppp.put("kcid", id);
		List<Pinlun> pllist = pinlunDAO.selectAll(ppp);
		for(Pinlun pinlun:pllist){
			Member mmm = memberDAO.findById(Integer.parseInt(pinlun.getStid()));
			pinlun.setMember(mmm);
		}
		request.setAttribute("pllist", pllist);
		return "moviex";
	}
	
	
	//查找类别
	@RequestMapping("admin/skipMovieAdd")
	public String skipMovieAdd(HttpServletRequest request) {
		String type = request.getParameter("type");
		HashMap map = new HashMap();
		List<Category> list = categoryDAO.selectAll(map);
		request.setAttribute("list", list);
		if(type.equals("add")){
		return "admin/movieadd";
		}else{
		String id = request.getParameter("id");
		Movie movie = movieDAO.findById(Integer.parseInt(id));
		request.setAttribute("movie", movie);
		return "admin/movieedit";	
		}
	}
	
	//后台视频列表
	@RequestMapping("admin/movieList")
	public String movieList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		PageHelper.startPage(pageNum,10);
		String key = request.getParameter("key");
		HashMap map = new HashMap();
		map.put("key", key);
		PageHelper.startPage(pageNum,10);
		List<Movie> list = movieDAO.selectAll(map);
		PageInfo<Movie> pageInfo = new PageInfo<Movie>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("key", key);
		return "admin/movielist";
	}
	
	//前台视频列表
	@RequestMapping("movieMsg")
	public String movieMsg(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String categoryid = request.getParameter("categoryid");
		String key = request.getParameter("key");
		HashMap map = new HashMap();
		map.put("categoryid", categoryid);
		map.put("key", key);
		PageHelper.startPage(pageNum,10);
		List<Movie> list = movieDAO.selectAll(map);
		PageInfo<Movie> pageInfo = new PageInfo<Movie>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("categoryid", categoryid);
		request.setAttribute("key", key);
		return "moviemsg";
	}
	
	@RequestMapping("admin/movieAdd")
	public String movieAdd(Movie movie,HttpServletRequest request){
		movieDAO.add(movie);
		return "redirect:movieList.do";
	}
	
	
	@RequestMapping("ckmovie")
	public String ckmovie(int id,HttpServletRequest request) {
		Movie movie = movieDAO.findById(id);
		request.setAttribute("movie", movie);
	    return "movieedit";		
	}
	
	@RequestMapping("admin/movieEdit")
	public String movieEdit(Movie movie,HttpServletRequest request) {
		movieDAO.update(movie);
	    return "redirect:movieList.do";		
	}
	
	
	@RequestMapping("admin/movieDel")
	public String movieDel(int id,HttpServletRequest request) {
		movieDAO.delete(id);
	    return "redirect:movieList.do";		
	}
	
	@RequestMapping("admin/movieShow")
	public String moviedetailsShow(int id,HttpServletRequest request) {
		Movie movie = movieDAO.findById(id);
		request.setAttribute("movie", movie);
	    return "admin/moviedetails";		
	}
	
	
}
