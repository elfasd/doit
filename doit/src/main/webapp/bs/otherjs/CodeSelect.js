/**
 * Code Input
 */
/** vars for codechange */ 
var codeSelectField = null;
var codeSelectFieldValue = null;
var codeSelectCodeMethod = null;
var codeSelectCodeType = null;
var codeSelectCodeRelation = null;
var codeSelectIsClear = null;
var codeSelectRealCondition = null;
var codeSelectTypeParam = null;
var codeSelectCallBackMethod = null;
var codeSelectGetDataMethod = null;
var codeSelectElementOrder = 0;
var codeSelectElementLength = 1;

var codeSelectHasSubmit = false;


var fun_codeSelect_onSelected = null;

/** 用于保存挂接到Tab标签的正在执行code_CodeChange方法的输入域 */
var $onchangeArray = new Array();
 
/**
 * prepare select data,for codeinput
 * @param field
 * @param codeType
 * @param codeRelation split by ","
 * @param isClear
 * @param otherCondition mode(key=value,key=value,key=fm.policyNo.value,key=fm.itemkindNo[].value)
 * @param callBackMethod callback
 * @param getDataMethod get data method,when codeType is custom
 */
function code_CodeSelect(field, codeType, codeRelation, isClear, otherCondition, typeParam, varParamField, callBackMethod, getDataMethod) {
	if (event.type == "keyup") {
        var charCode = window.event.keyCode;
        if (!(charCode == 13 & window.event.ctrlKey)) {
            return;
        }
    }
    if(varParamField != undefined && trim(varParamField)!= ""){
    	var woundCode  = document.getElementsByName(varParamField)[0].value;
    	//typeParam = getWoundType(trim(woundCode));
    }
    inCodeQuery = true; 
    private_Code_CallServiceByDialog(field, "select", codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod);
    inCodeQuery = false;
}

/**
 * prepare query data,for codequery,can select many value
 * @param field
 * @param codeType
 * @param codeRelation split by ","
 * @param isClear
 * @param otherCondition mode(key=value,key=value,key=fm.policyNo.value,key=fm.itemkindNo[].value)
 * @param callBackMethod callback
 * @param getDataMethod get data method,when codeType is custom
 */
function code_CodeQuery(field, codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod) {
    if (event.type == "keyup") {
        var charCode = window.event.keyCode;
        if (!(charCode == 13 & window.event.ctrlKey)) {
            return;
        }
    }
    inCodeQuery = true; 
    private_Code_CallServiceByDialog(field, "query", codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod);    
    inCodeQuery = false;
}

/**
 * only for parse params,set the value into public vars.
 */
function private_Code_ParseParams(field, codeMethod, codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod) {
    var elementOrder = getElementOrder(field)-1;
    codeSelectElementLength=getElementCount(field.name);    
    codeSelectElementOrder=elementOrder;
    var fieldIndex = 0;//getElementIndexInForm(document.forms[0], field); 
    var fieldValue = field.value;
    if (fieldValue != null) {
        fieldValue = fieldValue.replace("*", "%");
    }
    var relations = new Array();
    if (codeRelation.indexOf(",") > -1) {
        relations = codeRelation.split(",");
    } else {
        relations[0] = codeRelation;
    }
//    if (isClear == "Y"||isClear == "y") {
//        var relationsCount = relations.length;
//        for (var i = 0; i < relationsCount; i++) {
//            try {
//                var field = null;         
//                var relation = parseInt(relations[i], 10);
//                if(isNaN(relation)){ 
//                    field = eval("document.forms[0]."+relations[i]);
//                    if(field.length>1){
//                        field = field[elementOrder];
//                    }
//                }else{
//                    field = document.forms[0].elements[fieldIndex + relation];
//                }                             
//                field.value="";
//            }
//            catch (E) {
//            }
//        }
//    } 
    var conditions = new Array();
    if(otherCondition==null || otherCondition==undefined ||  otherCondition=="null"){
      otherCondition="";
    } 
    if (otherCondition.indexOf(",") > -1) {
        conditions = otherCondition.split(",");
    } else {
        conditions[0] = otherCondition;
    } 
    var conditionsCount = conditions.length;
    var realCondition = "";  
    for (var i = 0; i < conditionsCount; i++) { 
    	  if(conditions[i]==null||conditions[i]==""){
            continue;
        }
        var equalPos = conditions[i].indexOf("=");
        var condName = "";
        var condStatement = conditions[i];
        if(equalPos>0){
          condName = conditions[i].substring(0,equalPos) + "=";
          condStatement = conditions[i].substring(equalPos+1);
        }
        var condValue = "";
        if(condStatement.indexOf("[]")==-1){
            try{
                if(condStatement.indexOf(".value")>-1){
                  condValue = eval(condStatement);
                }else{
                  condValue = condStatement;
                }
            }catch (E) {
                condValue = condStatement;
            }
        }else{
            var startPos = condStatement.indexOf("[");
            var endPos = condStatement.indexOf("]");
            if(startPos==endPos-1){
                condStatement = condStatement.substring(0,startPos+1) + elementOrder + condStatement.substring(endPos);
            }
            try{
                condValue = eval(condStatement);
            }catch (E) {
                condValue = condStatement;
            }
        }
        realCondition += condName + condValue;
        if(i<conditionsCount-1){
            realCondition+=",";
        }
    }
 
    if(isClear==undefined || isClear=="null"){
        isClear="Y";
    }
    if(typeParam==undefined || typeParam=="null"){
        typeParam="";
    } 
    if(callBackMethod==undefined || callBackMethod=="null"){
        callBackMethod="";
    }
    if(getDataMethod==undefined || getDataMethod=="null"){
        getDataMethod="";
    } 
    codeSelectField= field;
    codeSelectFieldValue=fieldValue;
    codeSelectCodeMethod= codeMethod;
    codeSelectCodeType = codeType;
    codeSelectCodeRelation= codeRelation;
    codeSelectIsClear= isClear;
    codeSelectRealCondition = realCondition;
	if(codeMethod == "change") {
		codeSelectRealCondition = codeSelectRealCondition;
		codeSelectFieldValue = codeSelectFieldValue;
	}
    codeSelectTypeParam = typeParam;
    codeSelectCallBackMethod=callBackMethod;
    codeSelectGetDataMethod=getDataMethod;
}

