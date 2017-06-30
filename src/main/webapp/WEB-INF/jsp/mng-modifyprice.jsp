<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
jQuery(document).ready(function($){
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
/**修改电价*/
function modifyPrice(){
	jQuery.ajax({
		url:"admin/modifyPrice",
		data:jQuery("#price_form").serialize(),
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.flag){
				alert("修改成功");
				if(result.pt){
					jQuery("#show_price").text(result.p);
					jQuery("#show_start_date").text(result.ud);
				}
				jQuery("#recent_change_price").text(result.p);
				jQuery("#recent_change_date").text(result.ud);
			}else{
				alert("修改失败");
			}
		}
	});
}
</script>
</head>
<body>
<div id="inbox" class="subcontent">
	<center>
	<form id="price_form">
		<table>
			<tr><td>当前电价：</td><td><span id="show_price">${currPrice.price }</span>元/度</td><td>*自<span id="show_start_date" >${currPrice.startDateStr }</span>起计费</td></tr>
			<tr><td>最近修改：</td><td><span id="recent_change_date">${futurePrice.startDateStr }</span>起，电价改为：<span id="recent_change_price">${futurePrice.price }</span>元/度</td></tr>
			<tr><td>修改电价：</td><td><input style="width:40px;padding:0" name="new_price">元/度</td></tr>
			<tr><td>启用时间：</td><td><input id="usedate" class="Wdate" name="up_date"></td></tr>
			<tr><td>短信通知</td><td><label><input type="radio" name="sendable" value="1">是</label><label><input type="radio" name="sendable" value="0" checked="checked">否</label></td></tr>
			<tr class="sendcells"><td>发送时间：</td><td><input id="senddate" class="Wdate" name="send_date"></td></tr>
			<tr class="sendcells"><td>短信内容：</td><td colspan="2"><textarea rows="6" cols="" name="send_content" style="resize:none"></textarea></td></tr>
			<tr><td></td><td><input type="button" value="确认" onclick="modifyPrice()"></td></tr>
		</table>
	</form>
	</center>
</div>
</body>
</html>