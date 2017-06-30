jQuery(document).ready(function($){	
	///设置顶部导航样式///
	$(".headermenu li").removeClass('current');
	$(".headermenu li").eq(5).addClass('current');
	
	$("#innerpage").load("user/jumpin_dosagePage");
	
});
function modify_menu(el){
	jQuery("#leftmenu li").removeClass("current");
	jQuery(el).parent().addClass("current");
	jQuery("#leftmenu li a").removeClass("inbox");
	jQuery("#leftmenu li a").addClass("drafts");
	jQuery(el).removeClass("drafts");
	jQuery(el).addClass("inbox");
}
function show_dosage(obj){
	modify_menu(obj);
	jQuery("#innerpage").load("user/jumpin_dosagePage");
}
function show_price(obj){
	modify_menu(obj);
	jQuery("#innerpage").load("user/jumpin_pricePage");
}
function show_recharge(obj){
	modify_menu(obj);
	jQuery("#innerpage").load("user/jumpin_rechargePage");
}