jQuery(document).ready(function($){	
	$(".headermenu li").removeClass('current');
	$(".headermenu li").eq(1).addClass('current');
	
	$("#contentwrapper").load("user/jumpin_modifyprice");
});

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
	jQuery("#pagedesc").text(">>>"+jQuery(el).text());
}
function modify_price(obj){
	modify_menu(obj);
	jQuery("#contentwrapper").load("user/jumpin_modifyprice");
}
function mng_user(obj){
	modify_menu(obj);
	jQuery("#contentwrapper").load("user/jumpin_mnguser");
}
function add_point(obj){
	modify_menu(obj);
	jQuery("#contentwrapper").load("user/jumpin_addbranch");
}
function add_meter(obj){
	modify_menu(obj);
	jQuery("#contentwrapper").load("user/jumpin_adduser");
}
function send_msg(obj){
	modify_menu(obj);
	jQuery("#contentwrapper").load("user/jumpin_sendmsg");
}
