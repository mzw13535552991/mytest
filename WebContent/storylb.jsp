<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
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
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--webfont-->
<!-- start menu -->
<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
<link rel="stylesheet" href="css/font-awesome.min.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" type="text/css" href="css/page.css">
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
</head>
<body>
	<!-- header-section-starts -->
		
		<jsp:include page="top.jsp"></jsp:include>
		<div class="container">
		  <div class="main-content">
		  <jsp:include page="head.jsp"></jsp:include>
			<!-- end container -->
    
<!-- AddThis Smart Layers END -->
	<ol class="breadcrumb">
			  <li><a href="index">首页</a></li>
			  <li class="active">恋爱故事</li>
			</ol>
	<!----->
	<div class="support-center">
			<!-- s -->
			<style>
			.pcontent{
			overflow: hidden;/*超出部分隐藏*/
    	 	text-overflow:ellipsis;/* 超出部分显示省略号 */
    	 	white-space: nowrap;/*规定段落中的文本不进行换行 */
			}
			</style>
			
			<div class="grid_3 grid_4 wow fadeInLeft animated" >
		    <div class="bs-example">
		       <c:forEach items="${pageInfo.list}" var="story">
				<div class="mb-60" style="margin-top: 20px;margin-bottom: 50px;border:0px solid red;">
				<h4 style="margin-bottom: 20px;border:0px solid blue;"><a href="storyx.do?id=${story.id}" style="color:#1A4078;font-weight: bold;">${story.title }</a></h4>
				<p class="pcontent" style="border-bottom:1px solid #F1F1F1;font-size:12px;color:gray;padding-bottom: 20px;">${story.content}</p>
				</div>
			  </c:forEach>
			</div>
			
			<div id="pagediv" style="border:0px solid red;">
	              <ul>
	                  <li><a href="storyLb.do?pageNum=${pageInfo.hasPreviousPage==false?1:pageInfo.prePage}">上一页</a></li>
	                  <c:forEach begin="1" end="${pageInfo.pages}" varStatus="status">
	                      <li class="pagesz ${status.count eq pageInfo.pageNum ?"acvtive":""}" ><a href="storyLb.do?pageNum=${status.count}" >${status.count}</a></li>
	                  </c:forEach>
	                  <li><a href="storyLb.do?pageNum=${pageInfo.hasNextPage==false? pageInfo.pages : pageInfo.nextPage}">下一页</a></li>
	              </ul>
	          </div>  
	    	</div>
			
			<!-- e -->
	</div>
	</div>
			<div class="clearfix"></div>
		</div>
			<div class="copy-rights text-center">
			<p> “恋爱学堂”网络平台 - Collect from <a href="<%=path %>/admin/login.jsp" title="后台管理" target="_blank">后台管理</a></p>
			</div>
	</div>
 <script src="js/responsive-tabs.js"></script>
    <script type="text/javascript">
      $( '#myTab a' ).click( function ( e ) {
        e.preventDefault();
        $( this ).tab( 'show' );
      } );

      $( '#moreTabs a' ).click( function ( e ) {
        e.preventDefault();
        $( this ).tab( 'show' );
      } );

      ( function( $ ) {
          // Test for making sure event are maintained
          $( '.js-alert-test' ).click( function () {
            alert( 'Button Clicked: Event was maintained' );
          } );
          fakewaffle.responsiveTabs( [ 'xs', 'sm' ] );
      } )( jQuery );

    </script>
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