function private_Code_CallServiceByDialog(field, codeMethod, codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod) {
    private_Code_ParseParams(field, codeMethod, codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod);
	var url = contextRootPath + "/common/PrepareCodeInput.do";
    var currentRiskCode = "";
	var currentClassCode = "";
	var comCode = "";
//    if(window.parent){
//    	var headFrame = window.parent.frames["head"];
//    	if(headFrame){
//    		currentRiskCode = headFrame.document.getElementById("riskCode").value;
//    		currentClassCode = headFrame.document.getElementById("classCode").value;
//			comCode = headFrame.document.getElementById("tempComCode").value;
//    	}
//    }
	
	/* RLIUBIN2014041503_02 标的地址录入界面改造  add by sunlei 2014-06-20 start */
//	if(trim(codeType) == "CountryOrDistrictCode" || trim(codeType) == "PostCode"){
//		//报批riskCode特殊处理
//	    var conditions = new Array();
//		//'riskCode=QBB,otherCondition=from_bp_flag'
//	    if(otherCondition && otherCondition.indexOf(",") > -1){
//	        conditions = otherCondition.split(",");
//	    } 
//		if(conditions && conditions.length >= 2){
//			if(conditions[1].indexOf("=") > -1){
//				var conditions2 = conditions[1].split("=");
//				if(conditions2 && conditions2.length >= 2){
//					var from_bp_flag = conditions2[1];
//					if("from_bp_flag" == trim(from_bp_flag)){
//						var conditions1 = conditions[0].split("=");
//						if(conditions1 && conditions1.length >= 2){
//							currentRiskCode =  conditions1[1];
//						}
//					}
//				}
//			}
//		}
//		//获取开关信息
//		var params = {
//			"comCode" : comCode,
//			"riskCode" : currentRiskCode
//		}
//		var postDetalllSwitch = invokeDwrMethod("PrpinsDwrUtil.getPostDetalllSwitch", params);
//		if(postDetalllSwitch){
//			var canOpen = false;
//			//国内邮编直接open新查询
//			if("PostCode" == codeType){
//				canOpen = true;
//			} 
//			
//			//否则判断是否选择了国内，是则open新查询
//			else {
//				if(trim(field.value) != "" && trim(field.value) != "02"){
//					canOpen = isNumber(trim(field.value)).flag;
//				}
//			}
//			if(canOpen){
//				var postCodeInputObj = field;
//				//bug31128 JBW/JBV，投保单录入，关系人信息，输入‘联系邮编’，系统报JS错 modify by sunlei 2014-07-18
//			 	var reg = /^(addressInfos)(\[\d*\])\.(prpCaddress|prpSaddress)\.(postCode)$/;
//			 	if (reg.test(postCodeInputObj.name)) {
//					var addressNameInputObj = document.getElementById(field.id.replace("postCode","addressName"));
//					var postCodeObject = {};
//					postCodeObject.postCode = postCodeInputObj.value;  
//					var returnValue = window.showModalDialog("/prpins/prpins/policy/common/other/UIPoEnPostCodeEdit.jsp",postCodeObject,"resizable:no;dialogHide:yes;help:no;status:no;scroll:yes;dialogWidth:600px;dialogHeight:600px");
//					//点击确定或者取消按钮
//					if(returnValue){
//						if(returnValue.eventName){
//							var postCode = returnValue.postCodeInput;
//							var addressName =  returnValue.addressFirstInput + returnValue.addressLastInput;
//							postCodeInputObj.value = postCode;
//							addressNameInputObj.value = addressName;
//							postCodeInputObj.readOnly = true;
//							addressNameInputObj.readOnly = true;
//						} else {
//							//第一级查询清掉邮编信息
//							if(postCodeInputObj.value.length == 2){
//								postCodeInputObj.value = "";
//								addressNameInputObj.value = "";
//								postCodeInputObj.readOnly = false;
//								addressNameInputObj.readOnly = false;
//							}
//						}
//					} else {
//						//第一级查询清掉邮编信息
//						if(postCodeInputObj.value.length == 2){
//							postCodeInputObj.value = "";
//							addressNameInputObj.value = "";
//							postCodeInputObj.readOnly = false;
//							addressNameInputObj.readOnly = false;
//						}	
//					}
//					return;
//			 	}
//			}
//		}
//	}
	/* RLIUBIN2014041503_02 标的地址录入界面改造  add by sunlei 2014-06-20 end */
	currentRiskCode = "ZIS";
	currentClassCode = "10";
	comCode = "32000000";
    var obj = new Object();  
    window.prototype=obj;
    obj.pageNo="1";
    obj.pageSize="50";
    obj.fieldValue=codeSelectFieldValue;
    obj.codeMethod=codeSelectCodeMethod;
    obj.codeType=codeSelectCodeType;
    obj.codeRelation=codeSelectCodeRelation;
    obj.isClear=codeSelectIsClear;
    obj.otherCondition=codeSelectRealCondition;
    obj.typeParam=codeSelectTypeParam;
    obj.callBackMethod=codeSelectCallBackMethod;
    //AgencyCode--TPA机构代码
    if(codeType=="BrandName"||codeType=="FamilyName"||codeType=="VehicleYear" 
    	||codeType=="VehicleExhaust"||codeType=="TransmissionType"||codeType=="Remark"
    	||codeType=="VehicleName"||codeType=="mainTainBrandName"||codeType=="AgencyCode"
    	||codeType=="MainTainComLP"){
    	 obj.getDataMethod="local";
    }else{
    	obj.getDataMethod=codeSelectGetDataMethod;
    }
    obj.elementOrder=codeSelectElementOrder;
    obj.elementLength=codeSelectElementLength;
    obj.currentRiskCode = currentRiskCode;
    obj.currentField=field;
    //RWANGY2015090824 项目配置中所有涉及双击域弹出对话框扩大，展示更多内容，优化处理。 mod wangxiaoye 2015111901
    var returnValue = window.showModalDialog(url,window,"resizable:yes;dialogHide:yes;help:no;status:no;scroll:yes;dialogWidth:400px;dialogHeight:435px");
    try {
        field.focus();
    }
    catch (E) { }
    /**根据条件来判断是否执行回调函数 start modify by liyang 2010-09-07 */
    /**
     * 如果typeParam为0，则只有在点击确定的时候才执行回调方法
     * 如果typeParam为1，则只有在点击确定或取消的时候才执行回调方法
     * 如果typeParam为空，则都会执行回调方法
     */
    //如果typeParam为"0"（选择性执行）并且页面的返回值不是"确定"，则不执行回调方法，否则就执行
    if(typeParam == "0" && returnValue != "确定"){
    	return;
    } else if(typeParam == "1") {
    	if(returnValue != "确定" && returnValue != "取消") {
    		//点击的是直接关闭窗口的“X”按钮
    		return;
    	}
    }
    /**根据条件来判断是否执行回调函数 end modify by liyang 2010-09-07 */
    //do after window close
    private_Code_CallBack(codeSelectCallBackMethod); 
    /** 清空非空校验时添加的红色背景 start add by liyang 2010-10-12 */
	if(field.value != ""){
		//清空非空校验时添加的红色背景
	//	clearPreErrFieldBg(field);
	}
	/** 清空非空校验时添加的红色背景 end add by liyang 2010-10-12*/
}

