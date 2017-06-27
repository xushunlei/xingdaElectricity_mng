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
<script type="text/javascript" src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
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
        	<li class="current"><a href="javascript:void(0)" onclick="modify_price(this)" class="inbox">修改电价</a></li>
            <li><a href="javascript:void(0)" onclick="add_point(this)" class="drafts">新增网点</a></li>
            <li><a href="javascript:void(0)" onclick="add_meter(this)" class="drafts">新增用户</a></li>
            <li><a href="javascript:void(0)" onclick="send_msg(this)" class="drafts">推送消息</a></li>
        </ul>
        <a class="togglemenu"></a>
        <br /><br />
    </div><!--leftmenu-->
    
    <div class="centercontent">
    
        <div class="pageheader">
            <h1 class="pagetitle" id="pagename">管理页面</h1>
            <span class="pagedesc">The content below are loaded using ajax</span>
            
            <ul class="hornav">
                <li class="current"><a href="#inbox">修改电价</a></li>
                <li><a href="#compose">新建网点</a></li>
                <li><a href="#newmetchdiv">新建用户</a></li>
                <li><a href="#senddiv">推送短信</a></li>
            </ul>
        </div><!--pageheader-->
        
        <div id="contentwrapper" class="contentwrapper">
             <div id="inbox" class="subcontent">
             	<div>
             	<form id="price_form">
             		<table>
             			<tr><td>当前电价：</td><td><span id="show_price">${currPrice.price }</span>元/度</td><td>*自<span id="show_start_date" >${currPrice.startDateStr }</span>起计费</td></tr>
             			<tr><td>最近修改：</td><td><span id="recent_change_date">${futurePrice.startDateStr }</span>起，电价改为：<span id="recent_change_price">${futurePrice.price }</span>元/度</td></tr>
             			<tr><td>修改电价：</td><td><input style="width:40px;padding:0" name="new_price">元/度</td></tr>
             			<tr><td>启用时间：</td><td><input id="usedate" class="Wdate" name="up_date"></td></tr>
             			<tr><td>短信通知</td><td><label><input type="radio" name="sendable" value="1">是</label><label><input type="radio" name="sendable" value="0" checked="checked">否</label></td></tr>
             			<tr class="sendcells"><td>发送时间：</td><td><input id="senddate" class="Wdate" name="send_date"></td></tr>
             			<tr class="sendcells"><td>短信内容：</td><td colspan="2"><textarea rows="6" cols="" name="send_content" style="resize:none"></textarea></td></tr>
             			<tr><td></td><td><input type="button" value="确认" onclick="modifyPrice()"></td></tr>
             		</table>
             	</form>
             	</div>
             </div>
             <div id="compose" class="subcontent" style="display: none">&nbsp;
             	<center>
             	<form>
             		<table>
             		<tr><td>名称：</td><td><input ></td></tr>
             		<tr><td>编号：</td><td><input ></td></tr>
             		<tr><td>管理员：</td><td><select>
             			<option value="1">管理员</option>
             			<option value="2">超级管理员</option>
             		</select></td></tr>
             		<tr><td>地址：</td><td><input ></td></tr>
             		<tr><td>IP：</td><td><input ></td></tr>
             		<tr><td>端口：</td><td><input ></td></tr>
             		<tr><td><input type="reset">重置</td><td><input type="submit">提交</td></tr>
             		</table>
             	</form>
             	</center>
             </div>
             <div id="newmetchdiv" class="subcontent" style="display: none">&nbsp;
             	<div id="tree_machine">
             		<div id="seachdiv">
             			
             		</div>
             		<div id="userlist">
             			
             		</div>
             		<div id="groupname">
             			
             		</div>
             	</div>
             	<input type="button" onclick="showTree();">
             </div>
             <div id="senddiv" class="subcontent" style="display: none">&nbsp;
             	
             </div>
        </div><!--contentwrapper-->
    
    </div><!--centercontent-->
    
    
</div><!--bodywrapper-->

</body>
</html>