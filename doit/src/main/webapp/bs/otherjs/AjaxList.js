/**
 * Ajax模式List页面支持JS
 */
function AjaxList(selector) {
	this.selector = selector;
	this.targetUrl = null;
	this.exportFileName = null;
	this.formId = document.forms[0].id;
	this.columns = {};
	this.rowCallback = {};
	this.datatable = {};

	
	
	// init checkAll bind
	$("#checkAll").click(function() {
		var checkFlag = this.checked;
		$("[name = checkCode]:checkbox").each(function() {
			this.checked = checkFlag;
		});

	});
	$.extend( $.fn.dataTable.defaults, {
		"processing" : true,
		"serverSide" : true,
		"searching" : false, // 搜索
		"autoWidth" : false,
        "scrollX": true,
        "dom": '<"top">rt<"bottom"filp><"clear">',
	    "stateSave": false,
	    "pagingType" : "full_numbers",
		"language" : {
			"emptyTable":  "没有数据", 
			"lengthMenu" : "每页显示 _MENU_ 条记录",
			"zeroRecords" : "没有检索到数据",
			"info" : "当前第  _PAGE_ 页 （共   _PAGES_ 页  _TOTAL_ 条记录）",
			"infoEmpty" : "",
			"infoFiltered" : "(filtered from _MAX_ total records)",
			"processing" : "数据加载中...",
			"decimal" : ",",
			"thousands" : "",
			"paginate" : {
				"first" : "首页",
				"previous" : "前页",
				"next" : "后页",
				"last" : "末页"
			}
		},
		//"lengthMenu": [[10, 25, 50, -1], [10, 25, 50,"All"]], 
		"destroy" : true
	} );
}
(function($){  
    $.fn.serializeJson=function(){  
        var serializeObj={};  
        var array=this.serializeArray();   
        $(array).each(function(){  
            if(serializeObj[this.name]){  
                if($.isArray(serializeObj[this.name])){  
                    serializeObj[this.name].push(this.value);  
                 }else{  
                     serializeObj[this.name]=[serializeObj[this.name],this.value];  
                 }  
             }else{  
                 serializeObj[this.name]=this.value;   
             }  
         });  
         return serializeObj;  
     };  
 })(jQuery);
AjaxList.prototype.query = function() {
	var innerDataTable = $(this.selector);
	var innerTargetUrl = this.targetUrl;
	var innerFormId = this.formId;
	var innerColumns = this.columns;
	var innerRowCallback = this.rowCallback; 
    
	this.datatable = innerDataTable.dataTable({
		"ajax" : {
			url : innerTargetUrl,
			error : handleAjaxError,
			data: $("#"+innerFormId).serializeJson(),
			type : "POST"
		},
		"columns" : innerColumns,
		"rowCallback" : innerRowCallback
	});
	
	function handleAjaxError( xhr, textStatus, error ) {
		alert(xhr.responseText);
	}
};


function AjaxExportList(selector) {
	this.selector = selector;
	this.targetUrl = null;
	this.exportFileName = null;
}


AjaxExportList.prototype.exportExcel = function() {
	var innerDataTable = $(this.selector);
	var innerTargetUrl = this.targetUrl;
	var innerFileName = this.exportFileName;
	

	  var oTable =  innerDataTable.dataTable();
	  var data = oTable.fnSettings()["oAjaxData"];
	  if(data==undefined||data.length<=0)
	  {
		  alert("请先查询再导出Excel!");
		  return;
	  }

	  $('th',oTable).each(function(columnIndex){		  
		  var thead=$(this);
		  data["columns"][columnIndex]["thText"]=thead.text();
	  });

	 // printObject(data);
	  var url= innerTargetUrl;
	  var filename=innerFileName;
	  
	  $.jBox.tip('数据正在导出中，请稍候....','loading');
	  
	  $.ajax({
			type : "POST",
			url : url,
			data : data,
			async : false,
			success : function(obj) {
				$.jBox.closeTip();
				var filepath=obj;
				var iframe = document.createElement("iframe");
				iframe.style.display = 'none';
				iframe.src = contextRootPath+"/file/download.do?filepath="+filepath+"&filename="+encodeURI(encodeURI(filename));				
				document.body.appendChild(iframe);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				flag = false;
				$.jBox.closeTip();
				$.jBox.error(textStatus + errorThrown, 'jBox');
				//alert();
			}
			
		});
//	  var iframe = document.createElement("iframe");	  
//	  iframe.src = url;
//	  document.body.appendChild(iframe);
	//  var imgPath=contextPath+"/base/css/images/default/shared/blue-loading.gif";
//http://jquery.malsup.com/block/#
//	  $.blockUI({
//
//	  message:"<h4> 报表正在导出中，请稍后.... </h4>", 
//
//	  css:{background:'none',color: '#000',border:'none'},
//
//	  overlayCSS:{backgroundColor:'#C5E1F0',opacity:'0.4'}
//
//	  });
//
//	  iframe.onreadystatechange = function(){
//
//	      //文件下载是在http请求的interactive也就是浏览器交互阶段。
//
//	          if (iframe.readyState == "interactive"){
//
//	          $.unblockUI(); 
//
//	          }
//
//	  };

	 // iframe.style.display = 'none';

};

function printObject(obj){ 
	//obj = {"cid":"C0","ctext":"区县"}; 
	var temp = ""; 
	for(var i in obj){//用javascript的for/in循环遍历对象的属性 
	temp += i+"="+obj[i]+"\n"; 
	} 
	alert(temp);//结果：cid:C0 \n ctext:区县 
} 
