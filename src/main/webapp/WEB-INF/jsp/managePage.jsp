<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看消息页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<% String basePath = request.getScheme()+"://"+
		request.getServerName()+":"+
		request.getServerPort()+request.getContextPath()+"/";%>
<base href="<%=basePath%>">
<!-- 修改浏览器图标 -->
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/style.default.css" type="text/css" />
<link rel="stylesheet" href="My97DatePicker/skin/WdatePicker.css" type="text/css" />
<script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="js/plugins/jquery.alerts.js"></script>
<script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript" src="js/custom/manage.js"></script>
<style type="text/css">

.sendcells{
	display:none;
}
</style>
</head>
<body class="withvernav">
<div class="bodywrapper">
    <%@include file="header.jsp" %>
    <div class="vernav iconmenu">
    	<ul id="leftmenu">
            <li class="current"><a href="javascript:void(0)" onclick="mng_user(this)" class="inbox">管理用户</a></li>
        	<li><a href="javascript:void(0)" onclick="modify_price(this)" class="drafts">修改电价</a></li>
            <c:if test="${userRole==1}">
            <li><a href="javascript:void(0)" onclick="add_point(this)" class="drafts">新增网点</a></li>
            </c:if>
            <li><a href="javascript:void(0)" onclick="add_meter(this)" class="drafts">新增用户</a></li>
            <li><a href="javascript:void(0)" onclick="send_msg(this)" class="drafts">推送消息</a></li>
        </ul>
        <a class="togglemenu"></a>
        <br /><br />
    </div><!--leftmenu-->
    
    <div class="centercontent">
    
        <div class="pageheader">
            <h1 class="pagetitle" id="pagename">管理页面</h1>
            <span class="pagedesc" id="pagedesc">The content below are loaded using ajax</span>
        </div><!--pageheader-->
        
        <div id="contentwrapper" class="contentwrapper"></div><!--contentwrapper-->
    
    </div><!--centercontent-->
    
    
</div><!--bodywrapper-->

</body>
</html>