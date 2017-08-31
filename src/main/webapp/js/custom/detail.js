var myChart;
//今日数据
var new_value=[];
//昨日数据
var old_value=[5, 20, 36, 10, 10];
//横坐标
var x_axis=['00:00','00:15','00:30','00:45','01:00'];
jQuery(document).ready(function($){	
	
	///设置顶部导航样式///
	$(".headermenu li").removeClass('current');
	$(".headermenu li").eq(4).addClass('current');
	
	////绘制图表准备////
	$.ajax({
		url:"charts/refer",
		dataTyp:"json",
		success:function(data){
			x_axis=data.xAxis;
			old_value=data.value;
		},
		error:function(e1,e2,e3){
			console.log(e1.status);
			console.log(e1.readyState);
			console.log(e3);
		}
	})
	myChart = echarts.init(document.getElementById('detailChart'));
	myChart.setOption({
	    title: {
	        text: '数据比较'
	    },
	    tooltip: {},
	    legend: {
	        data:['今日','昨日']
	    },
	    xAxis: {
	        data: []
	    },
	    yAxis: {},
	    series: [
	        {name: '昨日',
	        type: 'line',
	        data: []},
	        {name:'今日',
	        type:'line',
	        data:[]}
	     ]
	});
	
	var timer=setInterval(draw,3000);
});

function draw(){
	console.log("绘制……");
	jQuery.ajax({
		url:"user/ma",
		dataType:"json",
		success:function(data){
			new_value.push(data);//将数据压入数组
			if(new_value.length>x_axis.length)
				new_value.shift();//删除第一个元素
			// 填入数据
		    myChart.setOption({
		    	xAxis: {
			        data: x_axis
			    },
		        series: [
		            {
		            name:'昨日',
		            data: old_value
		            },
		            {
		            // 根据名字对应到相应的系列
		            name: '今日',
		            data: new_value
		            }
		        ]
		    });
		},
		error:function(e1,e2,e3){
			console.log(e1.status);
			console.log(e1.readyState);
			console.log(e3);
		}
	})
}