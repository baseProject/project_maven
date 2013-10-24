<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="${basePath }"/>
	<title>信息后台管理系统</title>
	<link type="text/css" rel="stylesheet" href="<%=path %>/resources/css/css.css" />
	<!-- jQuery -->
	<script type="text/javascript" src="<%=path %>/resources/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/jquery.form.js"></script>
</head>
<body class="index">
<form id="form" action="<%=path %>/j_spring_security_check" method="post" onsubmit="return valid();">
    <div class="indexBg">
        <div class="indexNr">
            <p style="color:red;font-size: 15px;padding-bottom: 10px;" >${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</p> 
        	<input type="text" placeholder="请输入用户名" class="inputA"  id="j_username" name="j_username" value=""/>
            <input type="password" placeholder="请输入密码" class="inputA" id="j_password" name="j_password" value=""/>
            <input name="submit" type="submit" class="button" id="sumit" value=""/>
        </div>
        <div class="foot">
        	<p>基础项目</p>
        </div>
    </div>
</form>
<script type="text/javascript">
	$(function(){
		$("#codeNumb").focus();
		/**
		$('#password,#codeNumb').keyup(function(eventObject){
			if(13 == eventObject.keyCode){
				$("#loginform").submit();
			}
		});
		*/
	});
	function valid(){
			var codeNumb=$("#j_username").val();
			var password=$("#j_password").val();
			if(codeNumb==""){
				alert("请输入用户名!");
		        return false;
			}
			if(password==""){
				alert("请输入密码!");
				return false;
			}
			return true;
	}
</script>
</body>
</html>
