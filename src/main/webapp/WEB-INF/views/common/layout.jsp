<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="${basePath}" />
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<title>后台管理系统 <decorator:title default="MyTitle" /></title>
<link rel="stylesheet" href="${path}/resources/css/css.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${path}/resources/css/style.css" type="text/css" media="screen" />

<script type="text/javascript">
	path = '${path}';
	basePath = '${basePath}';
</script>
<!-- jQuery -->
<script type="text/javascript" src="${path}/resources/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${path}/resources/js/jquery.form.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="${path}/resources/js/simpla.jquery.configuration.js"></script>
<script type="text/javascript" src="${path}/resources/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="${path}/resources/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/common.js?v=1"></script>


<script type="text/javascript">
	$(function() {
	    var x=window.location.href.indexOf(window.location.host);
	    var y=window.location.host.length;
	    var url=window.location.href.substring(x+y);
	    $('dd a[href="'+url+'"]').parent().css("background","#FFFFFF");
	    var dds=$('dd a[href="'+url+'"]').parent().parent().children("dd");
	    dds.toggle();
		var _height = $(window).height(), _aheight = $(".head").outerHeight(
				true), _bheight = _height - _aheight;
		$(".main , .mainL, .mainR").height(_bheight - 1);

		$(".mainL dt").click(function() {
			$(this).nextAll().toggle();
		})
		var date=new Date();
		var zhou;
		if(date.getDay()==0){
			zhou="日";
		}else if(date.getDay()==1){
			zhou="一";
		}else if(date.getDay()==2){
			zhou="二";
		}else if(date.getDay()==3){
			zhou="三";
		}else if(date.getDay()==4){
			zhou="四";
		}else if(date.getDay()==5){
			zhou="五";
		}else if(date.getDay()==6){
			zhou="六";
		}
		$(".time").html(date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"号 周"+zhou);
	});
	function updatePass(id){
		$.layer({
		    type : 2,
		    title : '修改密码',
		    iframe : {src : '${path}/user/toUpdatePass.html?id='+id},
		    area : ['450px' , '266px'],
		    offset : ['100px',''],
		});
	}
</script>
<decorator:head />
</head>

<body>
	<div class="head">
		<div class="headTop">
			<div class="logo">
				<img src="${path}/resources/images/logo.jpg" width="120" height="50">
			</div>
			<div class="time">2013年07月10日 周三</div>
			<div class="fr">
				<div class="who">登录名：${SPRING_SECURITY_CONTEXT.authentication.principal.username}
				|<a href="javascript:;" onclick="updatePass(${SPRING_SECURITY_CONTEXT.authentication.principal.user.id})">修改密码</a></div>
				<div class="off"><a href="${path}/j_spring_security_logout">注销</a></div>
			</div>
		</div>
		<div class="nav">
		<!-- 	<ul>
				<li class="curr"><a href="#">系统管理</a></li>
				<li><a href="#">内容管理</a></li>
				<li><a href="#">用户管理</a></li>
				<li><a href="#">广告管理</a></li>
				<li><a href="#">荣誉管理</a></li>
				<li><a href="#">页面管理</a></li>
			</ul> -->
		</div>
	</div>
	<div class="main">
		<div class="mainL">
			<dl>
				<dt>系统管理</dt>
				<dd>
					<a href="${path}/user/list.html">用户管理</a>
				</dd>
				<dd>
					<a href="${path }/role/list.html">角色管理</a>
				</dd>
				<dd>
					<a href="${path }/resources/list.html">资源管理</a>
				</dd>
			</dl>
		</div>
		<div class="mainR">
			<div class="content">
				<decorator:body />
			</div>
		</div>
	</div>
</body>
</html>