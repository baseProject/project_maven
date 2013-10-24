<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="${path}/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/jquery.form.js"></script>
<script type="text/javascript" src="${path}/resources/js/layer/layer.min.js"></script>
<style type="text/css">
<!--
form{
margin: 0px auto;
width: 95%;
border: solid 1px #CCCCCC;
}
 
.bo{
border-bottom: solid 1px #CCCCCC;
}
 
label{
float: left;
width:100px;
text-align: right;
padding: 10px 0px 4px 10px;
}
 
input{
padding: 1px;
}
 
input,textarea{
border: 1px solid #CCCCCC;
margin: 5px 0px;
}
 
textarea{
padding: 2px;
}
 
.bt{
width: 38px;
height: 20px;
font-size: 11px;
border: solid 1px #CCCCCC;
background: #FBFBFB;
text-align: center;
}
 
.btcenter{
text-align: center;
clear: left;
padding: 4px 0px 0px;
}
 
/*----下面是选中表单时的变化效果，大家可以自由发挥的---*/
.sffocus {
background: #F0F9FB; /*----for IE----*/
border: 1px solid #1D95C7;
}
 
textarea:focus, input:focus {
background: #F0F9FB; /*----for firefox......----*/
border: 1px solid #1D95C7;
}
 
body {
font-family: Arial, Helvetica, sans-serif;
font-size: 12px;
color: #666666;
margin-top: 20px;
}
-->
</style>
<title>添加用户</title>
<script type="text/javascript">
    var i = parent.layer.getFrameIndex(window.name);
    var index;
	function no(){
		parent.layer.close(i);
	}
	function add(){
		var options = {
				type : 'post',
				dataType : 'json',
				beforeSubmit : showRequest,
				success : showResponse,
				error : function() {
					parent.layer.close(index);
					alert('请求服务器失败,请检查网络!');
				}
			};
			$('#myform').ajaxSubmit(options);
	}
	function showRequest(formData, jqForm, options){
		var form = jqForm[0];
		var email = $.trim(form.email.value);
		if (!email) {
			alert('请输入邮箱!');
			return false;
		}
		if (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email) == false) {
            alert("邮箱格式不正确，请重新填写!");
            return false;
        }
		index=parent.layer.load(0);
		return true;
	}
	function showResponse(responseText, statusText, xhr, $form){
		var json = eval(responseText);
		parent.layer.close(index);
		if(!json.success){
			parent.layer.msg(json.msg);
			return false;
		}
		parent.layer.msg(json.msg,1,1);
		parent.location.reload();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
 <body>
<form action="${path}/user/ajaxAddUser.html" method="post" name="myform" id="myform">
<input type="hidden" name="id" value="${user.id }"/>
<div class="bo"><label for="loginName">登录名：</label>
<input type="text" size="30" name="loginName" id="loginName" maxlength="20" readonly="readonly" value="${user.loginName }"/></div>
<div class="bo"><label for="email">邮箱：</label>
<input type="text" size="30" name="email" id="email" maxlength="40" value="${user.email }"/></div>
<div class="bo"><label for="name">真实姓名：</label>
<input type="text" size="30" name="name" id="name" maxlength="20" value="${user.name }"/></div>
<div class="bo"><label for="gender">性别：</label>
<input type="radio" size="30" name="gender" id="gender_1" value="0" <c:if test="${user.gender==0 }">checked="checked"</c:if> />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio" size="30" name="gender" id="gender_2" value="1" <c:if test="${user.gender==1 }">checked="checked"</c:if> />女</div>
<div class="btcenter"><input type="button" class="bt" value="保存" onclick="add()"/>
<input type="button" class="bt" value="取消" onclick="no()"/>
</div>
</form>
<script language="javascript">
function suckerfish(type, tag, parentId) {
	if (window.attachEvent) {
		window.attachEvent("onload", function() {
			var sfEls = (parentId==null)?document.getElementsByTagName(tag):document.getElementById(parentId).getElementsByTagName(tag);type(sfEls);
		});
	}
}
sfFocus = function(sfEls) {
	for (var i=0; i<sfEls.length; i++) {
		sfEls[i].onfocus=function() {
		this.className+=" sffocus";
		}
		sfEls[i].onblur=function() {
			this.className=this.className.replace(new RegExp(" sffocusb"), "");
		}
	}
}
suckerfish(sfFocus, "INPUT");
suckerfish(sfFocus, "TEXTAREA");
</script>
</body>
</html>
