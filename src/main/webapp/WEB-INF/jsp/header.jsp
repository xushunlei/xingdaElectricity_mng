<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<div class="topheader">
        <div class="left">
            <h1 class="logo">Ama.<span>Admin</span></h1>
            <span class="slogan">后台管理系统</span>
            
            
            <br clear="all" />
            
        </div><!--left-->
        
        <div class="right">
        	<!--<div class="notification">
                <a class="count" href="ajax/notifications.html"><span>9</span></a>
        	</div>-->
            <div class="userinfo">
            	<img src="images/thumbs/avatar.png" alt="" />
                <span>${loginUser.userRole==1?"管理员":"用户" }</span>
            </div><!--userinfo-->
            
            <div class="userinfodrop">
            	<div class="avatar">
                	<a href=""><img src="images/thumbs/avatarbig.png" alt="" /></a>
                    <!-- <div class="changetheme">
                    	切换主题: <br />
                    	<a class="default"></a>
                        <a class="blueline"></a>
                        <a class="greenline"></a>
                        <a class="contrast"></a>
                        <a class="custombg"></a>
                    </div> -->
                </div><!--avatar-->
                <div class="userdata">
                	<h4>${loginUser.userName }</h4>
                    <span class="email">${loginUser.userEmail }</span>
                    <ul>
                    	<li><a href="user/editprofile">编辑资料</a></li>
                        <li><a href="#">账号设置</a></li>
                        <li><a href="#">帮助</a></li>
                        <li><a href="user/logout">退出</a></li>
                    </ul>
                </div><!--userdata-->
            </div><!--userinfodrop-->
        </div><!--right-->
    </div><!--topheader-->
    
    
    <div class="header">
    	<ul class="headermenu">
        	<li class="current"><a href="dashboard.html"><span class="icon icon-flatscreen"></span>首页</a></li>
            <li><a href="admin/manageView"><span class="icon icon-pencil"></span>管理</a></li>
            <li><a href="#"><span class="icon icon-message"></span>查看消息</a></li>
            <li><a href="#"><span class="icon icon-chart"></span>统计报表</a></li>
            <li><a href="admin/detailView"><span class="icon icon-clock"></span>实时数据</a></li>
            <li><a href="#"><span class="icon icon-notebook"></span>查看历史</a></li>
        </ul>
        
       <div class="headerwidget">
       <c:if test="${loginUser.userRole==0 }">
        	<div class="earnings">
            	<div class="one_half">
                	<h4>账户余额</h4>
                    <h2>￥${loginUser.userBalance }</h2>
                </div><!--one_half-->
                <div class="one_half last alignright">
                	<h4><a href="" style="color:#fff">历史记录</a></h4>
                    <h2><a href="">充值</a></h2>
                </div><!--one_half last-->
            </div><!--earnings-->
        </c:if>
        </div><!--headerwidget-->
        
    </div><!--header-->
</body>
</html>