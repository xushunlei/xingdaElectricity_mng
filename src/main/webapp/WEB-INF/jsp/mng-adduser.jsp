<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div id="newmetchdiv" class="subcontent">&nbsp;
	<div id="tree_machine">
		<div id="seachdiv">
			<a href="javascript:;">下载模版</a>&nbsp;<a href="javascript:void(0);">导入模版</a>
		</div>
		<div id="userlist">
		<table>
			<tr><td>帐户名：</td><td><input name="newuseraccount"></td></tr>
			<tr><td>用户姓名：</td><td><input name="newusername"></td></tr>
			<tr><td>手机号码：</td><td><input name="newusermobile"></td></tr>
			<tr><td>身份证：</td><td><input name="newuseridcard"></td></tr>
			<tr><td>住址：</td><td><input name="newuseraddress"></td></tr>
		</table>
		</div>
		<div id="groupname">
		<table>
			<tr><td>电表描述：</td><td><input name="newmeternumber"></td></tr>
			<tr><td>类型：</td><td><input name="newmetertype"></td></tr>
			<tr><td>通讯地址：</td><td><input name="newmetercontact"></td></tr>
			<tr><td>波特率：</td><td><input name="newmeterrate"></td></tr>
			<tr><td>端口号：</td><td><input name="newmeterserial"></td></tr>
			<tr><td>所属网点：</td><td><select name="newmeterbranch"></select></td></tr>
		</table>
		</div>
	</div>
	<input type="button" onclick="showTree();" value="show">
</div>
</body>
</html>