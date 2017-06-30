<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript">
jQuery(document).ready(function($){
	$("#sdate").bind("focus",function(){WdatePicker({
		maxDate:"%y-%M-%d",
		isShowClear:false,
		readOnly:true
	});});
	$("#edate").bind("focus",function(){WdatePicker({
		maxDate:"%y-%M-%d",
		startDate:"%y-%M-%d",
		minDate:"#F{$dp.$D('sdate')}",
		isShowClear:false,
		readOnly:true
	});});
});
</script>
</head>
<body>
<div class="pageheader">
            <h1 class="pagetitle">查看?历史</h1>
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
          		<tr><th>和睦新村：</th><th><input class="Wdate" id="sdate">-<input class="Wdate" id="edate"></th><th><input type="button" value="查询"></th></tr>
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
</body>
</html>