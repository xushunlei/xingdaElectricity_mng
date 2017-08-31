<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var canSave=false;
jQuery(document).ready(function($){
	
});
function addZone(){
	checkMark();
	if(canSave){
		var formData=jQuery("#addForm").serialize();//序列化。自动封装成数组，后台可以直接用对象接收
		jQuery.ajax({
			url:"admin/addZone",
			type:"post",
			data:formData,
			dataType:"json",
			async:false,
			success:function(data){
				alert(data.result);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("添加失败！");
				console.log(XMLHttpRequest.status);
				console.log(XMLHttpRequest.readyState);
				console.log(textStatus);
			}
		});
	}
}
function checkMark(){
	var account=jQuery("#userAccount").val();
	if(account==null){
		jQuery("#accountTips").text("用户名不能为空！");
		console.log("1");
		return;
	}
	
	//后台校验唯一性……
	
	if(jQuery("#userName").val()==null){
		console.log("2");
		return;
	}
	var mobile=jQuery("#userMobile").val();
	var rex_phone=/^[\d]{5,20}$/;
	if(mobile==null){
		jQuery("#mobileTips").text("请填写手机号码");
		console.log("3");
		return;
	}else if(!rex_phone.test(mobile)){
		jQuery("#mobileTips").text("手机号码格式错误");
		console.log("4");
		return;
	}
	if(jQuery("#zoneName").val()==null){
		console.log("5");
		return;
	}
	canSave = true;
}
</script>
</head>
<body>
<div id="compose" class="subcontent">&nbsp;
	<center>
	<form action="admin/addZone" method="post" id="addForm">
		<table>
		<tr><td>管理员：</td><td><input name="userAccount" id="userAccount"></td></tr>
		<tr style="display:none;"><td></td><td id="accountTips" class="tips"></td></tr>
		<tr><td>工号：</td><td><input name="userCord"></td></tr>
		<tr><td>姓名：</td><td><input name="userName" id="userName"></td></tr>
		<tr><td>手机：</td><td><input name="userMobile" id="userMobile"></td></tr>
		<tr style="display:none;"><td></td><td id="mobileTips" class="tips"></td></tr>
		<tr><td>辖区名称：</td><td><input name="zoneName" id="zoneName"></td></tr>
		<tr><td>辖区描述：</td><td><input name="zoneDescription"></td></tr>
		<tr><td><input type="reset"></td><td><input type="submit">&nbsp;<input type="button" value="ajax提交" onclick="addZone();"></td></tr>
		</table>
	</form>
	</center>
</div>
</body>
</html>