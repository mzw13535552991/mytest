package com.controller;

import org.springframework.stereotype.Component;

import com.bean.Category;
import com.bean.Member;

import com.dao.CategoryDAO;
import com.dao.MemberDAO;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Component
public class Saveobject {
	@Resource 
	CategoryDAO categoryDAO;
	
	
	
     public void getCategoryObject(HttpServletRequest request){
    	 List<Category> ctlist = categoryDAO.selectAll(null);
    	 request.setAttribute("ctlist", ctlist);
     }
     
	
}
