
var curr_page=1;//当前页，默认为第一页
var page_size=10;//容量，每页最多显示条目书
var branch_num="";//网点编号
var seach_word="";//搜索关键字：用户姓名、用户身份证、用户电话
var meter_type;//电表类型
var meter_status;//电表状态
var total_count;//总记录数
var total_page;//总页数
jQuery(document).ready(function($){	
	///设置顶部导航样式///
	$(".headermenu li").removeClass('current');
	$(".headermenu li").eq(4).addClass('current');
	////////
	
	total_count=$("#b1").text();
	total_page=Math.ceil(total_count/page_size);
	dr(curr_page,page_size);
	
	///// CHECKBOX TRANSFORM /////
	jQuery('input:checkbox').uniform();

	
	
	///// CHECK ALL /////
	jQuery('.checkall, .checkall2').click(function(){
		if(jQuery(this).is(':checked')) {
			jQuery(this).parents('table')
						.find('input:checkbox')
						.each(function(){
									   
				jQuery(this).attr('checked',true);
				
				//check if this checkbox is part of the list(tbody) not in the header/footer (thead/tfoot)
				//this will add class "selected" in a row when checked
				if(jQuery(this).parents('tbody').length > 0)
					jQuery(this).parents('tr').addClass('selected');
			});
		} else {
			jQuery(this).parents('table')
						.find('input:checkbox')
						.each(function(){
									   
				jQuery(this).attr('checked',false);
				
				//check if this checkbox is part of the list(tbody) not in the header/footer (thead/tfoot)
				//this will remove class "selected" in a row when unchecked
				if(jQuery(this).parents('tbody').length > 0)
					jQuery(this).parents('tr').removeClass('selected');
			});
		}
		
		//this is needed to remain the checkbox in transformed (uniform) state
		jQuery.uniform.update();
	});
	
	
	///// EACH CHECKBOX CLICK EVENT /////
	/*jQuery('.mailinbox input:checkbox').each(function(){
		jQuery(this).click(function(){
			if(!jQuery(this).is(':checked')) {
				
				//this will hide trash icon only when there are no selected row
				var hidetrash = true;
				jQuery('.mailinbox tbody input:checkbox').each(function(){
					if(jQuery(this).is(':checked'))
						hidetrash = false;
				});
				
				if(hidetrash)
					jQuery('.dropdown_label, .reportspam, .msgtrash').hide();
				
				//check if this checkbox is part of the list(tbody) not in the header/footer (thead/tfoot)
				//this will remove class "selected" in a row when checked
				if(jQuery(this).parents('tbody').length > 0)
					jQuery(this).parents('tr').removeClass('selected');
					
			} else {
				
				//we use css({display:block}) instead of show() because show() is 
				//using display: inline to show element
				jQuery('.dropdown_label, .reportspam, .msgtrash').css({display: 'block'});
				
				//check if this checkbox is part of the list(tbody) not in the header/footer (thead/tfoot)
				//this will add class "selected" in a row when checked
				if(jQuery(this).parents('tbody').length > 0)
					jQuery(this).parents('tr').addClass('selected');
			
			}
		});
	});*/
	
	
	///// SHOW DROP DOWN BUTTON /////
	jQuery('.dropdown').each(function(){
		var t = jQuery(this);
		t.find('a.dropdown_label').click(function(){
			if(!t.hasClass('open')) {
				var h = t.height();
				t.find('ul').show().css({top: h+2+'px'});	
				t.addClass('open');
			} else {
				t.find('ul').hide();	
				t.removeClass('open');				   
			}
			return false;
		});
		
	});
	
	///// HIDE DROP DOWN BUTTON /////
	jQuery(document).click(function(){
		jQuery('.dropdown').removeClass('open').find('ul').hide();
	});
	
});
/**选择搜索类型*/
function changetype(){
	meter_type=jQuery("#metertype").val();
	//coding...获取总条目数，用于分页
	findNewCount(branch_num,seach_word,meter_type,meter_status);
	console.log(meter_type);
	dr(curr_page,page_size,"",branch_num);
}
/**选择搜索状态*/
function changestatus(){
	meter_status=jQuery("#meterstatus").val();
	//coding...获取总条目数，用于分页
	findNewCount(branch_num,seach_word,meter_type,meter_status);
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
		curr_page-=1;
		jQuery("#down_page").removeClass("next_disabled");
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
		///// EACH CHECKBOX CLICK EVENT /////
			jQuery('.mailinbox input:checkbox').each(function(){
				jQuery(this).click(function(){
					if(!jQuery(this).is(':checked')) {
						
						//this will hide trash icon only when there are no selected row
						var hidetrash = true;
						jQuery('.mailinbox tbody input:checkbox').each(function(){
							if(jQuery(this).is(':checked'))
								hidetrash = false;
						});
						
						if(hidetrash)
							jQuery('.dropdown_label, .reportspam, .msgtrash').hide();
						
						//check if this checkbox is part of the list(tbody) not in the header/footer (thead/tfoot)
						//this will remove class "selected" in a row when checked
						if(jQuery(this).parents('tbody').length > 0)
							jQuery(this).parents('tr').removeClass('selected');
							
					} else {
						
						//we use css({display:block}) instead of show() because show() is 
						//using display: inline to show element
						jQuery('.dropdown_label, .reportspam, .msgtrash').css({display: 'block'});
						
						//check if this checkbox is part of the list(tbody) not in the header/footer (thead/tfoot)
						//this will add class "selected" in a row when checked
						if(jQuery(this).parents('tbody').length > 0)
							jQuery(this).parents('tr').addClass('selected');
					
					}
				});
			});
		}
	});
}
/**导航栏选择网点后，搜索展示该网点下的电表*/
function select_branch(obj,b_no){
	branch_num=b_no;
	jQuery("#branches_tree li").removeClass("current");
	jQuery(obj).parent().addClass("current");
	jQuery("#branches_tree li a").removeClass("inbox");
	jQuery("#branches_tree li a").addClass("drafts");
	jQuery(obj).removeClass("drafts");
	jQuery(obj).addClass("inbox");
	//coding...获取总条目数，用于分页
	findNewCount(branch_num,seach_word,meter_type,meter_status);
	dr(curr_page,page_size,"",branch_num);
}
/**按条件搜索*/
function seachfor(){
	seach_word=jQuery("#keyword").val();
	if(seach_word=="请输入关键字"||jQuery.trim(seach_word)==""){
		seach_word="";
	}
	//coding...获取总条目数，用于分页
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
//		var basis=parseFloat(jQuery(obj).prev().text())+parseFloat(money);
//		jQuery(obj).prev().text(basis);
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
/**批量操作*/
function centralized(s){
	var ids=document.getElementsByName("mIds");
	var mIds="";
	for(var i=0;i<ids.length;i++){
		if(ids[i].checked){
			mIds=mIds+ids[i].value+",";
		}
	}
	mIds=mIds.substring(0, mIds.length-1);
	console.log(mIds);
	//局部刷新比较麻烦，采用页面刷新
	modify_all(mIds,s);
}
/**统一修改状态，实现全页面刷新*/
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
				window.location="admin/detailView";
			}else{
				alert("修改失败");
			}
		}
	});
	
}
function duqu(){
	jQuery.ajax({
		url:"user/duqu",
		dataType:"json",
		data:{"addr":"04"},
		async:true,
		success:function(data){
			console.log(data);
			jQuery("#span1").text(data.data);
		},
		error:function(){
			alert("err");
		}
	});
}