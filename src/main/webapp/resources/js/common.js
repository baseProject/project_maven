
var Block = {
	blockUI : function() {
		$.blockUI({
			message : '<h2>处理中,请稍候...</h2>',
			css : {
				border : '3px solid #a00'
			}
		});
	},
	unblockUI : function() {
		setTimeout($.unblockUI, 500);
	}
};

var Dialog = {
	openDialog : function(contentId, top, left) {
		var _top = top;
		var _left = left;
		if(!top){
			_top = ($(window).height() - $("#" + contentId).height())/ 2;
		}
		if(!left){
			_left = ($(window).width() - $("#" + contentId).width()) / 2;
		}
		$.blockUI({
			message : $("#" + contentId),
			css : {
				top : _top + 'px',
				left :  _left + 'px',
				width : 'auto',
				cursor : 'default'
			},
			fadeIn : 50,
			fadeOut : 50
		});
		$('.blockOverlay').click($.unblockUI);
	},
	closeDialog : function() {
		$.unblockUI({
			fadeOut : 100
		});
	}
};

function ajaxRequest(url, params, func, errorFunc) {
	$.ajax({
		type : "POST",
		url : url,
		cache : false,
		data : params,
		success : function(result) {
			var success = result.success;
			var data = result.data;
			var msg = result.msg;

			if (success || success == "true") {
				func(msg, data);
			} else {
				errorFunc(msg, data);
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText);
		}
	});
}

function ajaxSubmit(form, callFunc, validationfunc, errorFunc) {
	var option = new Object();
	option.beforeSubmit = function(formData, jqForm, options) {
		var isOK = true;
		if (validationfunc != null && validationfunc != undefined) {
			isOK = validationfunc(formData, jqForm, options);
		}
		return isOK;
	};

	option.success = function(result) {
		if (!isJOSN(result)) {
			result = eval("(" + result + ")");
		}
		var success = result.success;
		var data = result.data;
		var msg = result.msg;

		if (success || success == "true") {
			callFunc(msg, data);
		} else {
			errorFunc(msg, data);
		}
	};

	$(form).ajaxSubmit(option);
	
	return false;
}

function isJOSN(obj) {
	var isjson = typeof (obj) == "object"
			&& Object.prototype.toString.call(obj).toLowerCase() == "[object object]"
			&& !obj.length;
	return isjson;
}

function trimEmptyStr(obj, defaultStr) {
	if (!obj)
		return defaultStr?defaultStr:'';
	return $.trim(obj);
}

function checkAll() {
	if ($(":checkbox[class=check-all]").attr("checked") == undefined
			|| $(".check-all").attr("checked") == ""
			|| $(".check-all").attr("checked") == false) {
		$(":checkbox[class=check-one]").attr("checked", false);
	} else {
		$(":checkbox[class=check-one]").attr("checked", true);
	}
}

function bak_getSelectData(){
	var ids = $('input[type=checkbox][class=check-one]:checked');
	if(ids.length == 0){
		return '';
	}
	var data = '';
	$(ids).each(function(index,value){
		if(index == 0){
			data = $(this).val();
		} else {
			data = $(this).val()+',' + data;
		}
	});
	return data;
}

function getSelectDataAsArray(){
	var ids = $('input[type=checkbox][class=check-one]:checked');
	var data=new Array();
	$(ids).each(function(index,value){
		data.push($(this).val());
	});
	return data;
}