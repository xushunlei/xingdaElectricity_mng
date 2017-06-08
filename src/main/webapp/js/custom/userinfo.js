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
function clean_newpwd(){
	jQuery("#old_pwd").val("");
	jQuery("#new_pwd").val("");
	jQuery("#verify_pwd").val("");
	jQuery("#oldtips").val("");
	jQuery("#newtips").val("");
	jQuery("#verifytips").val("");
}
function canChange(){
	var old=jQuery("#old_pwd").val();
	if(old==null||old==""){
		jQuery("#oldtips").text("*请先输入原密码！");
		return false;
	}
	var first=jQuery("#new_pwd").val();
	if(first==null||first==""){
		jQuery("#newtips").text("*密码不能设为空！");
		return false;
	}else if(first==old){
		jQuery("#newtips").text("*新密码不能与原密码相同！");
		return false;
	}
	var second=jQuery("#verify_pwd").val();
	if(second==null||second==""||second!=first){
		jQuery("#verifytips").text("*两次密码不符！");
		return false;
	}
	return true;
}
function change_password(){
	if(canChange()){
		var pwd=jQuery("#new_pwd").val();
		var uname=jQuery("#account").text();
		jQuery.ajax({
			url:"user/modifyPwd",
			type:"post",
			data:{"account":uname,"password":pwd},
			dataType:"json",
			success:function(flag){
				console.log(flag);
				if(flag){
					alert("修改成功，新密码将在下次登陆时使用！");
					
				}
			},
			error:function(e){
				alert("修改失败");
			}
		});
		
	}
}
