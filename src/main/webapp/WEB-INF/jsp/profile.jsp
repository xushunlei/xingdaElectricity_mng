<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>资料展示页面</title>
<% String basePath = request.getScheme()+"://"+
		request.getServerName()+":"+
		request.getServerPort()+request.getContextPath()+"/";%>
<base href="<%=basePath%>">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/style.default.css" type="text/css" />
<script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="js/plugins/jquery.bxSlider.min.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript" src="js/custom/profile.js"></script>
</head>
<body class="withvernav">
<div class="bodywrapper">
<%@include file="header.jsp" %>
<div class="vernav2 iconmenu">
<ul>
   	<li><a href="#formsub" class="editor">Forms</a>
       	<span class="arrow"></span>
       	<ul id="formsub">
          		<li><a href="forms.html">Basic Form</a></li>
               <li><a href="wizard.html">Wizard</a></li>
               <li><a href="editor.html">WYSIWYG</a></li>
           </ul>
       </li>
       <!--<li><a href="filemanager.html" class="gallery">File Manager</a></li>-->
         <li><a href="elements.html" class="elements">Elements</a></li>
         <li><a href="widgets.html" class="widgets">Widgets</a></li>
         <li><a href="calendar.html" class="calendar">Calendar</a></li>
         <li><a href="support.html" class="support">Customer Support</a></li>
         <li><a href="typography.html" class="typo">Typography</a></li>
         <li><a href="tables.html" class="tables">Tables</a></li>
<li><a href="buttons.html" class="buttons">Buttons &amp; Icons</a></li>
         <li><a href="#error" class="error">Error Pages</a>
         	<span class="arrow"></span>
         	<ul id="error">
            		<li><a href="notfound.html">Page Not Found</a></li>
                 <li><a href="forbidden.html">Forbidden Page</a></li>
                 <li><a href="internal.html">Internal Server Error</a></li>
                 <li><a href="offline.html">Offline</a></li>
             </ul>
         </li>
         <li class="current"><a href="#addons" class="addons">Addons</a>
         	<span class="arrow"></span>
         	<ul id="addons">
            		<li><a href="newsfeed.html">News Feed</a></li>
                 <li class="current"><a href="profile.html">Profile Page</a></li>
                 <li><a href="productlist.html">Product List</a></li>
                 <li><a href="photo.html">Photo/Video Sharing</a></li>
             </ul>
         </li>
     </ul>
     <a class="togglemenu"></a>
     <br /><br />
 </div><!--leftmenu-->
    
<div class="centercontent">

    <div class="pageheader">
    	<span class="profilepic"><img src="images/thumbs/avatarbig.png" alt="" /></span>
        <div class="profiletitle">
        <h1 class="pagetitle">${loginUser.name}</h1>
        <span class="pagedesc">当前余额：${loginUser.balance}</span>
        </div>
        <ul class="hornav">
            <li class="current"><a href="#profile">Profile</a></li>
            <li><a href="#editprofile">编辑个人信息</a></li>
        </ul>
    </div><!--pageheader-->
   
   <div id="contentwrapper" class="contentwrapper">
   
   	<div class="two_third last profile_wrapper">
           <div id="profile" class="subcontent">
               <button id="followbtn" class="stdbtn btn_yellow followbtn">Follow</button>
               <ul class="profile_summary">
                   <li><a href="followers.html"><span>15</span> Followers</a></li>
                   <li><a href="" id="following"><span>20</span> Following</a></li>
                   <li><a href="blog.html"><span>2</span> Blog</a></li>
                   <li><a href=""><span>8</span> Project Shots</a></li>
               </ul>
               
               
               <blockquote class="bq2 currentstatus marginbottom0">
                   <a class="edit_status" title="Edit Status"></a>
                   This is an example of my current status. When clicking Follow button above, watch how the number of Following change. This is ajax implementation ready, just read the documentation on how. :)
               </blockquote>
               
               
               <div class="contenttitle2">
                   <h3>About Juan</h3>
               </div><!--contenttitle-->
               
               <div class="profile_about">
                   <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi auctor, sem sit amet eleifend feugiat, nulla mauris molestie lectus, id rutrum sem nunc ac dolor. Cras condimentum tincidunt semper. Proin a justo vitae massa adipiscing convallis eu in nunc. Nam fringilla ante cursus enim gravida sodales. Nulla sit amet felis erat. Praesent vel augue ac lacus lobortis pulvinar. Praesent elit quam, porta id sagittis sit amet, pretium a augue.</p>
                   <p>Aliquam molestie rutrum tincidunt. Nullam non augue a libero interdum hendrerit. Sed vestibulum mi quis odio vestibulum dapibus. Curabitur porttitor, libero eget lobortis ultrices, elit sem dignissim enim, in vestibulum magna tortor vel dui. </p> 
               </div>
               
               
               <div class="contenttitle2">
                   <h3>Recent Blog</h3>
               </div><!--contenttitle-->
               
               <div class="recentblog">
                   <div class="blogthumb">
                       <a href="blogview.html"><img src="images/preview/blog1.png" alt="" /></a>
                   </div><!--blogthumb-->
                   <div class="blogsummary">
                       <h3><a href="blogview.html">Some Tutorials (an in-house blog)</a></h3>
                       <small>June 10, 2012 3:30pm &nbsp;/&nbsp; 0 Comment</small>
                       <p>This is where you can discuss or give some tips/tutorials as a guide for your fellow teammates. Vivamus vitae lacus dui, in vestibulum augue. Vestibulum ante ipsum primis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eget nibh urna. Vivamus vitae lacus dui.</p>
                       <p><a href="blogview.html" class="orangeboldlink">Read More &raquo;</a></p>
                   </div><!--blogsummary-->
               </div><!--recentblog-->
               
               <br clear="all" />
               
               <div class="contenttitle2">
                   <h3>Project Shots</h3>
               </div><!--contenttitle-->
               
               <ul class="recentshots">
                   <li>
                       <a href="" class="th"><img src="images/preview/portfolio1.png" alt="" /></a>
                       <h4><a href="">Admin Template</a></h4>
                       <small>2 Comments</small>
                   </li>
                   <li>
                       <a href="" class="th"><img src="images/preview/portfolio2.png" alt="" /></a>
                       <h4><a href="">File Manager</a></h4>
                       <small>0 Comment</small>
                   </li>
                </ul>

               
               <br clear="all" />
               
           </div><!--#profile-->
           
           <div id="editprofile" class="subcontent" style="display: none">
               Edit profile form goes here...
               <form action="">
               	<span>电话：</span><input>
               	<span>邮箱：</span><input>
               	<span>住址：</span><input>
               </form>
           </div><!--#editprofile-->
           
           <br /><br />
       </div><!--two_third-->
       
       <br /><br />
       
   </div><!--contentwrapper-->
</div>
</div>
</body>
</html>