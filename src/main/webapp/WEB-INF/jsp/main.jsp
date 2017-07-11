<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>控制台页面</title>
<!-- 项目路径 -->
<% String basePath = request.getScheme()+"://"+
		request.getServerName()+":"+
		request.getServerPort()+request.getContextPath()+"/";%>
<!-- 基链接标记:它只能应用于标记head内。 
你网页上的所有相对路径在链接时都将在前面加上基链接指向的地址。 -->
<base href="<%=basePath%>">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.default.css" type="text/css" />

<script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script>

<script type="text/javascript" src="js/plugins/jquery.flot.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.flot.resize.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.slimscroll.js"></script>
<script type="text/javascript" src="js/custom/main.js"></script>
</head>
<body class="withvernav">
<div class="bodywrapper">
    <%@include file="header.jsp" %>
    
    <div class="vernav2 iconmenu">
    	<ul>
    		<li><a href="#usersub" class="msg">查看信息</a>
            	<span class="arrow"></span>
            	<ul id="usersub">
               		<c:forEach items="${userList}" var="user">
               		<li><a href="javascript:showOneBranch('${user.userAccount}');">${user.userName}</a></li>
               		</c:forEach>
                </ul>
            </li>
            <li><a href="user/userlistView" class="userlist">查看用户列表</a></li>
            
        	<li><a href="#formsub" class="editor">表单提交</a>
            	<span class="arrow"></span>
            	<ul id="formsub">
               		<li><a href="forms.html">基础表单</a></li>
                    <li><a href="wizard.html">表单验证</a></li>
                    <li><a href="editor.html">编辑器</a></li>
                </ul>
            </li>
            <!--<li><a href="filemanager.html" class="gallery">文件管理</a></li>-->
            <li><a href="elements.html" class="elements">网页元素</a></li>
            <li><a href="widgets.html" class="widgets">网页插件</a></li>
            <li><a href="calendar.html" class="calendar">日历事件</a></li>
            <li><a href="support.html" class="support">客户支持</a></li>
            <li><a href="typography.html" class="typo">文字排版</a></li>
            <li><a href="user/tablesView" class="tables">数据表格</a></li>
			<li><a href="buttons.html" class="buttons">按钮 &amp; 图标</a></li>
            <li><a href="#error" class="error">错误页面</a>
            	<span class="arrow"></span>
            	<ul id="error">
               		<li><a href="notfound.html">404错误页面</a></li>
                    <li><a href="forbidden.html">403错误页面</a></li>
                    <li><a href="internal.html">500错误页面</a></li>
                    <li><a href="offline.html">503错误页面</a></li>
                </ul>
            </li>
            <li><a href="#addons" class="addons">其他页面</a>
            	<span class="arrow"></span>
            	<ul id="addons">
               		<li><a href="newsfeed.html">新闻订阅</a></li>
                    <li><a href="user/profileView">资料页面</a></li>
                    <li><a href="productlist.html">产品列表</a></li>
                    <li><a href="photo.html">图片视频分享</a></li>
                    <li><a href="gallery.html">相册</a></li>
                    <li><a href="invoice.html">购物车</a></li>
                </ul>
            </li>
        </ul>
        <a class="togglemenu"></a>
        <br /><br />
    </div><!--leftmenu-->
        
    <div class="centercontent">
    
        <div class="pageheader">
            <h1 class="pagetitle">控制台</h1>
            <span class="pagedesc">页面的描述内容</span>
        </div><!--pageheader-->
        
        <div id="contentwrapper" class="contentwrapper">
        
        	<div id="updates" class="subcontent">
                    <div class="notibar announcement">
                        <a class="close"></a>
                        <h3>Announcement</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                    </div><!-- notification announcement -->
                    
                    <div class="two_third dashboard_left">
                    
                    	<ul class="shortcuts">
                            <li><a href="" class="users"><span>房间管理</span></a></li>
                            <li><a href="" class="recharge"><span>充值</span></a></li>
                            <li><a href="" class="rechargerecord"><span>充值查询</span></a></li>
                            <li><a href="" class="settings"><span>公寓配置</span></a></li>
                            <li><a href="" class="site"><span>房间配置</span></a></li>
                            <li><a href="" class="analytics"><span>房间日用电概况</span></a></li>
                            <li><a href="" class="events"><span>账单查询</span></a></li>
                        </ul>
                    </div><!--two_third dashboard_left -->
             </div><!-- subcontent -->
        </div><!--contentwrapper-->
        
        <br clear="all" />
        
	</div><!-- centercontent -->
    
    
</div><!--bodywrapper-->

</body>
</html>