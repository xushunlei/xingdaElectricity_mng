
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
function changetype(){
	meter_type=jQuery("#metertype").val();
	//coding...获取总条目数，用于分页
	findNewCount(branch_num,seach_word,meter_type,meter_status);
	console.log(meter_type);
	dr(curr_page,page_size,"",branch_num);
}
function changestatus(){
	meter_status=jQuery("#meterstatus").val();
	//coding...获取总条目数，用于分页
	findNewCount(branch_num,seach_word,meter_type,meter_status);
	console.log(meter_status);
	dr(curr_page,page_size,"",branch_num);
}
function keyDown(){
	if(event.keyCode ==13){
		seachfor();
	}
}
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
function prev_page(){
	if(curr_page>1){
		curr_page-=1;
		jQuery("#down_page").removeClass("next_disabled");
		seachfor();
		if(curr_page<=1)jQuery("#up_page").addClass("prev_disabled");
	}
}
function drowTable(pageNo,pageSize,obj,keyword){
	var table=jQuery("#meter_info");
	var seachword;
	if(keyword=="undefiend"||jQuery.trim(keyword)==""){
		branch_num="";
	}else{
		//seachword=keyword;
		branch_num=keyword;
		jQuery("#branches_tree li").removeClass("current");
		jQuery(obj).parent().addClass("current");
		jQuery("#branches_tree li a").removeClass("inbox");
		jQuery("#branches_tree li a").addClass("drafts");
		jQuery(obj).removeClass("drafts");
		jQuery(obj).addClass("inbox");
	}
	seachword=jQuery("#keyword").val();
	if(seachword=="请输入关键字"||jQuery.trim(seachword)==""){
		seachword="";
	}
	jQuery.ajax({
		url:"admin/meter_page",
		type:"post",
		data:{"pageNo":pageNo,"pageSize":pageSize,"seachfor":seachword},
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
				var meterStatus="";
				if(result[i].meterStatus==0)meterStatus="供电";
				else if(result[i].meterStatus==1)meterStatus="透支";
				else if(result[i].meterStatus==2)meterStatus="拉闸";
				var str='<tr><td class="aligncenter">'+
				'<input type="checkbox" name="cuser" value="'+result[i].meterId+
				'"/></td><td class="star">'+(i+1)+'</td><td>'+result[i].meterNumber+'</td>'+
				'<td>'+meterType+'</td><td>'+meterStatus+'<select style="float:right;">'+
				'<option value="0">供电</option><option value="1">透支</option><option value="2">拉闸</option></select></td>'+
				'<td>'+result[i].meterBalance+'<button style="float:right;" onclick="recharge('+result[i].meterId+')">充值</button></td>'+
				'<td>'+result[i].meterMaxOverdraft+'<button style="float:right;" onclick="setOverdraft('+result[i].meterId+')">设置</button></td>'+
				'<td>'+result[i].meterTotalConsumption+'</td>'+
				'<td>'+result[i].meterUser.userAccount+'</td>'+
				'<td>'+result[i].meterUser.userMobile+'</td>'+
				'<td>'+result[i].meterUser.userIdcard+'</td></tr>';
				table.append(str);
			}
		
		}
	});
}
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
				var meterStatus="";
				if(result[i].meterStatus==0)meterStatus="供电";
				else if(result[i].meterStatus==1)meterStatus="透支";
				else if(result[i].meterStatus==2)meterStatus="拉闸";
				var str='<tr><td class="aligncenter">'+
				'<input type="checkbox" name="cuser" value="'+result[i].meterId+
				'"/></td><td class="star">'+(i+1)+'</td><td>'+result[i].meterNumber+'</td>'+
				'<td>'+meterType+'</td><td>'+meterStatus+'<select style="float:right;">'+
				'<option value="0">供电</option><option value="1">透支</option><option value="2">拉闸</option></select></td>'+
				'<td><span>'+result[i].meterBalance+'</span><button style="float:right;" onclick="recharge('+result[i].meterId+',this)">充值</button></td>'+
				'<td><span>'+result[i].meterMaxOverdraft+'</span><button style="float:right;" onclick="setOverdraft('+result[i].meterId+',this)">设置</button></td>'+
				'<td>'+result[i].meterTotalConsumption+'</td>'+
				'<td>'+result[i].meterUser.userAccount+'</td>'+
				'<td>'+result[i].meterUser.userMobile+'</td>'+
				'<td>'+result[i].meterUser.userIdcard+'</td></tr>';
				table.append(str);
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
function seachfor(){
	seach_word=jQuery("#keyword").val();
	if(seach_word=="请输入关键字"||jQuery.trim(seach_word)==""){
		seach_word="";
	}
	//coding...获取总条目数，用于分页
	findNewCount(branch_num,seach_word,meter_type,meter_status);
	dr(curr_page,page_size,seach_word,branch_num);
}
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
			alert(XMLHttpRequest.status);//0
			alert(XMLHttpRequest.readyState);//0
			alert(textStatus);//error
		},
		success:function(redata){
			console.log(redata);
			jQuery("#b1").text(redata.total_count);
			total_page=Math.ceil(redata.total_count/page_size);
		}
	});
}
function recharge(meterid,obj){
	var money=prompt("输入充值金额");
	if(/^\d+(.\d+)?$/.test(money)){
		console.log("充值￥"+money+"元");
		var basis=parseFloat(jQuery(obj).prev().text())+parseFloat(money);
		jQuery(obj).prev().text(basis);
	}else{
		alert("输入金额错误！！");
	}
}
function setOverdraft(meterid,obj){
	var money=prompt("输入最大透支金额");
	if(/^\d+(.\d+)?$/.test(money)){
		console.log("可透支￥"+money+"元");
		jQuery(obj).prev().text(money);
	}else{
		alert("输入金额错误！！");
	}
	console.log(meterid);
}