/**
 * 功能：根据调用code_CodeChange的输入域获取页面tab标签名称group
 * 参数：fieldObj:执行code_CodeChange的输入域
 * 返回：group   :对应的页面tab标签名称(如果返回未定义则返回"")
 * 作者：李阳
 * 时间：2013-12-27
 */ 
function getChangeFieldGroup(fieldObj) {
	var group = "";
	var fieldName = fieldObj.id || fieldObj.name || "";
	if (fieldName.indexOf("inputItemKind.prpCitemKind") != -1) {
		group = "ItemKind";
	}
	return group;
}

/**
 * 功能：将处于调用code_CodeChange的输入域放入到一个$onchangeArray中
 * 参数：fieldObj:执行code_CodeChange的输入域
 * 作者：李阳
 * 时间：2013-12-27
 */
function addChangeFieldToGroup(fieldObj) {
	var group = getChangeFieldGroup(fieldObj);
	if (group == "") {
		return;
	}
	
	var index = -1;
	for (var i = 0; i < $onchangeArray.length; i++) {
		var obj = $onchangeArray[i];
		if (obj.id == group) {
			index = i;
			break;
		} 
	}
	if (index > -1) {
		var currentObj = $onchangeArray[index];
		currentObj.valueList.push(fieldObj);
	} else {
		var newObj = {
			"id" : group,
			"valueList" : [fieldObj]
		}
		$onchangeArray.push(newObj);
	}
}

/**
 * 功能：将调用code_CodeChange的完毕的输入域从$onchangeArray中移除
 * 参数：fieldObj:执行code_CodeChange的输入域
 * 作者：李阳
 * 时间：2013-12-27
 */
function removeChangeFieldFormGroup(fieldObj) {
	var group = getChangeFieldGroup(fieldObj);
	if (group == "") {
		return;
	}
	
	for (var i = 0; i < $onchangeArray.length; i++) {
		var obj = $onchangeArray[i];
		if (obj.id == group) {
			var valueList = obj.valueList;
			for (var j = 0; j < valueList.length; j++) {
				if (valueList[j] == fieldObj) {
					valueList.splice(j, 1);
					return;
				}
			}
		} 
	}
}

/**
 * code input for data change
 * @param field
 * @param codeType
 * @param codeRelation split by ","
 * @param isClear
 * @param otherCondition mode(key=value,key=value)
 * @param callBackMethod callback
 * @param getDataMethod get data method,when codeType is custom
 */
