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
<%-- <c:set var="loginUser" value="${loginUser }" scope="application"/> --%>
<style type="text/css">
.mybtn{
	background-color: orange;
    color: white;
    border: aliceblue;
    width: 48px;
    height: 32px;
}
</style>
<script type="text/javascript">
jQuery(document).ready(function($){
	$("#userid").val('${loginUser.userId}');
	$("#useraccount").val('${loginUser.userAccount}');
	$("#username").val("${loginUser.userName}");
	$("#usermobile").val("${loginUser.userMobile}");
	$("#useridcard").val("${loginUser.userEmail}");
	$("#useraddress").val("${loginUser.userAddress}");
});
function aaa(){
	alert(1);
	jQuery("#username").val('');
}
</script>
<script type="text/javascript" charset="utf-8" src="js/custom/userinfo.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script><!--  -->
</head>
<body class="withvernav">
<div class="bodywrapper">
    <%@include file="header.jsp" %>
    <div class="vernav iconmenu">
    	<ul>
        	<li class="current"><a href="admin/manageView" class="inbox">查看用户列表</a></li>
            <li><a href="admin/branchView" class="drafts">查看网点列表</a></li>
        </ul>
        <a class="togglemenu"></a>
        <br /><br />
    </div><!--leftmenu-->
    
    <div class="centercontent">
    
        <div class="pageheader">
            <h1 class="pagetitle">用户资料</h1>
            <span class="pagedesc">The content below are loaded using ajax</span>
            
            <ul class="hornav">
                <li class="current"><a href="#inbox">基本资料</a></li>
                <li><a href="#compose">更改密码</a></li>
            </ul>
        </div><!--pageheader-->
        <div id="contentwrapper" class="contentwrapper">
             
             <div id="inbox" class="subcontent">
             <form id="modify_info">
             	<table>
                	<tr><td>账户名：</td><td><input id="useraccount" name="account" readonly style="background-color:#eee"></td></tr>
                	<tr><td>姓名：</td><td><input id="username" name="name"></td></tr>
                	<tr><td>电话：</td><td><input id="usermobile" name="mobile"></td></tr>
                	<tr><td>身份证：</td><td><input id="useridcard" name="idcard"></td></tr>
                	<tr><td>邮箱：</td><td><input id="useremail" name="email"></td></tr>
                	<tr><td>住址：</td><td><input id="useraddress" name="address"></td></tr>
                	<tr><td><input type="hidden" name="id" id="userid"></td></tr>
                	<tr><td><input value="提交" type="button" onclick="saveuser()" class="mybtn"></td><td><input type="button" value="重置" onclick="resetform()" class="mybtn"></td></tr>
                </table>
                </form>             
             </div>
             <div id="compose" class="subcontent" style="display: none">&nbsp;
             	<table>
             		<tr><td>登陆账户:</td><td><span id="account">${loginUser.userAccount }</span></td></tr>
             		<tr><td>旧密码:</td><td><input id="old_pwd" type="password"><span id="oldtips"></span></td></tr>
             		<tr><td>新密码:</td><td><input id="new_pwd" type="password"><span id="newtips"></span></td></tr>
             		<tr><td>确认新密码:</td><td><input id="verify_pwd" type="password"><span id="verifytips"></span></td></tr>
             		<tr>
             			<td><input class="mybtn" value="提交" onclick="change_password()" type="button"></td>
             			<td><input class="mybtn" value="重置" onclick="clean_newpwd()" type="button"></td>
             		</tr>
             	</table>
             </div>
        </div><!--contentwrapper-->
    
    </div><!--centercontent-->
    
    
</div><!--bodywrapper-->

</body>
</html>