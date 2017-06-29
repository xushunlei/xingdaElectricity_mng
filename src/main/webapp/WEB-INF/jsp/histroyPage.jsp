<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript" src="js/custom/histroy.js"></script>

<script type="text/javascript" src="js/echarts/echarts.js"></script>
</head>
<body class="withvernav">
<div class="bodywrapper">
    <%@include file="header.jsp" %>
    <div class="vernav iconmenu">
    	<ul>
        	<li class="current"><a href="javascript:void(0);" class="inbox">历史用电记录</a></li>
            <li><a href="javascript:void(0);" class="drafts">充值记录</a></li>
            <li><a href="javascript:void(0);" class="sent">历史电价</a></li>
        </ul>
        <a class="togglemenu"></a>
        <br /><br />
    </div><!--leftmenu-->
    
    <div class="centercontent">
    
        <div class="pageheader">
            <h1 class="pagetitle">查看历史</h1>
            <span class="pagedesc">The content below are loaded using ajax</span>
            
            <ul class="hornav">
                <li class="current"><a href="#inbox">网点记录</a></li>
                <li><a href="#compose">电表记录</a></li>
            </ul>
        </div><!--pageheader-->
        
        <div id="contentwrapper" class="contentwrapper">
          <div id="inbox" class="subcontent">
          	<center>
          		<table>
          		<thead>
          		<tr><th>临平中心：</th><th><input class="Wdate">-<input class="Wdate"></th><th><input type="button" value="查询"></th></tr>
          		</thead>
          			<tr><th>总充值金额：</th><th>10300元</th><td><a href="javascript:void(0);" onclick="showDetail(this)">+展开</a></td></tr>
          			<tr class="detail"><td>人民广场：</td><td>3000</td></tr>
          			<tr class="detail"><td>余杭图书馆：</td><td>5200</td></tr>
          			<tr class="detail"><td>临平职高：</td><td>2100</td></tr>
          		</table>
          		<table>
          		<thead>
          		<tr><th>和睦新村：</th><th><input class="Wdate">-<input class="Wdate"></th><th><input type="button" value="查询"></th></tr>
          		</thead>
          			<tr><th>总充值金额：</th><th>3600元</th><td><a href="javascript:void(0);" onclick="showDetail(this)">+展开</a></td></tr>
          			<tr class="detail"><td>1幢</td><td>800</td></tr>
          			<tr class="detail"><td>2幢</td><td>1100</td></tr>
          			<tr class="detail"><td>3幢</td><td>1000</td></tr>
          			<tr class="detail"><td>4幢</td><td>700</td></tr>
          		</table>
          	</center>
          	<div id="trydrow" style="height:400px"></div>
          </div>
          <div id="compose" class="subcontent" style="display: none">
          
          </div>
        </div><!--contentwrapper-->
    
    </div><!--centercontent-->
    
    
</div><!--bodywrapper-->

</body>
</html>