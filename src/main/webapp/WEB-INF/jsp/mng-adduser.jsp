<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
jQuery(document).ready(function($){
	
});
function upload_temple(){
	/* jQuery.ajax({
		url:"file/upload",
		
	}); */
	
	jQuery("#form1").submit();
}
</script>
</head>
<body>
<div id="newmetchdiv" class="subcontent">&nbsp;
	<div id="tree_machine">
		<form action="">
		<table>
			<tr>
				<td>*</td>
				<th>选择网点：</th>
				<td>
					<c:if test="${userRole==1}">
					<select id="zones" onchange="document.getElementById('zoneid').value=this.value" name="zoneId">
					<c:forEach items="${zoneList}" var="zone">
						<option value="${zone.zoneId}">${zone.zoneName}</option>
					</c:forEach>
					</select>
					</c:if>
					<c:if test="${userRole==0}">
					<span style="font-size: 10pt;color: darkblue;">${loginUser.userZone.zoneName}</span>
					<input type="hidden" name="zoneId" value="${loginUser.userZone.zoneId}">
					</c:if>
					<a href="javascript:;" onclick="upload_temple()">导入模版</a>&nbsp;
					<a href="file/A测试模版.xls">下载模版</a>
				</td>
			</tr>
			<!-- Controller中直接用参数Meter可以自动封装成对象 -->
			<tr>
				<td>*</td>
				<th>用户名称：</th>
				<td><input name="meterName"></td>
			</tr>
			<tr>
				<td></td>
				<th>用户描述：</th>
				<td><input name="meterDescription"></td>
			</tr>
			<tr>
				<td>*</td>
				<th>用户类型：</th>
				<td><input name="meterType"></td>
			</tr>
			<tr>
				<td>*</td>
				<th>通讯地址：</th>
				<td><input name="meterAddress"></td>
			</tr>
			<tr>
				<td>*</td>
				<th>波特率：</th>
				<td><input name="meterRate"></td>
			</tr>
			<tr>
				<td>*</td>
				<th>端口号：</th>
				<td><input name="meterPort"></td>
			</tr>
			<tr>
				<td>*</td>
				<th>户主：</th>
				<td><input name="meterUserName"></td>
			</tr>
			<tr>
				<td>*</td>
				<th>户主手机：</th>
				<td><input name="meterUserMobile"></td>
			</tr>
			<tr>
				<td>*</td>
				<th>身份证：</th>
				<td><input name="meterUserIdcard"></td>
			</tr>
			<tr>
				<td>*</td>
				<th>户主住址：</th>
				<td><input name="meterUserAddress"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="button" value="提交"></td>
				<td><input type="button" value="重置"></td>
			</tr>
		</table>
		</form>
	</div>
</div>

<div>
<form action="filer/upload" enctype="multipart/form-data" id="form1" method="post">
	<input type="hidden" id="zoneid" value="${zoneList[0].zoneId}" name="zoneid">
	选择文件：<input type="file" id="filePath" name="filePath" onchange="document.getElementById('textfield').value=this.value">
	<input type="submit" value="上传">
</form>
<input id="textfield">
</div>
</body>
</html>