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
<script type="text/javascript" src="js/plugins/jquerySession.js"></script>
<script type="text/javascript" src="js/plugins/jquery.alerts.js"></script>
<script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript" src="js/custom/manage.js"></script>
<style type="text/css">
.my_cleancolor{
background:#fff;
}
</style>
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
            <h1 class="pagetitle" id="pagename">管理页面</h1>
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
			            	<form action="" method="post">
			                	<input type="text" name="keyword" id="keyword" value="请输入关键字" />
			                    <!-- <button class="submitbutton" onclick="drowTable(1,10)"></button> -->
			                    <input type="button" onclick="drowTable(1,10)" class="submitbutton">
			                </form>
			            </div><!--search-->
			            </li>
                    </ul>
                    <span class="clearall"></span>
                </div><!--msghead-->
                
                <table cellpadding="0" cellspacing="0" border="0" class="stdtable mailinbox" id="meter_info">
                    <colgroup>
                        <col class="con1" width="3%"/>
                        <col class="con0" width="3%" />
                        <col class="con1" width="10%"/>
                        <col class="con0" width="5%"/>
                        <col class="con1" width="11%"/>
                        <col class="con0" width="15%"/>
                        <col class="con1" width="10%"/>
                        <col class="con0" width="15%"/>
                        <col class="con1" width="8%"/>
                        <col class="con0" width="10%"/>
                        <col class="con1" width="10%"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th width="20" class="head1 aligncenter"><input type="checkbox" name="checkall" class="checkall" /></th>
                        <th class="head0">&nbsp;</th>
                        <th class="head1">编号</th>
                        <th class="head0">类型</th>
                        <th class="head1">状态</th>
                        <th class="head0">余额</th>
                        <th>允许透支金额</th>
                        <th>消费总额</th>
                        <th>户主</th>
                        <th>联系电话</th>
                        <th>身份证</th>
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
                    <%-- <c:forEach items="${meterList}" var="meter" varStatus="vs">
                    	<tr>
                    		<td class="aligncenter"><input type="checkbox" name="cuser" value="${meter.meterId}"/></td>
                    		<td class="star">${vs.count}</td>
                    		<td>${meter.meterNumber}</td>
                    		<td>${meter.meterType==0?"单相":"三相"}</td>
                    		<td>
                    			<c:choose>
                    				<c:when test="${meter.meterStatus==0}">供电</c:when>
                    				<c:when test="${meter.meterStatus==1}">透支</c:when>
                    				<c:when test="${meter.meterStatus==2}">拉闸</c:when>
                    			</c:choose>
                    			<select style="float:right;">
                    				<option >--操作--</option>
                    				<option value="0">供电</option>
                    				<option value="1">透支</option>
                    				<option value="2">拉闸</option>
                    			</select>
                    		</td>
                    		<td>${meter.meterBalance}<button style="float:right;">充值</button></td>
                    		<td>${meter.meterMaxOverdraft}<button style="float:right;">设置</button></td>
                    		<td>${meter.meterTotalConsumption}</td>
                    		<td>${meter.meterUser.userAccount}</td>
                    		<td>${meter.meterUser.userMobile}</td>
                    		<td>${meter.meterUser.userIdcard}</td>
                    	</tr>
                    </c:forEach> --%>
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