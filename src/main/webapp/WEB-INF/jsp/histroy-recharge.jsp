<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/custom/general.js"></script>

<script type="text/javascript">
jQuery(document).ready(function($){
	//初始化日期控件
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
	//绘制图表所需的配置
	require.config({
		paths:{echarts:"./js/dist"}
	});
});
/*选择网点*/
function showWithBranch(){
	jQuery("#meterOps").empty();
	var branchN=jQuery("#branchOps").val();
	if(jQuery.trim(branchN)!=""){
		jQuery.ajax({
			url:"admin/selectMeterByBranch",
			data:{"branchNum":branchN},
			type:"post",
			dataType:"json",
			async:false,
			success:function(redate){
				jQuery("#meterOps").append('<option>选择电表</option>');
				for(var m = 0; m < redate.length; m++){
					var t="<option value='"+redate[m].meterId+"'>"+redate[m].meterNumber+"</option>";
					jQuery("#meterOps").append(t);
				}
			}
		});
	}
}
function showDetail(obj){
	jQuery(obj).parents("table").find(".detail").show();
}
function drawchartByCondi(){
	//时间段
	var s=jQuery("#sdate").val();//2017-07-05
	var e=jQuery("#edate").val();
	var b=jQuery("#branchOps").val();//branchNumber
	var m=jQuery("#meterOps").val();//meterId
	
	if(isNaN(m)){//m="选择电表",统计与图表以branch为单位
		m="";
	}
	if(s!=null&&jQuery.trim(s)!=""){
	jQuery.ajax({
		url:"admin/chartVar",
		data:{"sd":s,"ed":e,"cb":b,"cm":m},
		type:"post",
		dataType:"json",//图表数据：横坐标数组、纵坐标数组;{array[],array[]}
		success:function(data){
			var tp=0;
			for(var t=0;t<data.Xval.length;t++){
				tp+=data.Xval[t];
			};
			jQuery("#totalP").text(tp);
			require(["echarts", "echarts/chart/line"], function (ec){
				var myChart = ec.init(document.getElementById("trydrow"));
				var option = {
	    	    	    tooltip: {
	    	    	        show: true
	    	    	    },
	    	    	    legend: {
	    	    	        data:['充值金额']
	    	    	    },
	    	    	    xAxis : [
	    	    	        {
	    	    	            type : 'category',
	    	    	            data : data.Yval
	    	    	        }
	    	    	    ],
	    	    	    yAxis : [
	    	    	        {
	    	    	            type : 'value'
	    	    	        }
	    	    	    ],
	    	    	    series : [
	    	    	        {
	    	    	            "name":"总值",
	    	    	            "type":"line",
	    	    	            "data":data.Xval
	    	    	        }
	    	    	    ]
	    	    	};
				myChart.setOption(option);
			});
		}
	});
	}
}
</script>
</head>
<body>
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
  		网点：<select id="branchOps" onchange="showWithBranch()">
  			<option value="">选择网点</option>
  			<c:forEach items="${branchList}" var="branch">
  			<option value="${branch.branchNumber }">${branch.branchName}</option>
  			</c:forEach>
  		</select>
  		用户：<select id="meterOps">
  		<option>选择电表</option>
  		</select>
  		时间：<input class="Wdate" id="sdate">-<input class="Wdate" id="edate">
  		<input value="查询" type="button" onclick="drawchartByCondi()">
  		总充值金额：￥<span id="totalP"></span>元
  	</center>
  	<div id="trydrow" style="height:400px"></div>
  </div>
  <div id="compose" class="subcontent" style="display: none">
  </div>
</div><!--contentwrapper-->
    
</body>
</html>