jQuery(document).ready(function($){	
	$(".headermenu li").removeClass('current');
	$(".headermenu li").eq(1).addClass('current');
	
	$("#contentwrapper").load("user/jumpin_modifyprice");
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
