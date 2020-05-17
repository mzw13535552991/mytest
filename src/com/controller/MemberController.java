package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.ExcelUtil;
import com.util.Info;
import com.util.MD5;
import com.dao.MemberDAO;
import com.dao.SysuserDAO;
import com.bean.Member;
import com.bean.Sysuser;

import java.util.*;

@Controller
public class MemberController{
	@Resource
	MemberDAO memberDAO;
	
	
	
	
	//退出
		@RequestMapping("memberexit")
		public String memberexit(HttpServletRequest request) {
			request.getSession().removeAttribute("sessionmember");
			return "redirect:index.do";
		}
	
	//登录
		@RequestMapping("Login")
		public String Login(Member member,HttpServletRequest request) {
			HashMap map = new HashMap();
			map.put("uname", member.getUname());
			map.put("upass", member.getUpass());
			map.put("isjy", "正常");
			List<Member> list = memberDAO.selectAll(map);
			if(list.size()==0){
				request.setAttribute("suc", "用户名或密码错误或封号");
				return "login";
			}else{
				Member mmm = list.get(0);
				request.getSession().setAttribute("sessionmember",mmm);
				return "redirect:index.do";
			}
		}
		
		
		//检查用户名的唯一性
		@RequestMapping("checkUname")
		public void checkUname(String uname, HttpServletRequest request, HttpServletResponse response){
			try {
				PrintWriter out = response.getWriter();
				HashMap map = new HashMap();
				map.put("uname", uname);
				List<Member> list = memberDAO.selectAll(map);
				if(list.size()==0){
					out.print(0);
				}else{
					out.print(1);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	//后台用户列表
	@RequestMapping("admin/memberList")
	public String userList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String key = request.getParameter("key");
		String utype = request.getParameter("utype");
		PageHelper.startPage(pageNum,10);
		HashMap map = new HashMap();
		map.put("key", key);
		map.put("utype", utype);
		List<Member> list = memberDAO.selectAll(map);
	   	PageInfo<Member> pageInfo = new PageInfo<Member>(list);
	   	request.setAttribute("key", key);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("utype", utype);
		return "admin/memberlist";
	}
	
	
	//用户注册
	@RequestMapping("Register")
	public String Register(Member member,HttpServletRequest request) {
		member.setIsjy("正常");
		memberDAO.add(member);
		request.setAttribute("suc", "注册成功");
		return "register";
	}
	
	//添加用户
	@RequestMapping("admin/memberAdd")
	public String memberAdd(Member member,HttpServletRequest request) {
		memberDAO.add(member);
		return "redirect:memberList.do";
	}
	
	//编辑页面
	@RequestMapping("admin/memberCk")
	public String memberCk(int id,HttpServletRequest request) {
		Member member = memberDAO.findById(id);
		request.setAttribute("member", member);
		return "admin/memberedit";
	}
	
	//编辑用户
	@RequestMapping("admin/memberEdit")
	public String memberEdit(Member member,HttpServletRequest request) {
		memberDAO.update(member);
		return "redirect:memberList.do";
	}
	
	//个人信息页面
	@RequestMapping("memberShow")
	public String memberShow(int id,HttpServletRequest request) {
		String suc = request.getParameter("suc")==null?"":request.getParameter("suc");
		Member member = memberDAO.findById(id);
		request.setAttribute("member", member);
		if(!suc.equals("")){
			request.setAttribute("suc", "编辑成功");
		}
		return "memberxg";
	}
	
	
	//个人信息修改
	@RequestMapping("memberxg")
	public String memberxg(Member member,HttpServletRequest request) {
		memberDAO.update(member);
		return "redirect:memberShow.do?id="+member.getId()+"&suc=suc";
	}
	
	//删除用户
	@RequestMapping("admin/memberDel")
	public String memberDel(int id,HttpServletRequest request) {
		Member member = memberDAO.findById(id);
		member.setDelstatus("1");
		memberDAO.update(member);
		return "redirect:memberList.do";
	}
	
	//更新帐号状态
	@RequestMapping("admin/updateIsjy")
	public String updateIsjy(int id,HttpServletRequest request) {
		Member member = memberDAO.findById(id);
		if(member.getIsjy().equals("正常")){
			member.setIsjy("已锁");
		}else{
			member.setIsjy("正常");
		}
		memberDAO.update(member);
		return "redirect:memberList.do";
	}
	
	
	
	
	
	
	

}