function code_CodeChange(field, codeType, codeRelation, isClear, otherCondition, typeParam, varParamField,callBackMethod, getDataMethod) {
    var codeMethod = "change";
	// 将当前调用code_CodeChange方法的输入域放到array中
	addChangeFieldToGroup(field);
	
    if(varParamField != undefined && trim(varParamField)!= ""){
    	var woundCode  = document.getElementsByName(varParamField)[0].value;
    	typeParam = getWoundType(trim(woundCode));
    }
    private_Code_ParseParams(field, codeMethod, codeType, codeRelation, isClear, otherCondition, typeParam, callBackMethod, getDataMethod);
   	/** 如果输入域的值为空，则不需要再进行后台翻译了，直接清空所有相关输入域即可 start modify by wangzhifu 2010-07-07 */
    if(trim(field.value) == '') {
    	clearInputsByCodeRelation(field);
    	// 调用完毕后将输入域从map中移除
    	removeChangeFieldFormGroup(field);
    	return;
    }
    /** 如果输入域的值为空，则不需要再进行后台翻译了，直接清空所有相关输入域即可 end modify by wangzhifu 2010-07-07 */
   
    var url = contextRootPath + "/common/changeCodeInput.do";
    var currentRiskCode = "";
	var comCode = "";
    if(window.parent){
    	var headFrame = window.parent.frames["head"];
    	if(headFrame){
    		currentRiskCode = headFrame.document.getElementById("riskCode").value;
			comCode = headFrame.document.getElementById("tempComCode").value;
    	}
    }
	
	/* RLIUBIN2014041503_02 标的地址录入界面改造  add by sunlei 2014-06-20 start */
	//onchange如果输入的数字，直接清空，只能通过双击查询获取国内邮编
//	if(trim(codeType) == "CountryOrDistrictCode" || trim(codeType) == "PostCode"){
//		//报批riskCode特殊处理
//	    var conditions = new Array();
//		//'riskCode=QBB,otherCondition=from_bp_flag'
//	    if(otherCondition && otherCondition.indexOf(",") > -1){
//	        conditions = otherCondition.split(",");
//	    } 
//		if(conditions && conditions.length >= 2){
//			if(conditions[1].indexOf("=") > -1){
//				var conditions2 = conditions[1].split("=");
//				if(conditions2 && conditions2.length >= 2){
//					var from_bp_flag = conditions2[1];
//					if("from_bp_flag" == trim(from_bp_flag)){
//						var conditions1 = conditions[0].split("=");
//						if(conditions1 && conditions1.length >= 2){
//							currentRiskCode =  conditions1[1];
//						}
//					}
//				}
//			}
//		}
//		//获取开关信息
//		var params = {
//			"comCode" : comCode,
//			"riskCode" : currentRiskCode
//		}
//		var postDetalllSwitch = invokeDwrMethod("PrpinsDwrUtil.getPostDetalllSwitch", params);
//		if(postDetalllSwitch){
//			var isNumberFlag = false;
//			if(trim(field.value) != ""){
//				isNumberFlag = isNumber(trim(field.value)).flag;
//			}
//			if(isNumberFlag){
//				//bug31128 JBW/JBV，投保单录入，关系人信息，输入‘联系邮编’，系统报JS错 modify by sunlei 2014-07-18
//				var reg = /^(addressInfos)(\[\d*\])\.(prpCaddress|prpSaddress)\.(postCode)$/;
//				if (reg.test(field.name)) {
//					field.value = "";
//					// 调用完毕后将输入域从map中移除
//	    			removeChangeFieldFormGroup(field);
//					return;
//				}
//			}
//		}
//	}
	
    var pars="actionType=query";
    pars=pars+"&field="+codeSelectField;
    pars=pars+"&fieldValue="+codeSelectFieldValue;
    pars=pars+"&codeMethod="+codeSelectCodeMethod;
    pars=pars+"&codeType="+codeSelectCodeType;
    pars=pars+"&codeRelation="+codeSelectCodeRelation;
    pars=pars+"&isClear="+codeSelectIsClear;
    pars=pars+"&otherCondition="+codeSelectRealCondition;
    pars=pars+"&typeParam="+codeSelectTypeParam;
    pars=pars+"&callBackMethod="+codeSelectCallBackMethod;
 if(codeType=="mainTainBrandName"){
	 pars=pars+"&getDataMethod=local";
    }else{
     pars=pars+"&getDataMethod="+codeSelectGetDataMethod;
    }
    pars=pars+"&riskCode="+"ZIS";
    pars=pars+"&pageNo=1";
    pars=pars+"&pageSize=50";
    var originalParams = new Object();
	originalParams["codeSelectField"]=codeSelectField;
	originalParams["codeSelectFieldValue"]=codeSelectFieldValue;
	originalParams["codeSelectCodeMethod"]=codeSelectCodeMethod;
	originalParams["codeSelectCodeType"]=codeSelectCodeType;
	originalParams["codeSelectCodeRelation"]=codeSelectCodeRelation;
	originalParams["codeSelectIsClear"]=codeSelectIsClear;
	originalParams["codeSelectRealCondition"]=codeSelectRealCondition;
	originalParams["codeSelectTypeParam"]=codeSelectTypeParam;
	originalParams["codeSelectCallBackMethod"]=codeSelectCallBackMethod;
	originalParams["codeSelectGetDataMethod"]=codeSelectGetDataMethod;
	//调整双击域js错 暂时注掉
	//alert("id is :"+originalParams["fieldId"]);
	
	
//    var myAjax = new Ajax.Request(
//       url,{method:'post',asynchronous:'false',parameters:pars,onComplete:setFieldValueForCodeChange,originalPars:originalParams}
//    );
//    
    $.ajax({
		type : "POST",
		url : url,
		data :pars,
		async : true,
		dataType: "json",
		success : function(msg) {
			if(msg != ""){
				msg = msg.substring(1,msg.length-1);
				setFieldForCodeChange(msg,originalParams);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
			alert(textStatus + errorThrown);
			if(textStatus!=200){
		    	errorMessage("双击输入框数据查询出错!");
		    	currentField.value = "";
		    	// 调用完毕后将输入域从map中移除
				removeChangeFieldFormGroup(currentField);
		    	return;
			}
		}
	}); 
}
function setFieldForCodeChange(msg,originalParams){
	
	var codeSelectField= originalParams["codeSelectField"];
	var codeSelectFieldValue=originalParams["codeSelectFieldValue"];
	var codeSelectCodeMethod=originalParams["codeSelectCodeMethod"];
	var codeSelectCodeType=originalParams["codeSelectCodeType"];
	var codeSelectCodeRelation=originalParams["codeSelectCodeRelation"];
	var codeSelectIsClear=originalParams["codeSelectIsClear"];
	var codeSelectRealCondition=originalParams["codeSelectRealCondition"];
	var codeSelectTypeParam=originalParams["codeSelectTypeParam"];
	var codeSelectCallBackMethod=originalParams["codeSelectCallBackMethod"];
	var codeSelectGetDataMethod=originalParams["codeSelectGetDataMethod"];
	
	var currentField = codeSelectField;
    var inputValue = currentField.value;
    var elementOrder = getElementOrder(currentField)-1;
    var value = msg; 
    var values = [];
    if (value.indexOf(FIELD_SEPARATOR) > -1) {
        values = value.split(FIELD_SEPARATOR);
    } else {
        values[0] = value;
    }
    		    
    if((value == null || value == "") && inputValue != "") {
    	/**新需求，费用提取允许无效渠道码的提取 addby xupp 20120419*/
    	if( codeSelectTypeParam == "withoutCheck"){
    		alert(inputValue +"代码不存在或没有权限,请确认是否继续使用！");
    	}else{
	      //alert(inputValue + "  " + "\u4ee3\u7801\u4e0d\u5b58\u5728\u6216\u6ca1\u6709\u6743\u9650\uff01");
	      if(codeSelectCodeType == "UserCode" && codeSelectTypeParam == "message"){
	      // 经办人提示信息不提示权限
	      	 alert(inputValue + "  " + "代码不存在！");
	      }else{
			 //alert(inputValue + "  " + "代码不存在或没有权限！");
			 //Bug 137590: 测试期测试--承保模块-全单退保页面-经办部门没有录入也可以进入详细页面 mod by huangna 20150607 begin
			 if(document.getElementsByName("prpPhead.makeCom")[0]){
				 document.getElementsByName("prpPhead.makeCom")[0].value = "";
			 }
			 //Bug 137590: 测试期测试--承保模块-全单退保页面-经办部门没有录入也可以进入详细页面 mod by huangna 20150607 end
	      }
	      if(currentField.onblur == null) {
	      	currentField.onblur = emptyFunction;
	      	currentField.blur();
	      }
    	}
    } 
    if(value != null && value != "" && codeSelectTypeParam == "cascade") {
		var flag = values[values.length - 1];
		if(flag.charAt(0) != '1') {
			alert("系统消息：\n\n此代码不允许选择！");
			value = "";
			if(currentField.onblur == null) {
		      	currentField.onblur = emptyFunction;
		      	currentField.blur();
		    }
		}
    }	    
    var relations = [];
    if (codeSelectCodeRelation.indexOf(",") > -1) {
        relations = codeSelectCodeRelation.split(",");
    } else {
        relations[0] = codeSelectCodeRelation;
    }
    var relationsCount = relations.length; 
    for (var i = 0; i < relationsCount; i++) {
    	var currRalation = relations[i];
        if(currRalation == null || trim(currRalation) == ""){
            continue;
        }    
        var field = null;         
        var relation = parseInt(currRalation, 10);
        if(isNaN(relation)){ 
            field = eval("document.forms[0]."+relations[i]);
            if(field.length>1){
                field = field[elementOrder];
            }
        }else{
            field = private_getFieldByRelation(currentField,relation);
        }              
         
		if(trim(value)!=""){
				if (i < values.length) { 
							field.value = values[i];
					}
			}else{		
	        if (codeSelectIsClear == "Y"||codeSelectIsClear == "y"){
	        	/**新需求，费用提取允许无效渠道码的提取 addby xupp 20120419*/
				if( codeSelectTypeParam != "withoutCheck"){
		            field.value = "";
	        	}else{
	        		if(i != 0 ){
	        			field.value = "";
	        		}
	        	}
	        }else if(codeSelectIsClear == "H" || codeSelectIsClear == "h"){
	        		 //do nothing
	        }else if(codeSelectIsClear == "N" || codeSelectIsClear == "n"){
	        		if(i == 0){
	        				field.value = "";
	        		}
	      	}	 
		} 
    }
	// 调用完毕后将输入域从map中移除
	removeChangeFieldFormGroup(currentField);
    private_Code_CallBack(codeSelectCallBackMethod);     
	
}



/** 
 * 通过field和coderation 获得相关rationField的数组 (field :当前输入框 ，relations ：偏移量数组)
 * 
 * 作者 ：刘立伟 
 * 时间 ：2012-04-25 11：02
 */
function getFiledsByCodeRelation (field ,relations){
	//codeRation的数组长度
	var relationLength = relations.length;
	//用来存放fields元素的数组
	var fields = [];
	//偏移量有误的话，提示信息,否则存放在fields数组中
	for(var i =0;i < relationLength;i++){
		var retField = private_getFieldByRelation(field,parseInt(relations[i]));
		//如果retField 是 null 的时候，那么new 一个新的Field,并且提示信息
		if(retField == null ) { // new 一个新的 field, 解决 ： 偏移量 赋值错误 
			alert("偏移量 " + relations[i] + " 有误，导致无法获取正确的输入域!");
			retField = new Object();
		}
		fields.push(retField);
	}
	return fields;
}

/**
 * 获取父节点input和textarea元素的叠，field ： 传入的元素 targetEls ：存放元素的数组
 * 
 * 作者 ：刘立伟 
 * 时间：2012-04-27 10 ：53
 */
function getTargetEls(field,targetEls,relation){
	//跟据relation的左偏移或右偏移分开来处理，这里需要注意元素的存放顺序
	if(relation > 0){
		//把父节点（后面节点的）的同级的兄弟节点全取出来
		var fieldTemp=field.nextSibling; 
		while (fieldTemp != null ){
			var elements = fieldTemp.getElementsByTagName("*");
			var len = elements.length;
			//保存当前行中的INPUT 和 TEXTAREA 域
			for(var j=0;j<len;j++){
				var currentEl = elements[j];
				if(!currentEl){
					continue;
				}
				//modify by sunlei 2012-06-12 增加“SELECT”节点的检索，避免“SELECT”上的code_CodeChange事件报错
				if(currentEl.tagName == "INPUT" || currentEl.tagName == "TEXTAREA" || currentEl.tagName == "SELECT"){
					targetEls.push(currentEl);
				}
			} 
			fieldTemp=fieldTemp.nextSibling; 
		}
	}else if(relation < 0){
		//把父节点（前面节点的）的同级的兄弟节点全取出来
		var fieldTemp=field.previousSibling; 
		while (fieldTemp != null ){
			var elements = fieldTemp.getElementsByTagName("*");
			var len = elements.length;
			//保存当前行中的INPUT 和 TEXTAREA 域
			for(var j=len-1;j>=0;j--){
				var currentEl = elements[j];
				if(!currentEl){
					continue;
				}
				//modify by sunlei 2012-06-12 增加“SELECT”节点的检索，避免“SELECT”上的code_CodeChange事件报错
				if(currentEl.tagName == "INPUT" || currentEl.tagName == "TEXTAREA" || currentEl.tagName == "SELECT"){
					targetEls.push(currentEl);
				}
			} 
			fieldTemp=fieldTemp.previousSibling; 
		}
	}
	return  targetEls;
}

/**
 * 根据field和relation获取到，级联某个的元素
 * 
 * 作者 ：刘立伟
 * 时间 ：2012-04-25 11:44
 */
function private_getFieldByRelation(currentField,relation){
	var relation = parseInt(relation);
	//返回域赋值成当前域，也就是relation是0的情况
	var returnField = currentField;
	//初始化targetEls
	var targetEls = [];
	//当前域的父节点
	var parentNodeField = currentField.parentNode;
	//得到父节点下的所有元素
	var elements = parentNodeField.getElementsByTagName("*");
	var len = elements.length;
	if(relation > 0){
		//保存当前行中的INPUT 和 TEXTAREA 域
		for(var j=0;j<len;j++){
			var currentEl = elements[j];
			if(!currentEl){
				continue;
			}
			//modify by sunlei 2012-06-12 增加“SELECT”节点的检索，避免“SELECT”上的code_CodeChange事件报错
			if(currentEl.tagName == "INPUT" || currentEl.tagName == "TEXTAREA" || currentEl.tagName == "SELECT"){
				targetEls.push(currentEl);
			}
		}
		//找到当前域在其父节点数组中的位置
		var targetInitLen = targetEls.length;
		var targetInitIndex ;
		for(var i=0;i<targetInitLen;i++){
			//如果找到当前的输入域，则记录当前输入域在数组中的位置,用来计算迭代循环的次数
			var currentEl = targetEls[i];
			if(!currentEl){
				continue;
			}
			if(currentEl == currentField){
				targetInitIndex = i ;				
			}
		}
		//在 targetEls的数组元素数量少于relation时，迭代循环，直到数量相等或者大于relation的值
		while ((relation + targetInitIndex + 1) > targetEls.length){
			if(parentNodeField){
				targetEls = getTargetEls(parentNodeField,targetEls,relation);
				parentNodeField =  parentNodeField.parentNode;
			}else {
				return null;
			}
		
		}
		//获取targetEls的长度
		var lenEls = targetEls.length;
		var index ;
		for(var i =0;i<lenEls;i++){
			var currentEl = targetEls[i];
			if(!currentEl){
				continue;
			}
			//如果找到当前的输入域，则记录当前输入域在数组中的位置的下标
			if(currentEl == currentField){
				index = i;
			}
		}
		//获取偏移量后的元素
		returnField = targetEls[index + relation];
	}else if(relation < 0){
		//保存当前行中的INPUT 和 TEXTAREA 域
		for(var j=len-1;j>=0;j--){
			var currentEl = elements[j];
			if(!currentEl){
				continue;
			}
			//modify by sunlei 2012-06-12 增加“SELECT”节点的检索，避免“SELECT”上的code_CodeChange事件报错
			if(currentEl.tagName == "INPUT" || currentEl.tagName == "TEXTAREA" || currentEl.tagName == "SELECT"){
				targetEls.push(currentEl);
			}
		}
		//找到当前域在其父节点数组中的位置
		var targetInitLen = targetEls.length;
		var targetInitIndex ;
		for(var i=0;i<targetInitLen;i++){
			//如果找到当前的输入域，则记录当前输入域在数组中的位置,用来计算迭代循环的次数
			var currentEl = targetEls[i];
			if(!currentEl){
				continue;
			}
			if(currentEl == currentField){
				targetInitIndex = i ;				
			}
		}
		//在 targetEls的数组元素数量少于relation时，迭代循环，直到数量相等或者大于relation的值
		while (((relation*(-1)  + targetInitIndex + 1)) > targetEls.length){
			if(parentNodeField){
				targetEls = getTargetEls(parentNodeField,targetEls,relation);
				parentNodeField =  parentNodeField.parentNode;
			}else {
				return null;
			}
		}
		//获取targetEls的长度
		var lenEls = targetEls.length;
		var index ;
		for(var i =0;i<lenEls;i++){
			var currentEl = targetEls[i];
			if(!currentEl){
				continue;
			}
			//如果找到当前的输入域，则记录当前输入域在数组中的位置的下标
			if(currentEl == currentField){
				index = i;
			}
		}
		//获取偏移量后的元素
		returnField = targetEls[index + relation*(-1)];
	}
	return returnField;
}

/** for query */
var FIELD_SEPARATOR = "_FIELD_SEPARATOR_";
function setFieldValue() {
    inCodeQuery = false; 
    var elementOrder = parseInt(document.forms[0].elementOrder.value, 10);
    var elementLength = parseInt(document.forms[0].elementLength.value, 10);
    
    var callerWindowObj = window.dialogArguments;
    var currentField = callerWindowObj.prototype.currentField;
    var openerFm = callerWindowObj.document.forms[0];
    var relations = new Array();
    if (document.forms[0].codeRelation.value.indexOf(",") > -1) {
        relations = document.forms[0].codeRelation.value.split(",");
    } else {
        relations[0] = document.forms[0].codeRelation.value;
    }

    //var fieldIndex = parseInt(document.forms[0].fieldIndex.value, 10);
    if (document.forms[0].codeselect.selectedIndex < 0) {
        document.forms[0].codeselect.selectedIndex = 0;
        return false;
    }
    var value = ""; 
    var rowValues = new Array();
    var values = new Array();
    var selectedCount = 0;
    var typeParam = document.forms[0].typeParam.value;
    for (var i = 0; i < document.forms[0].codeselect.length; i++) {
        if (document.forms[0].codeselect.options[i].selected == true) {
            rowValues = new Array();
            var selectedValue = document.forms[0].codeselect.options[i].value;
            if (selectedValue.indexOf(FIELD_SEPARATOR) > -1) {
                rowValues = selectedValue.split(FIELD_SEPARATOR);
            } else {
                rowValues[0] = selectedValue;
            }
            if(typeParam == "cascade") {
    			var flag = rowValues[rowValues.length - 1];
    			if(flag == "") {
    				return false;
    			}
    			if(flag.charAt(0) != '1') {
//					alert("系统消息：\n\n此代码不允许选择！");
					return false;
				}
            }
            values[selectedCount++] = rowValues;
        }
    }
    var relationsCount = relations.length;
    var dest_Els = [];
    var dest_Values = [];
    
    for (var i = 0; i < relationsCount; i++) {
        relations[i] = trim(relations[i]);
        if(relations[i]==null||relations[i]==""){
            continue;
        }
        value = values[0][i];
        if(i >= values[0].length) {
          break;
        }
        for (var j = 1; j < selectedCount; j++) {
            if (i >= values[j].length) {
                value = value + "," + values[j][values.length - 1];
            } else {
                value = value + "," + values[j][i];
            }
        } 
//        var field = null;         
//        var relation = parseInt(relations[i], 10);
//        if(isNaN(relation)){ 
//        	 	field = eval("openerFm."+relations[i]);
//            if(elementLength>1){
//                field = field[elementOrder];
//            }
//        }else{
//            field = openerFm.elements[fieldIndex + relation];
//        } 
//        if(field == null) {
//			alert("偏移量 " + relation + " 有误，导致无法获取正确的输入域!");
//			break;
//		}
		/**
	       	修改人：王致富
	    	修改日期：2009-11-10
	    	问题：如果给出的偏移量有问题，原来会报JS错误，加入判断，即可提示正确的错误信息
		*/
        //dest_Els[dest_Els.length] = field;
       
        dest_Els = getFiledsByCodeRelation (currentField,relations);
        dest_Values[dest_Values.length] = trim(value);
    }
    
    if(callerWindowObj.fun_codeSelect_onSelected == null){
    	callerWindowObj.fun_codeSelect_onSelected = codeSelect_onSelected;
    }
    //alert("fun_codeSelect_onSelected = " + fun_codeSelect_onSelected);
    
    callerWindowObj.fun_codeSelect_onSelected.call(this, dest_Els,dest_Values);
    callerWindowObj.fun_codeSelect_onSelected = null;
    /**
       	修改人：王致富
    	修改日期：2009-11-5
    	问题：同一个输入域，双击调用双击框两次以后会有问题
	
		先用直接调用codeSelect_onSelected的方法解决，目前此方法没出现问题
		如果需要还原，取消上面的注释，删掉codeSelect_onSelected(dest_Els,dest_Values);这一行即可。
	*/
    /*codeSelect_onSelected(dest_Els,dest_Values);*/
    
    /** 添加一个返回值，供判断是否需要执行回调函数 start add by liyang 2010-09-07 */
	//设置window的returnValue值，在主界面用变量接住来判断是否是执行回调函数
	window.returnValue = "确定";
	/** 添加一个返回值，供判断是否需要执行回调函数 end add by liyang 2010-09-07 */
    window.close();
}
function codeSelect_onSelected(els,values){
	var len = els.length;
	for(var i = 0;i < len;i++){
		els[i].value = values[i];
		if(els[i].endorseOnchange) {
        	els[i].endorseOnchange();
        }
	}
}
function cancelFieldValue() {
    inCodeQuery = false;
    var elementOrder = parseInt(document.forms[0].elementOrder.value, 10);
    var elementLength = parseInt(document.forms[0].elementLength.value, 10);
    var callerWindowObj = window.dialogArguments;
    var currentField = callerWindowObj.prototype.currentField;
    var openerFm = callerWindowObj.document.forms[0];
    if (document.forms[0].isClear.value == "Y"||document.forms[0].isClear.value == "y") {
        var relations = new Array();
        if (document.forms[0].codeRelation.value.indexOf(",") > -1) {
            relations = document.forms[0].codeRelation.value.split(",");
        } else {
            relations[0] = document.forms[0].codeRelation.value;
        }
        //var fieldIndex = parseInt(document.forms[0].fieldIndex.value, 10);
        var relationsCount = relations.length;
        for (var i = 0; i < relationsCount; i++) {
            relations[i] = trim(relations[i]);
            if(relations[i]==null||relations[i]==""){
                continue;
            }
            var field = null;         
            var relation = parseInt(relations[i], 10);
            if(isNaN(relation)){ 
                field = eval("openerFm."+relations[i]);
                if(elementLength>1){
                    field = field[elementOrder];
                }
            }else{
            	 field = private_getFieldByRelation(currentField,relation);
                //field = openerFm.elements[fieldIndex + relation];
            } 
            field.value=""; 
            if(field.endorseOnchange) {
            	field.endorseOnchange();
            }
        }
    }
	/** 添加一个返回值，供判断是否需要执行回调函数 start add by liyang 2010-09-07 */
	//设置window的returnValue值，在主界面用变量接住来判断是否是执行回调函数
	window.returnValue = "取消";
	/** 添加一个返回值，供判断是否需要执行回调函数 end add by liyang 2010-09-07 */
    window.close();
}

function fieldOnKeyPress() {
    var charCode = window.event.keyCode;
    if (charCode == 13) { //enter
        setFieldValue();
    } else if (charCode == 27) { //escape 
        cancelFieldValue();     
	}
/*
    } else if (charCode == 38) { //up arrow
        if((fm.codeselect.selectedIndex==0)){
        		var currentPageNo = parseInt(fm.pageNo.value,10);
        		if(codeSelectHasSubmit){
        			  return;
        		}
          	if(currentPageNo>1){
          			locate(currentPageNo-1);
          			codeSelectHasSubmit = true;
          	}
        }
	} else if (charCode == 40) { //down arrow
        if((fm.codeselect.selectedIndex==fm.codeselect.options.length-1)){
        		var currentPageNo = parseInt(fm.pageNo.value,10);
        		if(codeSelectHasSubmit){
        			  return;
        		}
          	if(parseInt(fm.pagesCount.value,10)>currentPageNo){
          			locate(currentPageNo+1);
          			codeSelectHasSubmit = true;
          	}
        }        
    }  
*/
}

/**
 * 用于解决IE6的bug问题
 * 
 * 作者：王致富
 * 日期：2010-11-23
 */
function emptyFunction(){
	this.value = this.value;
	try { this.blur(); } catch(e) {}
} 
/** only for onchange */
function setFieldValueForCodeChange(originalRequest, object, AjaxObject){
	var currentField;
	if(originalRequest.originalPars != null) {
    	codeSelectFieldValue     =   originalRequest.originalPars["codeSelectFieldValue"]    ;
    	codeSelectCodeMethod     =   originalRequest.originalPars["codeSelectCodeMethod"]    ;
    	codeSelectCodeType       =   originalRequest.originalPars["codeSelectCodeType"]      ;
    	codeSelectCodeRelation   =   originalRequest.originalPars["codeSelectCodeRelation"]  ;
    	codeSelectIsClear        =   originalRequest.originalPars["codeSelectIsClear"]       ;
    	codeSelectRealCondition  =   originalRequest.originalPars["codeSelectRealCondition"] ;
    	codeSelectTypeParam      =   originalRequest.originalPars["codeSelectTypeParam"]     ;
    	codeSelectCallBackMethod =   originalRequest.originalPars["codeSelectCallBackMethod"];
    	codeSelectGetDataMethod  =   originalRequest.originalPars["codeSelectGetDataMethod"] ;
    	currentField           =   originalRequest.originalPars["codeSelectField"] ;
	}else{
		currentField = codeSelectField;
	}
    var inputValue = currentField.value;
    var elementOrder = getElementOrder(currentField)-1;
    
    /** 双击框code_CodeChange方法增加数据查询出错的校验 start Add by wangzhifu 20100122 */
    /** 避免因为查询出错导致输入域中的内容变成出错信息 */
//    if(originalRequest.status != 200) {
//    	errorMessage("双击输入框数据查询出错!");
//    	currentField.value = "";
//    	// 调用完毕后将输入域从map中移除
//		removeChangeFieldFormGroup(currentField);
//    	return;
//    }
    /** 双击框code_CodeChange方法增加数据查询出错的校验 end Add by wangzhifu 20100122 */
    var value = trim(originalRequest.responseText); 
    var values = [];
    if (value.indexOf(FIELD_SEPARATOR) > -1) {
        values = value.split(FIELD_SEPARATOR);
    } else {
        values[0] = value;
    }
    if((value == null || value == "") && inputValue != "") {
    	/**新需求，费用提取允许无效渠道码的提取 addby xupp 20120419*/
    	if( codeSelectTypeParam == "withoutCheck"){
    		//alert(inputValue +"代码不存在或没有权限,请确认是否继续使用！");
    	}else{
	      //alert(inputValue + "  " + "\u4ee3\u7801\u4e0d\u5b58\u5728\u6216\u6ca1\u6709\u6743\u9650\uff01");
	      if(codeSelectCodeType == "UserCode" && codeSelectTypeParam == "message"){
	      // 经办人提示信息不提示权限
	      	 alert(inputValue + "  " + "代码不存在！");
	      }else{
			alert(inputValue + "  " + "代码不存在或没有权限！");	
	      }
	      if(currentField.onblur == null) {
	      	currentField.onblur = emptyFunction;
	      	currentField.blur();
	      }
    	}
    } 
    if(value != null && value != "" && codeSelectTypeParam == "cascade") {
		var flag = values[values.length - 1];
		if(flag.charAt(0) != '1') {
			alert("系统消息：\n\n此代码不允许选择！");
			value = "";
			if(currentField.onblur == null) {
		      	currentField.onblur = emptyFunction;
		      	currentField.blur();
		    }
		}
    }
    var relations = [];
    if (codeSelectCodeRelation.indexOf(",") > -1) {
        relations = codeSelectCodeRelation.split(",");
    } else {
        relations[0] = codeSelectCodeRelation;
    }
    var relationsCount = relations.length; 
    for (var i = 0; i < relationsCount; i++) {
    	var currRalation = relations[i];
        if(currRalation == null || trim(currRalation) == ""){
            continue;
        }    
        var field = null;         
        var relation = parseInt(currRalation, 10);
        if(isNaN(relation)){ 
            field = eval("document.forms[0]."+relations[i]);
            if(field.length>1){
                field = field[elementOrder];
            }
        }else{
            field = private_getFieldByRelation(currentField,relation);
        }              
         
    		if(trim(value)!=""){
    				if (i < values.length) { 
								field.value = values[i];
						}
				}else{		
		        if (codeSelectIsClear == "Y"||codeSelectIsClear == "y"){
		        	/**新需求，费用提取允许无效渠道码的提取 addby xupp 20120419*/
					if( codeSelectTypeParam != "withoutCheck"){
			            field.value = "";
		        	}else{
		        		if(i != 0 ){
		        			field.value = "";
		        		}
		        	}
		        }else if(codeSelectIsClear == "H" || codeSelectIsClear == "h"){
		        		 //do nothing
		        }else if(codeSelectIsClear == "N" || codeSelectIsClear == "n"){
		        		if(i == 0){
		        				field.value = "";
		        		}
		      	}	 
    		} 
    	if(field.endorseOnchange) {
    		field.endorseOnchange();
    	}
    	/** 清空非空校验时添加的红色背景 start add by liyang 2010-10-12 */
    	if(field.value != ""){
    		//清空非空校验时添加的红色背景
    		clearPreErrFieldBg(field);
    	}
    	/** 清空非空校验时添加的红色背景 end add by liyang 2010-10-12*/   	
    }
	// 调用完毕后将输入域从map中移除
	removeChangeFieldFormGroup(currentField);
    private_Code_CallBack(codeSelectCallBackMethod);         
}

/**
 * 根据codeRelation，清空输入域的值
 * 	用于在输入域的值清空时，将相关输入域清空，避免再走一次后台
 * 
 * 作者：王致富
 * 日期：2010-07-07
 */
function clearInputsByCodeRelation(currentField) {
    var elementOrder = getElementOrder(currentField) - 1;
	var relations = [];
    if (codeSelectCodeRelation.indexOf(",") > -1) {
        relations = codeSelectCodeRelation.split(",");
    } else {
        relations[0] = codeSelectCodeRelation;
    }
   var relationsCount = relations.length; 
    for (var i = 0; i < relationsCount; i++) {
    	var currRelation = relations[i];
        if(currRelation == null || trim(currRelation) == ""){
            continue;
        }    
        var field = null;         
        var relation = parseInt(currRelation, 10);
        if(isNaN(relation)){ 
            field = eval("document.forms[0]."+currRelation);
            if(field.length>1){
                field = field[elementOrder];
            }
        }else{
            field = private_getFieldByRelation(currentField,relation);
        }
    	field.value = "";
    	if(field.endorseOnchange) {
    		field.endorseOnchange();
    	}
    }
    private_Code_CallBack(codeSelectCallBackMethod);
}

/**
 * eval callback method
 */ 
function private_Code_CallBack(callBackMethodValue){
    if (callBackMethodValue != "") {
	      var callbackValues = new Array(); 
	      if (callBackMethodValue.indexOf(";") > -1) {
	          callbackValues = callBackMethodValue.split(";");
	      }else{
	        	callbackValues[0] = callBackMethodValue;
	      }
	      var callbackCount = callbackValues.length;
	      for (var i = 0; i < callbackCount; i++) {
	          callbackValues[i] = trim(callbackValues[i]);
	          if(callbackValues[i]==null||callbackValues[i]==""){
	              continue;
	          }
	          eval(callbackValues[i]);
	      }
	  }
}

/**
 * trim
 */
 function trim(str) { 
 	if(str == undefined){
 		return "";
 	}else{					/** add by wangsr 当传入str为数值型报JS错*/
		str = str + "";
	}
	return str.replace(/^\s*(.*?)[\s\n]*$/g, '$1'); 
 }
 
 function getWoundType(woundCode){
 	var woundType = "";
 	if("02" == woundCode){
 		woundType = "WoundDamage";
 	}else if("03" == woundCode){
 		woundType = "WoundDead";
 	}else{
 		woundType = "WoundInjure";
 	}
 	return woundType;
 }
 
 /**是否为数字*/
function isNumber(str){
	if(isEmpty(str)){
        return {flag:true,errorMsg:""};
    }
	if(!/^([-]?)\d+(\.\d+)?$/.test(str)) 
		return {flag:false,errorMsg:"数字"};
	try{
		if(parseFloat(str)!=str) 
			return {flag:false,errorMsg:"数字"};
	}catch(ex){
		return {flag:false,errorMsg:"数字"};
	}
	return {flag:true};
}
function getElementOrder(field) {
    var i = 0;
    var order = 0;
    var elements = document.getElementsByName(field.name);
    var elementsCount = elements.length;
    for (i = 0; i < elementsCount; i++) {
        order++;
        if (elements[i] == field) {
            break;
        }
    }
    return order;
}
function getElementCount(fieldName) {
    var count = 0;
    count = document.getElementsByName(fieldName).length;
    return count;
}
/**全选方法*/
function selectAllcheck(){
	var checkFlag = document.getElementById("checkAll").checked;
		$("[name = checkCode]:checkbox").each(function() {
			this.checked = checkFlag;
		});
}