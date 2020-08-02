<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Form表单</title>
<script type="text/javascript">
	var flag = false;//false表示为提交 true 就是已经提交
	function dosubmit() {
		//获取表单提交按钮
		var btnSubmit = document.getElementById("submit");
		//将表单提交按钮设置为不可用，这样就可以避免用户再次点击提交按钮
		//btnSubmit.disabled = "disabled";
		//返回true让表单可以正常提交
		return true;
	}
</script>
</head>

<body>
	<form action="DoFormServlet" method="post" onsubmit="return dosubmit()">
		<input type="hidden" name="parameterToken" value="${token}">
		用户名：<input type="text" name="userName"> <input type="submit"
			value="提交" id="submit">
	</form>


</body>
</html>
