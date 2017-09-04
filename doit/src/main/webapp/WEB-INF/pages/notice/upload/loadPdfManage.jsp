<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<html>
<head>
<title>通知上传管理</title>
<meta charset="utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bs/ztree/css/metroStyle/metroStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bs/fileinput/css/fileinput.min.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/bs/fileinput/js/fileinput.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/bs/fileinput/js/locales/zh.js"></script>
	<script src="${pageContext.request.contextPath}/bs/otherjs/jquery.media.js"></script>
</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<div class="box box-primary">

				<div class="box-header with-border">
					<h3 class="box-title">通知上传管理</h3>
				</div>

				<div class="box-body">
					<table id="fileInfotable"
						class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th style="text-align: center"><input id="checkAll"
									type="checkbox" onclick="selectAllcheck()"></th>
								<th style="text-align: center">文件名称</th>
								<th style="text-align: center">文件类型</th>
								<th style="text-align: center">文件路径</th>
								<th style="text-align: center">操作人</th>
								<th style="text-align: center">上传时间</th>
								<th style="text-align: center">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div style="display: none">
						<form id="fileInfoForm" name="fileInfoForm" role="form">
							<input type="text" class="form-control" id="file_id" name="file_id"
								placeholder="文件ID" />
						</form>
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<div class="row">
						<div class="col-xs-6">
								<input id="pdfUpload" name="pdfUpload" type="file">
						</div>
						<div class="col-xs-2">
						</div>
						<div class="col-xs-2">
						</div>
						<div class="col-xs-2">
						</div>
					</div>


				</div>

			</div>
		</div>
		
		
		<div class="modal fade" id="preViewModel" tabindex="-1" role="dialog"
			aria-labelledby=""preViewModel"Label" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">x</button>
						<h4 class="modal-title" id="preViewModelModalLabel">文件预览</h4>
					</div>
					<div class="modal-body">
						<div class="row">
						<div class="col-xs-1">
							
						</div>
						<div class="col-xs-10">
							<div id="media">
						</div>
						</div>
						<div class="col-xs-1">
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>

	<script>
	//datatables中的
	var columns = [ 
	               {
	            	   "data": null,
	            	   "searchable" : false,
	        			"orderable" : false,
	        			"targets" : 0,
	        			"class" : "center" ,
	        			"render" : function(data, type, row) {				
	        				return '<input name="checkCode" type="checkbox" id="'+data.f_id+'">';
	        			}
	            
	               },
	        	   {
	           		"data" : "fileName",
	           		"orderable" : false,
	           		"class" : "center",
	           		"render":function(data, type, row) {
	           			var f_name = row.fileName;
    					return  "<a href='#' onclick='preViewPdf(\""+f_name+"\")'>"+f_name+"</a>";
    				}
	           	   },			    
	        	   {
	        		"data" : "fileType",
	        		"orderable" : false,
	        		"class" : "center"
	        	   },
	        	   {
	        			"data" : "filePath",
	        			"class" : "center",
	        			"orderable" : false
	        	   },
	        	   {
	        			"data" : "operator",
	        			"class" : "center",
	        			"orderable" : false
	        	   },
	        		  
	        	   {
	        			"data" : "operateTime",
	        			"class" : "center",
	        			"orderable" : false,
        				"render":function(data, type, row) {
        					return new Date(data).Format("yyyy-MM-dd HH:mm:ss");
        				}
	        	   },
	        	   {
	        			"data" : "dosom",
	        			"class" : "center",
	        			"orderable" : false,
	        			"render":function(data, type, row) {
	        				var f_id = row.f_id;
        					return  "<button type='button' class='btn btn-block btn-info' onclick='deleteFile("+f_id+")'>删除</button>";
        				}
	        	   }
	        	  ];
	
	
	//删除文件
	function deleteFile(obj){
		//var selectedId = $(obj).parent().parent().find('.sorting_1').children("input").attr("id")
		
		alert("当前点击按钮的行id为:"+obj);
		
	}
	
	//预览文件
	function preViewPdf(filename){
		//根据文件名称预览数据
		$('#preViewModel').modal("show");
			$('#media').media({
	   		 	width:500,
	   		 	height:900,
	   		 	autoplay: true, 
	   		 	src: '${pageContext.request.contextPath}/pdf/1.pdf'
   			});
		
	}
	
		 
	
	
    //菜单的json格式
    $(function () {

    	//查询已经上传的文件列表
    	getDataTable();
    	//初始化文件上传控件
    	initFileUpload();
    
    });
    
    //初始化文件上传控件
    function initFileUpload(){
    	$("#pdfUpload").fileinput({
    		language: 'zh', //中文显示
            showPreview : false,
            //showUpload: true,//是否显示上传按钮
            maxFileCount: 1,//最大允许的文件数量
            allowedFileExtensions : ['pdf'],//接收的文件后缀
            initialCaption:"请上传PDF格式的文件,最大10MB",
            mainClass: "input-group-lg",
            dropZoneEnabled: false,//是否显示拖拽区域
            browseClass: "btn btn-primary",//按钮样式
            enctype:"multipart/form-data",
           // maxFileSize: 10240,//允许的文件大小,0 标示不限制
            uploadUrl: '${pageContext.request.contextPath}/notice/fileUpload',
        })

    }




    //填充datatable
    function getDataTable(){
    	//加载datatable
        var ajaxList = new AjaxList("#fileInfotable");
        ajaxList.targetUrl = '${pageContext.request.contextPath}' + '/notice/getAllFileInfoJson.do';
        ajaxList.columns = columns;	
    	//ie 8 以上根据原有方法无法获取form的id，需要提供
    	ajaxList.formId = "fileInfoForm";
    	ajaxList.rowCallback = rowCallback;
    	ajaxList.query();
    }
    //回调函数
    function rowCallback(row, data, displayIndex, displayIndexFull){
    	//alert(JSON.stringify(data));
    }
    //时间格式化js 后续放到frame.jsp 
    Date.prototype.Format = function (fmt) {  
        var o = {
            "M+": this.getMonth() + 1, //月份 
            "d+": this.getDate(), //日 
            "H+": this.getHours(), //小时 
            "m+": this.getMinutes(), //分 
            "s+": this.getSeconds(), //秒 
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
            "S": this.getMilliseconds() //毫秒 
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
  	
</script>




</body>
</html>