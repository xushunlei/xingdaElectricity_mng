<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看消息页面</title>
<% String basePath = request.getScheme()+"://"+
		request.getServerName()+":"+
		request.getServerPort()+request.getContextPath()+"/";%>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript">
var curr_page=1;//当前页，默认为第一页
var page_size=10;//容量，每页最多显示条目书
var branch_num="";//网点编号
var seach_word="";//搜索关键字：用户姓名、用户身份证、用户电话
var meter_type;//电表类型
var meter_status;//电表状态
var total_count;//总记录数
var total_page;//总页数
jQuery(document).ready(function($){
	total_count=$("#b1").text();
	total_page=Math.ceil(total_count/page_size);
	
	dr(curr_page,page_size);
	///// 重置单选框 /////
	jQuery('input:checkbox').uniform();
	///// 选择全部 /////
	jQuery('.checkall').click(function(){//全选功能的单选框点击事件
		if(jQuery(this).is(':checked')) {//若勾选：全选
			jQuery(this).parents('table')//找到祖先节点中的表格节点
						.find('input:checkbox')//找到单选框节点
						.each(function(){//遍历节点执行
									   
				jQuery(this).attr('checked',true);//选中遍历的节点
				if(jQuery(this).parents('tbody').length > 0)//若该节点祖先节点为‘tbody’的长度>0：有记录
					jQuery(this).parents('tr').addClass('selected');//给该节点中“tr”祖先节点增加“selected”样式
			});
		} else {
			jQuery(this).parents('table')//若不选：全不选
						.find('input:checkbox')
						.each(function(){
									   
				jQuery(this).attr('checked',false);
				if(jQuery(this).parents('tbody').length > 0)
					jQuery(this).parents('tr').removeClass('selected');
			});
		}
		jQuery.uniform.update();
	});
	
});
/**选择搜索网点*/
function changebranch(){
	branch_num=jQuery("#branchOps").val();
	if(branch_num=="0"){
		branch_num="";
	}
	curr_page=1;
	findNewCount(branch_num,seach_word,meter_type,meter_status);
	dr(curr_page,page_size,"",branch_num);
}
/**选择搜索类型*/
function changetype(){
	meter_type=jQuery("#metertype").val();
	findNewCount(branch_num,seach_word,meter_type,meter_status);
	console.log(meter_type);
	curr_page=1;
	dr(curr_page,page_size,"",branch_num);
}
/**选择搜索状态*/
function changestatus(){
	meter_status=jQuery("#meterstatus").val();
	findNewCount(branch_num,seach_word,meter_type,meter_status);
	curr_page=1;
	console.log(meter_status);
	dr(curr_page,page_size,"",branch_num);
}
/**侦测条件输入框的键盘按键，13表示敲入回车键*/
function keyDown(){
	if(event.keyCode ==13){
		seachfor();
	}
}
/**下一页*/
function next_page(){
	if(curr_page<total_page){
		jQuery("#up_page").removeClass("prev_disabled");
		curr_page+=1;
		seachfor();
		if(curr_page>=total_page){
			jQuery("#down_page").addClass("next_disabled");
		}
	}
}
/**上一页*/
function prev_page(){
	if(curr_page>1){
		jQuery("#down_page").removeClass("next_disabled");
		curr_page-=1;
		seachfor();
		if(curr_page<=1)jQuery("#up_page").addClass("prev_disabled");
	}
}
/**绘制电表列表*/
function dr(pageNo,pageSize,seachWord,branchNum){
	var table=jQuery("#meter_info");
	jQuery.ajax({
		url:"admin/meter_page",
		type:"post",
		data:{
			"pageNo":pageNo,
			"pageSize":pageSize,
			"seachfor":seachWord,
			"branchNum":branchNum,
			"meterType":meter_type,
			"meterStatus":meter_status
			},
		dataType:"json",
		async:true,
		error:function(XMLHttpRequest,textStatus,errorThow){
			alert(XMLHttpRequest.status);//0
			alert(XMLHttpRequest.readyState);//0
			alert(textStatus);//error
		},
		success:function(result){
			jQuery("#meter_info tbody").html('');
			for(var i=0;i<result.length;i++){
				var meterType=result[i].meterType==0?"单相":"三相";
				var str='<tr><td class="aligncenter">'+
				'<input type="checkbox" name="mIds" value="'+result[i].meterId+
				'"/></td><td class="star">'+(i+1)+'</td><td>'+result[i].meterNumber+'</td>'+
				'<td>'+result[i].meterBranch.branchName+'</td>'+
				'<td>'+meterType+'</td><td><select style="float:right;" class="color_'+result[i].meterStatus+'" onchange="modifyStatus('+result[i].meterId+',this)">'+
				'<option value="0" style="color:green">供电</option><option value="1" style="color:orange">透支</option><option value="2" style="color:red">拉闸</option></select></td>'+
				'<td><span>'+result[i].meterBalance+'</span><button style="float:right;" onclick="recharge('+result[i].meterId+',this)">充值</button></td>'+
				'<td><span>'+result[i].meterMaxOverdraft+'</span><button style="float:right;" onclick="setOverdraft('+result[i].meterId+',this)">设置</button></td>'+
				'<td>'+result[i].meterTotalConsumption+'</td>'+
				'<td>'+result[i].meterUser.userName+'</td>'+
				'<td>'+result[i].meterUser.userMobile+'</td>'+
				'<td>'+result[i].meterUser.userIdcard+'</td>'+
				'<td>'+result[i].meterUser.userAddress+'</td></tr>';
				table.append(str);
				jQuery("#meter_info tr:eq("+(i+1)+") select").val(result[i].meterStatus);
			}
			var t=(curr_page-1)*page_size+1;
			jQuery("#page_info").text(t+"-"+(t+result.length-1));
			///// EACH CHECKBOX CLICK EVENT /////
			jQuery('.mailinbox input:checkbox').each(function(){//遍历.mailinbox中的单选框
				jQuery(this).click(function(){//绑定点击事件
					if(!jQuery(this).is(':checked')) {//若被取消勾选
						var hidetrash = true;
						jQuery('.mailinbox tbody input:checkbox').each(function(){//遍历tbody的单选框
							if(jQuery(this).is(':checked')){//若被选中
								hidetrash = false;
							}
						});
						
						if(hidetrash){//表示没有选中项
							jQuery('.dropdown_label, .reportspam, .msgtrash').hide();
						}
						if(jQuery(this).parents('tbody').length > 0){//有记录
							jQuery(this).parents('tr').removeClass('selected');//把记录样式移除选中的样式
						}
					} else {//若被勾选
						jQuery('.dropdown_label, .reportspam, .msgtrash').css({display: 'block'});//显示
						if(jQuery(this).parents('tbody').length > 0){
							jQuery(this).parents('tr').addClass('selected');
						}
					}
				});
			});
		}
	});
}
/**导航栏选择网点后，搜索展示该网点下的电表*/
function select_branch(obj,b_no){
	curr_page=1;
	branch_num=b_no;
	jQuery("#branches_tree li").removeClass("current");
	jQuery(obj).parent().addClass("current");
	jQuery("#branches_tree li a").removeClass("inbox");
	jQuery("#branches_tree li a").addClass("drafts");
	jQuery(obj).removeClass("drafts");
	jQuery(obj).addClass("inbox");
	findNewCount(branch_num,seach_word,meter_type,meter_status);
	dr(curr_page,page_size,"",branch_num);
}
/**按条件搜索*/
function seachfor(){
	seach_word=jQuery("#keyword").val();
	if(seach_word=="请输入关键字"||jQuery.trim(seach_word)==""){
		seach_word="";
	}
	findNewCount(branch_num,seach_word,meter_type,meter_status);
	dr(curr_page,page_size,seach_word,branch_num);
}
/**每次搜索后都要重新从后台读取满足条件的记录数，并转换成总页数用于分页*/
function findNewCount(branchNum, seachWord, meterType, meterStatus){
	jQuery.ajax({
		url:"admin/findCount",
		type:"post",
		data:{
			"branchNum":branchNum,
			"seachWord":seachWord,
			"meterType":meterType,
			"meterStatus":meterStatus
			},
		async:true,
		dataType:"json",
		error:function(XMLHttpRequest,textStatus,errorThow){
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		},
		success:function(redata){
			console.log(redata);
			jQuery("#b1").text(redata.total_count);
			total_page=Math.ceil(redata.total_count/page_size);
		}
	});
}
/**电表充值*/
function recharge(meterid,obj){
	var money=prompt("输入充值金额");
	if(/^\d+(.\d+)?$/.test(money)){
		console.log("充值￥"+money+"元");
		jQuery.ajax({
			url:"admin/payment",
			data:{"meter_id":meterid,"price":money},
			type:"post",
			dataType:"json",
			success:function(result){
				if(!result.flag){
					alert("充值失败！");
				}else{
					jQuery(obj).prev().text(result.funds);
				}
			},
			error:function(XMLHttpRequest,textStatus,errorThow){
				alert("充值失败！");
			}
		});
	}else{
		alert("输入金额格式错误！！");
	}
}
/**设置最大透支金额*/
function setOverdraft(meterid,obj){
	var money=prompt("输入最大透支金额");
	if(/^\d+$/.test(money)){
		console.log("可透支￥"+money+"元");
		jQuery.ajax({
			type:"post",
			url:"admin/markOverdraft",
			data:{"meterId":meterid,"maxValue":money},
			dataType:"json",
			error:function(){
				alert("设置失败");
			},
			success:function(result){
				if(result.flag){
					jQuery(obj).prev().text(money);
				}else{
					alert("shibai");
				}
			}
		});
	}else{
		alert("输入金额格式错误，只能是正整数格式！！");
	}
}
/**更改指定ID电表的状态*/
function modifyStatus(meterId,obj){
	var t=jQuery(obj).val();
	jQuery.ajax({
		url:"admin/modifyStatus",
		type:"post",
		data:{"meterId":meterId,"meterStatus":t},
		dataType:"json",
		error:function(){
			alert("大错");
		},
		success:function(result){
			if(result.flag){
				jQuery(obj).removeClass().addClass("color_"+t);
			}else{
				alert("状态更改失败");
			}
		}
	});
}
/**统一修改状态，父页面重新加载子页面*/
function modify_all(mIds,status){
	jQuery.ajax({
		url:"admin/modifyStatus",
		type:"post",
		data:{"meterId":mIds,"meterStatus":status},
		dataType:"json",
		error:function(e){
			console.log(e);
		},
		success:function(result){
			if(result.flag){
				alert("修改成功");
				jQuery("#contentwrapper").load("user/jumpin_mnguser");
			}else{
				alert("修改失败");
			}
		}
	});
	
}
/**批量操作*/
function centralized(s){
	if(isNaN(s))return;
	var ids=document.getElementsByName("mIds");
	var bNums="";
	for(var i=0;i<ids.length;i++){
		if(ids[i].checked){
			bNums=bNums+ids[i].value+",";
		}
	}
	bNums=bNums.substring(0, bNums.length-1);
	//局部刷新比较麻烦，采用页面刷新
	modify_all(bNums,s);
}
</script>
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
             
