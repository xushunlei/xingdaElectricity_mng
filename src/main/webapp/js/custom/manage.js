
jQuery(document).ready(function($){	
	$(".headermenu li").removeClass('current');
	$(".headermenu li").eq(1).addClass('current');
});
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
