
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
	
});
function modifyPrice(){
	jQuery.ajax({
		url:"admin/modifyPrice",
		data:jQuery("#price_form").serialize(),
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.flag){
				jQuery("#recent_change_price").text(result.p);
				jQuery("#recent_change_date").text(result.ud);
			}else{
				alert("修改成功");
			}
		}
	});
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
