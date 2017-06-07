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
<script type="text/javascript" src="js/custom/userlist.js"></script>
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
            <h1 class="pagetitle">管理页面</h1>
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
                        <li><a class="reportspam">这个按钮做点啥呢</a></li>
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
                    	<li class="right"><a class="next"></a></li>
                        <li class="right"><a class="prev prev_disabled"></a></li>
                        <li class="right"><span class="pageinfo">1-10 of ${paging_user_totalcount}</span></li>
                    </ul>
                    <span class="clearall"></span>
                </div><!--msghead-->
                
                <table cellpadding="0" cellspacing="0" border="0" class="stdtable mailinbox">
                    <colgroup>
                        <col class="con1" width="3%"/>
                        <col class="con0" width="3%" />
                        <col class="con1" width="8%"/>
                        <col class="con0" width="8%"/>
                        <col class="con1" width="8%"/>
                        <col class="con0" width="15%"/>
                        <col class="con1" width="10%"/>
                        <col class="con0" width="27%"/>
                        <col class="con1" width="8%"/>
                        <col class="con0" width="5%"/>
                        <col class="con1" width="5%"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th width="20" class="head1 aligncenter"><input type="checkbox" name="checkall" class="checkall" /></th>
                        <th class="head0">&nbsp;</th>
                        <th class="head1">姓名</th>
                        <th class="head0">账户名</th>
                        <th class="head1 attachement">手机</th>
                        <th class="head0">邮箱</th>
                        <th>身份证号</th>
                        <th>地址</th>
                        <th>余额</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <!-- <tfoot>
                        <tr>
                            <th class="head1 aligncenter"><input type="checkbox" name="checkall" class="checkall2" /></th>
                            <th class="head0"></th>
                            <th class="head1">Sender</th>
                            <th class="head0">Subject</th>
                            <th class="head1 attachement">&nbsp;</th>
                            <th class="head0">Date</th>
                        </tr>
                    </tfoot> -->
                    <tbody>
                    <c:forEach items="${userList}" var="user" varStatus="vs">
                    	<tr>
                    		<td class="aligncenter"><input type="checkbox" name="cuser" value="${user.id}"/></td>
                    		<td class="star">${vs.count}</td>
                    		<td>${user.name}</td>
                    		<td>${user.account}</td>
                    		<td>${user.mobile}</td>
                    		<td>${user.email}</td>
                    		<td>${user.idcard}</td>
                    		<td>${user.address}</td>
                    		<td>${user.balance}</td>
                    		<td>${user.status==0?"活跃":"停用"}</td>
                    		<td>
	                    		<c:if test="${user.status==0}">
	                    		<a href="admin/disable?userid=${user.id}" style="color:red">停用</a>
	                    		</c:if>
	                    		<c:if test="${user.status==1}">
	                    		<a href="admin/enable?userid=${user.id}" style="color:green">启用</a>
	                    		</c:if>
                    		</td>
                    	</tr>
                    </c:forEach>
                    </tbody>
                </table>             
             </div>
             <div id="compose" class="subcontent" style="display: none">&nbsp;
             	
             </div>
        </div><!--contentwrapper-->
    
    </div><!--centercontent-->
    
    
</div><!--bodywrapper-->

</body>
</html>