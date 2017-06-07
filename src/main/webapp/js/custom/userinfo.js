jQuery(document).ready(function($){
	
});
function resetform(){
	jQuery("#username").val('');
	jQuery("#usermobile").val('');
	jQuery("#useridcard").val('');
	jQuery("#useremail").val('');
	jQuery("#useraddress").val('');
}
function saveuser(){
	if(confirm("确认修改？")==true){
		jQuery.ajax({
			url:"user/saveUser",
			data:jQuery("#modify_info").serialize(),
			dataType:"json",
			type:"post",
			success:function(result){
				if(result.flag==1){
					alert("修改成功");
				}else{
					alert("修改失败");
				}
			},
			error:function(e){
				alert("The service is boom");
			}
		})
	}
}