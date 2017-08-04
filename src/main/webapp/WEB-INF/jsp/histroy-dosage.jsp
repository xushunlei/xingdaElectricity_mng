<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript">
var timeTicket;
var xVal=["1月","2月","3月","4月","5月","2月","3月","4月","5月"];
var sereis=["224","206","211","248","250","206","211","248","250"];
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
	
	require.config({
		paths:{echarts:"./js/dist"}
	});
		require(["echarts", "echarts/chart/line","echarts/chart/bar"],function(ec){
		    var myChart=ec.init(document.getElementById("trydrow"));
		    myChart.showLoading({
		    	text: "图表数据正在努力加载..."
		    });
		var option = {
			    title : {
			        text: '动态数据',
			        subtext: '纯属虚构'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['最新成交价']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    dataZoom : {
			        show : false,
			        start : 0,
			        end : 100
			    },
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap: false,
			            data : []
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            scale: true,
			            min:200,
			            max:300,
			            name : '价格',
			            //splitArea: { show: true }
			            boundaryGap: [0, '50%']
			        }
			    ],
			    series : [
			        {
			            name:'最新成交价',
			            type:'line',
			            data:[]
			        }
			    ],
			    animationDurationUpdate:10
			}; 
			var timeTicket = setInterval(function (){
			
			$.ajax({
				type:"post",
				async:false,
				url:"user/ni",
				data:{"jg":"5"},
				dataType:"json",
				success:function(redata){
					drow1(redata,option,myChart);
				},
				error:function(){
					alert("sorry biggrandpa table please fail");
				}
			});
			},5000);
		}); 
		function drow(redata,option,myChart){
			if(redata){
				option.xAxis[0].data=redata[0];
				option.series[0].data=redata[1];
				myChart.hideLoading();
				myChart.setOption(option);
			}
		}
		function drow1(redata,option,myChart){
			if(redata){
				xVal.push(redata[1]);
				sereis.push(redata[0]);
				xVal.shift();
				sereis.shift();
				option.xAxis[0].data=xVal;
				option.series[0].data=sereis;
				myChart.hideLoading();
				myChart.setOption(option);
			}
		}
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