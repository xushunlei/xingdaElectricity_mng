
jQuery(document).ready(function($){	
	$(".headermenu li").removeClass('current');
	$(".headermenu li").eq(1).addClass('current');
	$("#usedate").bind("focus",function(){WdatePicker({
		minDate:"%y-%M-%d",
		isShowClear:false,
		readOnly:true
	});});
	$("#senddate").bind("focus",function(){WdatePicker({
		minDate:"%y-%M-%d",
		startDate:"%y-%M-%d",
		maxDate:"#F{$dp.$D('usedate')}",
		isShowClear:false,
		readOnly:true
	});});
	$("#price_form input:radio[name='sendable']").change(function(){
		if($("#price_form input:radio[name='sendable']:checked").val()==="1"){
			$(".sendcells").css("display","table-row");
		}else{
			$(".sendcells").css("display","none");
		}
	});
	/*jQuery("#tree_machine").window({
		modal:true
	});
	jQuery("#userlist").tree({
		url:"",
		queryParams:,//在请求远程数据的时候增加查询参数并发送到服务器
		checkbox:true,
		loadFilter:function(data){
			if (data.d){    
	            return data.d;    
	        } else {    
	            return data;    
	        }
		}
	})*/
});
/**修改电价*/
function modifyPrice(){
	jQuery.ajax({
		url:"admin/modifyPrice",
		data:jQuery("#price_form").serialize(),
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.flag){
				alert("修改成功");
				if(result.pt){
					jQuery("#show_price").text(result.p);
					jQuery("#show_start_date").text(result.ud);
				}
				jQuery("#recent_change_price").text(result.p);
				jQuery("#recent_change_date").text(result.ud);
			}else{
				alert("修改失败");
			}
		}
	});
}
/**管理消息组*/
function showTree(){
	
}
function modify_menu(el){
	jQuery("#leftmenu li").removeClass("current");
	jQuery(el).parent().addClass("current");
	jQuery("#leftmenu li a").removeClass("inbox");
	jQuery("#leftmenu li a").addClass("drafts");
	jQuery(el).removeClass("drafts");
	jQuery(el).addClass("inbox");
}
function modify_price(obj){
	modify_menu(obj);
}
function add_point(obj){
	modify_menu(obj);
}
function add_meter(obj){
	modify_menu(obj);
}
function send_msg(obj){
	modify_menu(obj);
}
