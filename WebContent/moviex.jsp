<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<style type="text/css">
input[type="range"] {
  display: none;
  width: 100%;
}
.buyticket{
list-style: none;

}
.buyticket li{
font-weight: bold;
border:0px solid red;
height: 50px;
line-height: 50px;
border-bottom: 1px dashed gray;
}
</style>
<script src="layer/jquery-2.0.3.min.js"></script>
<script src="layer/layer.js"></script>
</head>
<body>
<input type="hidden" id="sessionmember" value="${sessionScope.sessionmember.id}"/>
<input type="hidden" id="utype" value="${member.utype}"/>
	<!-- header-section-starts -->
		
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
					<img src="upload/${movie.filename}" alt="" />
					<div class="bahubali-details">
					   <ul style="list-style: none;font-size:12px;">
					     <li style="margin-top: 20px;"><font style="font-weight: bold;">视频名:</font>&nbsp;&nbsp;${movie.name }</li>
					     <li style="margin-top: 10px;"><font style="font-weight: bold;">资源下载:</font>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="xz('${movie.videoname}')" style="color:#FECE00">下载</a></li>
					     <li style="margin-top: 10px;"><font style="font-weight: bold;">视频介绍:</font>&nbsp;&nbsp;<p>${movie.content}</p></li>
					   </ul>
					</div>
				</div>
			</div>
		<div class="col-md-8 movies-dates">	
		
		
		
			<div >
			<h4 style="border-bottom: 1px solid #EEEEEE;padding-bottom: 10px;margin-bottom: 30px;">在线观看</h4>
			<div style="width: 100%;border:0px solid red;" id="videodiv" >
			<video   id="myVideo"     controls="controls"   width="100%"     poster='预览图'   preload="auto"    >
                   <source src="upload/${movie.videoname}" type="video/mp4">
     		</video>
			</div>
			
			
			</div>
		</div>
		<div class="clearfix"></div>
		
		
		<div class="vochers" style="border:1px solid #EEEEEE;margin-top:30px;">
		<div style="background: #003B64;height:35px;color:white;font-size:20px;font-weight: bold;line-height: 35px;padding-left: 20px;letter-spacing: 5px;margin-bottom: 50px;">评论</div>
		<div class="m-vocher-text" style="width: 100%;margin-bottom: 20px;">
		  <!-- s -->
		  
		  <div class="tab-pane" id="source">
		  <div class="response" >
		  <c:forEach items="${pllist}" var="pinlun">
						<div class="media response-info">
							<div class="media-left response-text-left">
								<a href="#">
									<img class="media-object" src="<%=path %>/upload/${pinlun.member.filename}" alt="" style="width: 64px;height:64px;border-radius:32px 32px;">
								</a>
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
			<input type="hidden" name="kcid" value="${movie.id}"/>
			<input type="hidden" name="flag" value="影视"/>
			<span></span>
			<span style="color:red;">请在下方填写您评论</span>
			<div style="margin-top: 20px;">
			<textarea name="content"  style="border:1px solid #EEEEEE" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入内容...';}" required></textarea>
			<input type="submit" value="提交">
			</div>
			</form>
		</div>
      
      	
	</div>
		
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

<script type="text/javascript">
function tofav(vid){
	location.replace("favAdd.do?vid="+vid);
}

<%
String suc = (String)request.getAttribute("suc");
if(suc!=null){
%>
layer.msg('<%=suc%>');
<%}%>

function xz(filename){
	var sessionmember = $("#sessionmember").val();
	if(sessionmember!=''){
    	 location.replace("upload?filename="+filename);
	}else{
		location.replace("login.jsp");
	}
}
</script>
<script type="text/javascript" src="<%=path %>/js/hivideo.js"></script>
</html>