<div id="inbox" class="subcontent">

   <div class="msghead">
       <ul class="msghead_menu">
           <li class="marginleft5 dropdown">
               <select class="dropdown_label my_select" onchange="centralized(this.value)">
               <option>批量操作</option>
               <option value="0">供电</option>
               <option value="1">透支</option>
               <option value="2">拉闸</option>
               </select>
           </li>
           <li class="marginleft5"><a class="msgtrash" title="Trash"></a></li>
       	<li class="right"><a class="next" href="javascript:next_page()" id="down_page"></a></li>
           <li class="right"><a class="prev prev_disabled" id="up_page" href="javascript:prev_page()"></a></li>
           <li class="right"><p class="pageinfo"><span id="page_info">1-10</span> of <b id="b1">${all_meter_count}</b></p></li>
           <li class="right">
  <div class="search">
      	<input type="text" name="keyword" id="keyword" value="请输入关键字" onkeydown="keyDown();"/>
          <!-- <button class="submitbutton" onclick="drowTable(1,10)"></button> -->
          <input type="button" onclick="seachfor();" class="submitbutton" id="seach_btn">
  </div><!--search-->
  </li>
  <li class="right"><select id="metertype" onchange="changetype()" class="my_select">
   <option>选择类型</option>
   <option value="0">单相电表</option>
   <option value="1">三相电表</option>
  </select></li>
  <li class="right"><select id="meterstatus" onchange="changestatus()" class="my_select">
   <option>选择状态</option>
   <option value="0">供电</option>
   <option value="1">透支</option>
   <option value="2">拉闸</option>
  </select></li>
  <li class="right"><select  class="my_select" id="branchOps" onchange="changebranch()">
  	<option value="0">选择网点</option>
  	<c:forEach items="${branchs}" var="bran">
  	<option value="${bran.branchNumber }">${bran.branchName }</option>
  	</c:forEach>
  </select></li>
       </ul>
       <span class="clearall"></span>
   </div><!--msghead-->
   
   <table cellpadding="0" cellspacing="0" border="0" class="stdtable mailinbox" id="meter_info">
       <colgroup>
           <col class="con1" width="3%"/>
           <col class="con0" width="3%" />
           <col class="con1" width="6%"/>
           <col class="con1" width="6%"/>
           <col class="con0" width="5%"/>
           <col class="con1" width="5%"/>
           <col class="con0" width="10%"/>
           <col class="con1" width="10%"/>
           <col class="con0" width="8%"/>
           <col class="con1" width="8%"/>
           <col class="con0" width="10%"/>
           <col class="con1" width="10%"/>
           <col class="con0" width="16%"/>
       </colgroup>
       <thead>
       <tr>
           <th width="20" class="head1 aligncenter"><input type="checkbox" name="checkall" class="checkall" /></th>
           <th class="head0">&nbsp;</th>
           <th class="head1">编号</th>
           <th class="head1">网点</th>
           <th class="head0">类型</th>
           <th class="head1">状态</th>
           <th class="head0">余额</th>
           <th>允许透支金额</th>
           <th>消费总额</th>
           <th>户主</th>
           <th>联系电话</th>
           <th>身份证</th>
           <th>地址</th>
       </tr>
       </thead>
       <tbody>
       </tbody>
   </table>             
</div>

</body>
</html>