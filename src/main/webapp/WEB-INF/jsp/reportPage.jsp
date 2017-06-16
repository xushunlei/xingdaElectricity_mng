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
<script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="js/plugins/jquery.alerts.js"></script>
<script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript" src="js/custom/report.js"></script>
<style type="text/css">
</style>
</head>
<body class="withvernav">
<div class="bodywrapper">
    <%@include file="header.jsp" %>
    <div class="vernav iconmenu">
    	<ul id="leftmenu">
        	<li class="current"><a href="javascript:void(0)" onclick="" class="inbox">统计网点</a></li>
            <li><a href="javascript:void(0)" onclick="" class="drafts">统计电表</a></li>
        </ul>
        <a class="togglemenu"></a>
        <br /><br />
    </div><!--leftmenu-->
    
    <div class="centercontent">
    
        <div class="pageheader">
            <h1 class="pagetitle" id="pagename">统计页面</h1>
            <span class="pagedesc">The content below are loaded using ajax</span>
            
            <ul class="hornav">
                <li class="current"><a href="#inbox">用户列表信息</a></li>
                <li><a href="#compose">网点列表信息</a></li>
            </ul>
        </div><!--pageheader-->
        
        <div id="contentwrapper" class="contentwrapper">
             
             <div id="inbox" class="subcontent">
             
                <div class="msghead">
                    <ul class="msghead_menu">
                        <li><a class="reportspam" onclick="resetPwd();">重置密码</a></li>
                        <li class="marginleft5 dropdown" id="actions">
                            <a class="dropdown_label" href="#actions">
                           	 批量操作
                            <span class="arrow"></span>
                            </a>
                            <ul>
                                <li><a id="do_enable" href="javascript:void(0);">启用</a></li>
                                <li><a id="do_disable" href="javascript:void(0);">停用</a></li>
                                <li><a href="javascript:void(0);">注销</a></li>
                            </ul>
                        </li>
                        <li class="marginleft5"><a class="msgtrash" title="Trash"></a></li>
                    	<li class="right"><a class="next" href="javascript:next_page()" id="down_page"></a></li>
                        <li class="right"><a class="prev prev_disabled" id="up_page" href="javascript:prev_page()"></a></li>
                        <li class="right"><span class="pageinfo" id="page_info">1-10 of <b id="b1">${all_meter_count}</b></span></li>
                        <li class="right">
			            <div class="search">
			                	<input type="text" name="keyword" id="keyword" value="请输入关键字" onkeydown="keyDown();"/>
			                    <!-- <button class="submitbutton" onclick="drowTable(1,10)"></button> -->
			                    <input type="button" onclick="drowTable(1,10);" class="submitbutton" id="seach_btn">
			            </div><!--search-->
			            </li>
                    </ul>
                    <span class="clearall"></span>
                </div><!--msghead-->
             </div>
             <div id="compose" class="subcontent" style="display: none">&nbsp;
             	
             </div>
        </div><!--contentwrapper-->
    
    </div><!--centercontent-->
    
    
</div><!--bodywrapper-->

</body>
</html>