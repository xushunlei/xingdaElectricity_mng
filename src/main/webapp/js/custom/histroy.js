/*
 * 	Additional function for message.html
 *	Written by ThemePixels	
 *	http://themepixels.com/
 *
 *	Copyright (c) 2012 ThemePixels (http://themepixels.com)
 *	
 *	Built for Amanda Premium Responsive Admin Template
 *  http://themeforest.net/category/site-templates/admin-templates
 */


jQuery(document).ready(function($){	
	///设置顶部导航样式///
	$(".headermenu li").removeClass('current');
	$(".headermenu li").eq(5).addClass('current');
	
	$(".detail").hide();
	
	require.config({
		paths:{echarts:"./js/dist"}
	})
	require(["echarts", "echarts/chart/line"], function (ec) {
	    var myChart = ec.init(document.getElementById("trydrow"));
	    $.ajax({
	    	url:"admin/chartVar",
	    	type:"post",
	    	data:{"branch":"hzgongsu001"},
	    	dataType:"json",
	    	success:function(data){
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
	    	    	            "name":"总值",
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
function showDetail(obj){
	jQuery(obj).parents("table").find(".detail").show();
}