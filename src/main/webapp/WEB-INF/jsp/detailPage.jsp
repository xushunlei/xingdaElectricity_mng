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
<script type="text/javascript" src="js/custom/detail.js"></script>
<script type="text/javascript" src="js/echarts3/echarts.js"></script>
<style type="text/css">
.my_select{
    height: 30px;
    border-color: #445775;
    margin: 5px;
}
.color_0{
	color:green;
}
.color_1{
	color:orange;
}
.color_2{
	color:red;
}
</style>
</head>
<body class="withvernav">
<div class="bodywrapper">
    <%@include file="header.jsp" %>
    <div class="vernav iconmenu">
    	<c:if test="${loginUser.userRole==1}">
    	<ul id="branches_tree">
   		<c:forEach items="${branchList }" var="branch">
   			<li><a href="javascript:void(0)" onclick="select_branch(this,'${branch.zoneId }');" class="drafts">${branch.zoneName }</a></li>
   		</c:forEach>
        </ul>
    	</c:if>
        <a class="togglemenu"></a>
        <br /><br />
    </div><!--leftmenu-->
    <div class="centercontent">
    
        <div class="pageheader">
            <h1 class="pagetitle" id="pagename">详情页面</h1>
            <span class="pagedesc">The content below are loaded using ajax</span>
            
            <ul class="hornav">
                <li class="current"><a href="#inbox">网点概况</a></li>
                <li><a href="#compose">用户详情</a></li>
            </ul>
        </div><!--pageheader-->
        
        <div id="contentwrapper" class="contentwrapper">
          <!-- 网点概况标签页。图表展示今昔比较数据 -->
          <div id="inbox" class="subcontent">
          	<div id="detailChart" style="width: 400px;height: 380px;"></div>
          </div>
          <!-- 用户列表标签页。 -->
          <div id="compose" class="subcontent" style="display: none">&nbsp;
          	
          </div>
        </div><!--contentwrapper-->
    
    </div><!--centercontent-->
    
    
</div><!--bodywrapper-->

</body>
</html>