<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户信息</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<link rel="stylesheet" type="text/css" href="common/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="common/bootstrap/css/bootstrap.css" media="all">
	<link rel="stylesheet" type="text/css" href="common/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="css/personal.css" media="all">
	<link rel="stylesheet" type="text/css" href="../css/page.css">
	
	<script type="text/javascript" src="<%=path %>/layer/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="<%=path %>/layer/layer.js"></script>
	
</head>
<body>
<section class="layui-larry-box">
	<div class="larry-personal">
	    <div class="layui-tab">
            <blockquote class="layui-elem-quote news_search">
		
		<div class="layui-inline">
		    <div class="layui-input-inline" >
		      <form action="<%=path %>/admin/memberList.do" method="post" id="searchform" name="searchform">
		      <div style="display: flex;">
		        <div>
		    	<input value="${key}" placeholder="用户名或姓名" name="key" class="layui-input search_input" type="text">
		    	</div>
		    	</div>
		    <!-- <input type="submit" class="layui-btn" value="查询"> -->	
		      </form>
		    </div>
		    <a class="layui-btn"  href="javascript:void(0)" onclick="searchnew()">查询</a>
		</div>
	</blockquote>
            
		         <!-- 操作日志 -->
                <div class="layui-form news_list" >
                    <table class="layui-table" style="font-size:12px;">
					<thead>
						<tr>
							<th>用户名</th>
							<th>密码</th>
							<th>姓名</th>
							<th>电话</th>
							<th>邮箱</th>
							<th>帐号状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody class="news_content">
					   <c:forEach items="${pageInfo.list}" var="member">
						<tr>
							<td>${member.uname}</td>
							<td>******</td>
							<td>${member.tname}</td>
							<td>${member.tel}</td>
							<td>${member.email}</td>
							<td>${member.isjy}</td>
							<td>
							<c:choose>
							  <c:when test="${member.isjy eq '正常'}">
							  <a class="layui-btn layui-btn-mini" href="updateIsjy.do?id=${member.id}">封号</a>
							  </c:when>
							  <c:otherwise>
							  <a class="layui-btn layui-btn-mini" href="updateIsjy.do?id=${member.id}">解禁</a>
							  </c:otherwise>
							</c:choose>
							<a class="layui-btn layui-btn-danger layui-btn-mini" href="memberDel.do?id=${member.id}" ><i class="layui-icon"></i> 删除</a>
							</td>
						</tr>
						</c:forEach>
						<tr>
                    <td align="center"  colspan="9">
                    <c:if test="${fn:length(pageInfo.list) gt 0}">
                           <div id="pagediv" style="border:0px solid red;">
				              <ul>
				                  <li><a href="memberList.do?pageNum=${pageInfo.hasPreviousPage==false?1:pageInfo.prePage}&key=${key}&utype=${utype}">上一页</a></li>
				                  <c:forEach begin="1" end="${pageInfo.pages}" varStatus="status">
				                      <li class="pagesz ${status.count eq pageInfo.pageNum ?"acvtive":""}" ><a href="memberList.do?pageNum=${status.count}&key=${key}&utype=${utype}" >${status.count}</a></li>
				                  </c:forEach>
				                  <li><a href="memberList.do?pageNum=${pageInfo.hasNextPage==false? pageInfo.pages : pageInfo.nextPage}&key=${key}&utype=${utype}">下一页</a></li>
				              </ul>
				          </div>
                   </c:if>
                    </td>
                  </tr>
					</tbody>
					</table>
                     <div class="larry-table-page clearfix">
			         </div>
			    </div>
			     <!-- 登录日志 -->
		    </div>
		</div>
	
</section>
<script type="text/javascript" src="common/layui/layui.js"></script>
<script type="text/javascript" src="js/newslist.js"></script>
<script type="text/javascript">
	layui.use(['jquery','layer','element','laypage'],function(){
	      window.jQuery = window.$ = layui.jquery;
	      window.layer = layui.layer;
          var element = layui.element(),
              laypage = layui.laypage;

            
          laypage({
					cont: 'page',
					pages: 10 //总页数
						,
					groups: 5 //连续显示分页数
						,
					jump: function(obj, first) {
						//得到了当前页，用于向服务端请求对应数据
						var curr = obj.curr;
						if(!first) {
							//layer.msg('第 '+ obj.curr +' 页');
						}
					}
				});

          laypage({
					cont: 'page2',
					pages: 10 //总页数
						,
					groups: 5 //连续显示分页数
						,
					jump: function(obj, first) {
						//得到了当前页，用于向服务端请求对应数据
						var curr = obj.curr;
						if(!first) {
							//layer.msg('第 '+ obj.curr +' 页');
						}
					}
				});
    });


	function deleteAll(){

    	layer.confirm('是否确认删除？', {
      	  btn: ['是','否'] //按钮
      	}, function(){

      	  var len = $("input[name='checked']:checked").length;	
      	  if(len!=0){
      	  var checkedbox = $("input[name='checked']:checked");
      	  var arr = new Array();
            $(checkedbox).each(function(i){
                arr[i] = $(this).val();
            });
            var vals = arr.join(",");
            $.ajax({
      		type:"post",
      		url:"memberDelAll.do?vals="+vals,
      		date:"",
      		success:function(msg){
      			location.replace("memberList");
      		  }
            })
      	  }else{
				layer.msg("请选择要删除的项");
          	  }
            
            
      	}, function(){
      	});
	  //var $checkbox = $("input[name='checked']");
	  //var len = $("input[name='checked']:checked").length;
      
    }

    function searchnew(){
      //${"#searchform"}.submit();
      searchform.submit()
      
    }

    function importuser(){
		location.href="memberimport.jsp";
	}

    function exportB(key,utype){
		location.href="exportB.do?key="+key+"&utype="+utype;
	}
</script>
<c:if test="${message ne null and message ne ''}">
<script>
layer.msg('${message}');
</script>
</c:if>
</body>
</html>