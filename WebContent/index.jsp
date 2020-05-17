<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>“恋爱学堂”网络平台</title>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<link rel="stylesheet" href="css/menu.css" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- Custom Theme files -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="My Show Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--webfont-->
	<!-- start menu -->
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
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
				});
			});
		</script>
<!---- start-smoth-scrolling---->
 <style >
         #myTab{
         list-style: none;
         }
         
         #myTab li{
         border:0px solid red;
         height: 40px;
         border-bottom: 1px dashed #EEEEEE;
         width: 98%;
         line-height: 40px;
         overflow: hidden;/*超出部分隐藏*/
    	 text-overflow:ellipsis;/* 超出部分显示省略号 */
    	 white-space: nowrap;/*规定段落中的文本不进行换行 */
    	 font-size:14px;
         }
         
         </style>
</head>
<body>
	<!-- header-section-starts -->
		<jsp:include page="top.jsp"></jsp:include>
		
		<div class="container">
		  <div class="main-content">
			<jsp:include page="head.jsp"></jsp:include>
			<!-- end container -->
    

<!-- AddThis Smart Layers END -->


	<div class="main-banner" >
		 <div class="banner col-md-8" style="width: 70%">
			<section class="slider" >
				<div class="flexslider">
						<ul class="slides">
						<c:forEach items="${imglist}" var="imgadv">
						<li>
							<img src="upload/${imgadv.filename}"  style="border:0px solid red;width: 100%;height: 360px;" class="img-responsive" alt="" />
						</li>
				  		</c:forEach>
				       </ul>
				</div>
					</section>
				 <!-- FlexSlider -->
				 <script defer src="js/jquery.flexslider.js"></script>
				 <link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
					<script type="text/javascript">
				$(function(){
				 SyntaxHighlighter.all();
				});
				$(window).load(function(){
				  $('.flexslider').flexslider({
					animation: "slide",
					start: function(slider){
					  $('body').removeClass('loading');
					}
				 });
				});
			 </script>
         </div>
         <!-- right -->
         <div class="col-md-4 banner-right" style="border:1px solid #EEEEEE;width: 24.33333333%;padding:0px;border-radius:10px 10px;">
			<h5 style="background: #003B64;margin: 0px;width: 100%;border:0px solid black;padding-left:8px;border-radius:10px 10px 0px 0px;height: 40px;line-height: 40px;color:white;">最新恋爱知识</h5>
			<div class="grid_3 grid_5">
			<div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs" style="padding-left:8px;">
			  <ul id="myTab">
			  <c:forEach items="${aclist}" var="activity">
			  <li ><a href="activityx.do?id=${activity.id}" class="activitytitle">${activity.title }</a></li>
			  </c:forEach>
			</ul>
		   </div>
		  </div>
		 </div>
		 
		 <!-- right -->
		 <div class="clearfix"></div>
	</div>
		<div class="footer-top-grid" style="border:0px solid red;width: 100%">
		<div class="list-of-movies col-md-8" style="border:0px solid red;width: 98%">
				<div class="clearfix"></div>
				<div class="featured">
					<h4>推荐课程</h4>
					<ul>
					<c:forEach items="${kclist}" var="kechen" begin="0" end="5">
						<li style="width: 24%">
							<div class="f-movie">
							
								<div class="f-movie-img">
									<a href="kechenDetails.do?id=${kechen.id}"><img src="upload/${kechen.filename}" alt="" style="width: 268px;height: 278px;"></a>
								</div>
									<div class="f-movie-name">
										<a href="kechenDetails.do?id=${kechen.id}">${kechen.kcname}</a>
									</div>								 
							</div>
						</li>
					</c:forEach>
					</ul>
				</div>
			</div>
			<div class="clearfix"></div>			
			</div>
		</div>
			<div class="clearfix"></div>
		</div>
			<div class="copy-rights text-center">
			<p> “恋爱学堂”网络平台 - Collect from <a href="<%=path %>/admin/login.jsp" title="后台管理" target="_blank">后台管理</a></p>
			</div>
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
</html>