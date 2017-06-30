<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
jQuery(document).ready(function($){
	require.config({
		paths:{echarts:"./js/dist"}
	});
	require(["echarts", "echarts/chart/line"],function(ec){
		var myChart = ec.init(document.getElementById("pricechart"));
		$.ajax({
			url:"admin/chartPrice",
			type:"post",
			dataType:"json",
			success:function(data){
				var option={
						tooltip: {
	    	    	        show: true
	    	    	    },
	    	    	    legend: {
	    	    	        data:['电价']
	    	    	    },
	    	    	    xAxis : [
	    	    	        {
	    	    	            type : 'category',
	    	    	            data : data.month
	    	    	        }
	    	    	    ],
	    	    	    yAxis : [
	    	    	        {
	    	    	            type : 'value'
	    	    	        }
	    	    	    ],
	    	    	    series : [
	    	    	        {
	    	    	            "name":"电价",
	    	    	            "type":"line",
	    	    	            "data":data.money
	    	    	        }
	    	    	    ]
				};
				myChart.setOption(option);
			}
		});
	});
});
</script>
</head>
<body>
<div class="pageheader">
    <h1 class="pagetitle">查看历史</h1>
    <span class="pagedesc">The content below are loaded using ajax</span>
</div><!--pageheader-->

<div style="height:400px" id="pricechart">
</div>
</body>
</html>