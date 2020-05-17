<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>评价管理</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<link rel="stylesheet" type="text/css" href="common/layui/css/layui.css" notice="all">
	<link rel="stylesheet" type="text/css" href="common/bootstrap/css/bootstrap.css" notice="all">
	<link rel="stylesheet" type="text/css" href="common/global.css" notice="all">
	<link rel="stylesheet" type="text/css" href="css/personal.css" notice="all">
	<link rel="stylesheet" type="text/css" href="../css/page.css">
</head>
<body>
<section class="layui-larry-box">
	<div class="larry-personal">
	    <div class="layui-tab">
            <blockquote class="layui-elem-quote news_search">
		
		<div class="layui-inline">
		    <div class="layui-input-inline">
		      <form action="<%=path %>/admin/pinlunList.do" method="post" id="searchform" name="searchform">
		    	<input value="${key}" placeholder="请输入评价人" name="key" class="layui-input search_input" type="text">
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
						    <th>标题</th>
							<th>评价人</th>
							<th>评价时间</th>
							<th>评价内容</th>
							<th>评价回复</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody class="news_content">
					   <c:forEach items="${pageInfo.list}" var="pinlun">
						<tr>
						<c:choose>
						  <c:when test="${pinlun.flag eq '影视' }">
						  <td>${pinlun.movie.name}</td>
						  </c:when>
						  <c:otherwise>
						  <td>${pinlun.kechen.kcname}</td>
						  </c:otherwise>
						</c:choose>
							<td>${pinlun.member.uname}</td>
							<td>${pinlun.savetime}</td>
							<td>${pinlun.content}</td>
							<td>${pinlun.hfcontent}</td>
							<td>
							<c:if test="${pinlun.hfcontent eq null}">
							<a class="layui-btn layui-btn-mini" href="pinlunShow.do?id=${pinlun.id}"><i class="iconfont icon-edit"></i>回复</a>
							</c:if>
							   <a class="layui-btn layui-btn-danger layui-btn-mini" href="pinlunDel.do?id=${pinlun.id}" ><i class="layui-icon"></i> 删除</a>
							</td>
						</tr>
						</c:forEach>
						
						<tr>
                    <td align="center"  colspan="13">
                        <div id="pagediv" style="border:0px solid red;">
			              <ul>
			                  <li><a href="pinlunList.do?pageNum=${pageInfo.hasPreviousPage==false?1:pageInfo.prePage}&key=${key}">上一页</a></li>
			                  <c:forEach begin="1" end="${pageInfo.pages}" varStatus="status">
			                      <li class="pagesz ${status.count eq pageInfo.pageNum ?"acvtive":""}" ><a href="pinlunList.do?pageNum=${status.count}&key=${key}" >${status.count}</a></li>
			                  </c:forEach>
			                  <li><a href="pinlunList.do?pageNum=${pageInfo.hasNextPage==false? pageInfo.pages : pageInfo.nextPage}&key=${key}">下一页</a></li>
			              </ul>
			          </div>   
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

    function searchnew(){
      //${"#searchform"}.submit();
      searchform.submit()
      
    }

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
      		url:"pinlunDelAll.do?vals="+vals,
      		date:"",
      		success:function(msg){
      			location.replace("pinlunList");
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


    function show(title,id,type){
        var acontent;
        var content = $("#content"+id).val();
        var note = $("#note"+id).val();
        if(type=="jj"){
        	acontent=content;
        }
        if(type=="bz"){
        	acontent=note;
        }
    	layer.open({
    		  title: title,
    		  area: ['500px', '300px'],
    		  offset: 'rb',
    		  content: acontent
       }); 
    }
</script>
</body>
</html>