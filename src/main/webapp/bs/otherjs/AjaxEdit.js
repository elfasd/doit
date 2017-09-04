/**
 * Ajax模式Edit页面支持JS
 */

var submitInProgress = false;
function AjaxEdit(selector) {
	this.selector = selector;
	this.rules = {};
	this.messages = {};
	this.targetUrl = null;
	this.beforeSubmit = null;
	this.afterSuccess = null;
	this.afterFailure = null;
}
AjaxEdit.prototype.bindForm = function() {
	
	var innerForm = $(this.selector);
	var innerFormTargetUrl = this.targetUrl;
	var innerFormValidRules = this.rules;
	var innerFormValidMessages = this.messages;
	var innerFormBeforeSubmit = this.beforeSubmit;
	var innerFormAfterSuccess = this.afterSuccess;
	var innerFormAfterFailure = this.afterFailure;
	//下面两个临时变量的值，用于callback中使用。
	var innerXMLHttpRequest="";
	var innerTextStatus="";
	var validData = {
		rules : innerFormValidRules,
		messages : innerFormValidMessages,

		ignore: "", 
		invalidHandler : function(e, validator) {
			var errors = validator.numberOfInvalids();
			if (errors) {
				var message = errors == 1 ? 'You missed 1 field. It has been highlighted below'
						: 'You missed ' + errors
								+ ' fields.  They have been highlighted below';
				$("div.error span").html(message);
				$("div.error").show();
			} else {
				$("div.error").hide();
			}
			;
		},
		errorPlacement : function(error, element) {
			error.css("color", "red").css("font-size","12px");
			var elem = $(element);
			if(elem.parent().attr("class").indexOf("input-group") != -1 || elem.parent().attr("class").indexOf("form_date") != -1){
				error.appendTo(elem.parent().parent());
			}else{
				error.appendTo(elem.parent());
			}
		},
		highlight: function(element, errorClass, validClass) { // element出错时触发
			$(element).addClass("alert-danger error");
		},
		unhighlight: function(element, errorClass) { // element通过验证时触发
			$(element).removeClass("alert-danger error");
		},
		success : $.noop,
		submitHandler : function() {
			if (submitInProgress) {
				return false;
			}
			if(innerFormBeforeSubmit!=null){ 
				innerFormBeforeSubmit(innerFormTargetUrl);
			}
			submitInProgress = true;
			$("div.error").hide();
//			alert(innerFormTargetUrl)
			$.ajax({
				// 提交数据的类型 POST GET
				type : "POST",
				// 提交的网址
				url : innerFormTargetUrl,
				// 提交的数据
				data : innerForm.serialize(),
				// 返回数据的格式
				datatype : "json",
				// "xml", "html", "script", "json", "jsonp", "text".
				// 在请求之前调用的函数
				beforeSend : function() {
					var msgOption = {
						message : "操作进行中，请稍候...",
						closeButton : false,
						animate : false,
						buttons : {},
					};
					bootbox.dialog(msgOption);
				},
				// 成功返回之后调用的函数
				success : function(data) {
					var result = eval(data);
					var messageText = "";
					var success = true;
					if(result) {
						if (result.status == "200") {
							messageText = '操作成功';
						} else {
							success= false;
							messageText = '操作失败：' + result.statusText;
						}
					}else {
						if (data == "success") {
							messageText = '操作成功';
						} else {
							success= false;
							messageText = '操作失败：' + data;
						}
					}

					var msgOption = {
						message : messageText,
						closeButton : false,
						animate : false,
						buttons : {
							close : {
								label : "确定",
								className : "btn-success",
								callback : function() {
									bootbox.hideAll();  
									if(success){
										if(innerFormAfterSuccess!=null){ 
											innerFormAfterSuccess(innerXMLHttpRequest, innerTextStatus,data);
										}
									}else{
										if(innerFormAfterFailure!=null){ 
										    innerFormAfterFailure(innerXMLHttpRequest, innerTextStatus,data);
										}
									}
									//重置complete设置的这两个变量
									innerXMLHttpRequest = null;
									innerTextStatus = null;
								}
							}
						}
					};
					bootbox.dialog(msgOption);
				},
				// 调用执行后调用的函数
				complete : function(XMLHttpRequest, textStatus) {
					//alert(XMLHttpRequest.responseText);
					//alert(textStatus);
					// HideLoading();
					submitInProgress = false;
					//调用成功后设置这两个临时变量的值，用于callback中使用。
					innerXMLHttpRequest=XMLHttpRequest;
					innerTextStatus = textStatus;
				},
				// 调用出错执行的函数
				error : function() {
				},
				clearForm : true,
			});
			return false;
		},
	// Odd workaround for errorPlacement not firing!
	};
	innerForm.validate(validData);
};
