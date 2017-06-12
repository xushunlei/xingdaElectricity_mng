
var curr_page=1;
var page_size=10;
jQuery(document).ready(function($){	
	//drowTable(curr_page,page_size);
	$("#pagename").text();
	
	
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
function next_page(){
	jQuery("#up_page").removeClass("prev_disabled");
	curr_page+=1;
	var keyword=jQuery("#keyword").val();
	console.log(curr_page+"&"+keyword);
}
function prev_page(){
	if(curr_page==2)jQuery("#up_page").addClass("prev_disabled");
	if(curr_page>1){
		curr_page-=1;
		jQuery("down_page").removeClass("next_disabled");
	}else {
		
	}
}
function drowTable(pageNo,pageSize){
	var table=jQuery("#meter_info");
	var seachword=jQuery("#keyword").val();
	if(seachword=="请输入关键字"||jQuery.trim(seachword)==""){
		seachword="";
	}
	console.log(seachword);
	jQuery.ajax({
		url:"admin/meter_page",
		type:"post",
		data:{"pageNo":pageNo,"pageSize":pageSize,"seachfor":seachword},
		dataType:"json",
		error:function(){
			alert("!!");
		},
		success:function(result){
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
