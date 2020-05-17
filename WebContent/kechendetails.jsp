<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.util.Info"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>“恋爱学堂”网络平台</title>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- Custom Theme files -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="My Show Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<!--webfont-->
<!-- start menu -->
<link rel="stylesheet" href="<%=path %>/assets/hivideo.css" />

<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
<link rel="stylesheet" href="css/font-awesome.min.css" />
<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
		    <script type="text/javascript">
			    $(document).ready(function () {
			        $('#horizontalTab').easyResponsiveTabs({
			            type: 'default', //Types: default, vertical, accordion           
			            width: 'auto', //auto or any width like 600px
			            fit: true   // 100% fit in a container
			        });
			    });
</script>
<link rel="stylesheet" href="css/menu.css" />
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<!-- checkbox -->

<!-- checkbox -->
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
				});
			});
		</script>
<!---- start-smoth-scrolling---->


<script src="layer/jquery-2.0.3.min.js"></script>
<script src="layer/layer.js"></script>

</head>
<body>
	<!-- header-section-starts -->
<input type="hidden" id="hiddenmember" value="${sessionScope.sessionmember}"/>		
<jsp:include page="top.jsp"></jsp:include>
		<div class="container">
		  <div class="main-content">
		  <!-- head -->
<jsp:include page="head.jsp"></jsp:include>    
<!-- AddThis Smart Layers END -->
	<div class="now-showing-list">
		<div class="col-md-4 movies-by-category movie-booking">
			<div class="movie-ticket-book">
					<div class="clearfix"></div>
					<img src="upload/${kechen.filename}" alt="" />
					<div class="bahubali-details">
					   <ul style="list-style: none;font-size:12px;">
					     <li style="margin-top: 20px;"><font style="font-weight: bold;">课程名称:</font>&nbsp;&nbsp;${kechen.kcname}</li>
					     <li style="margin-top: 10px;"><font style="font-weight: bold;">课程分类:</font>&nbsp;&nbsp;${kechen.category.name}</li>
					   </ul>
					</div>
				</div>
			</div>
		<div class="col-md-8 movies-dates">	
		
		
		<!-- 视频 -->
		<div class=newsxitme ><video controls="controls"   width="100%" poster='预览图'   preload="auto" >
    	<source src="upload/${kechen.vfilename }" type="video/mp4"></video></div>
			<!-- 视频 -->
			
			
			
			
			
			<div style="border:0px solid red;width: 100%;margin-top: 30px;">
			<ul style="display: flex;list-style: none;flex-direction:column;">
			</ul>
			</div>
		</div>
		<div class="clearfix"></div>
		
		
		<div class="vochers" style="border:1px solid #EEEEEE;margin-top:30px;padding: 0px;">
		<div style="background: #003B64;color:white;font-size:18px;line-height: 35px;border:0px solid red;padding-left: 20px;">课程概述</div>
        <p style="padding: 20px;">
        ${kechen.content}
        </p>
	    </div>
	    
	     <!-- 评论 -->
	    <div class="vochers" style="border:0px solid red;margin-top:30px;padding: 0px;">
		<div style="background: #003B64;color:white;font-size:18px;line-height: 35px;border:0px solid red;padding-left: 20px;">评价</div>
		<div class="m-vocher-text" style="width: 100%;margin-bottom: 20px;padding: 10px;">
		  <!-- s -->
		  
		  <div class="tab-pane" id="source">
		  <div class="response" >
		  <c:forEach items="${pllist}" var="pinlun">
						<div class="media response-info">
							<div class="media-left response-text-left">
							    <img src="upload/${pinlun.member.filename}" style="width: 60px;height: 60px;">
								<h5 style="margin-left: 20px;"><a href="#">${pinlun.member.uname}</a></h5>
							</div>
							<div class="media-body response-text-right">
								<p>${pinlun.content}</p>
								<c:if test="${pinlun.hfcontent ne null}">
								<p style="color:red;"><span>回复:</span>&nbsp;&nbsp;${pinlun.hfcontent}</p>
								</c:if>
								<ul>
									<li>${pinlun.savetime}</li>
								</ul>
							</div>
							<div class="clearfix"> </div>
						</div>
			</c:forEach>			
						
						
					</div>
        </div>
      </div>	
      
      
      <div class="feed-back-form" style="margin-top: 320px;padding: 10px;">
			<form action="pinlunAdd.do" method="post">
			<input type="hidden" name="kcid" value="${kechen.id}"/>
			<input type="hidden" name="flag" value="课程"/>
			<span></span>
			<span style="color:red;">请在下方填写您评论</span>
			<div style="margin-top: 20px;">
			<textarea name="content"  style="border:1px solid #EEEEEE" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入内容...';}" required></textarea>
			<input type="submit" value="提交">
			</div>
			</form>
		</div>
      
      	
	</div>
	    <!-- 评论 -->
		
	    </div>
		</div>
		
			<div class="clearfix"></div>
		</div>
			<div class="copy-rights text-center">
			<p> “恋爱学堂”网络平台 - Collect from <a href="<%=path %>/admin/login.jsp" title="后台管理" target="_blank">后台管理</a></p>
			</div>
 <script type="text/javascript">
						$(document).ready(function() {
							/*
							var defaults = {
					  			containerID: 'toTop', // fading element id
								containerHoverID: 'toTopHover', // fading element hover id
								scrollSpeed: 1200,
								easingType: 'linear' 
					 		};
							*/
							
							$().UItoTop({ easingType: 'easeOutQuart' });
							
						});
					</script>
				<a href="#home" class="scroll" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
</body>
<script src="jcount/jquery/jquery-1.8.2.min.js"></script>
<link href="jcount/jcountdown/jcountdown.css" rel="stylesheet" type="text/css">
<script src="jcount/jcountdown/jquery.jcountdown.min.js"></script>
<script type="text/javascript">

<%
String suc = (String)request.getAttribute("suc");
if(suc!=null){
%>
layer.msg('<%=suc%>');
<%}%>

function baoming(kcid){
	location.replace("skipBaoming.do?kcid="+kcid);
}


function shouchang(kcid){
	location.replace("favAdd.do?kechenid="+kcid);
}


function isbaoming(kcid,veid){
	$.ajax({
		type:"post",
		url:"lookvedio.do?kcid="+kcid+"&veid="+veid,
		date:"",
		success:function(res){
			if(res==0){
				layer.msg("请先报名");
			}else if(res==1){
				location.replace("login.jsp");
			}else{
				lookvedio(res);
			}
	    }
	})
}

function lookvedio(filename,videoid){
	var hiddenmember = $("#hiddenmember").val();
	if(hiddenmember==""){
		location.replace('login.jsp');
		return false;
	}
	
	$.ajax({
		type:"post",
		url:"historyAdd.do?videoid="+videoid,
		date:'',
		success:function(msg){
		}
	})
	
	var content = "<div class=\"newsxitme\" >"+
    "<video      controls=\"controls\"   width=\"100%\"        poster='预览图'   preload=\"auto\"    >"+
    "<source src=\"upload/"+filename+"\" type=\"video/mp4\"></video></div>"
	layer.open({
		  title:"在线观看",
		  type: 1,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['420px', '340px'], //宽高
		  content: content
		});
}


</script>
<script type="text/javascript" src="<%=path %>/js/hivideo.js"></script>
<c:if test="${msg ne null and msg ne ''}">
<script>
layer.msg('${msg}');
</script>
</c:if>
</html>