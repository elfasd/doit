<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>资源管理</title>
<meta charset="utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bs/ztree/css/metroStyle/metroStyle.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/bs/ztree/js/jquery.ztree.all.js"></script>
</head>
<body>
	<div class="row">
		<div class="col-xs-3">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">系统资源</h3>
				</div>
				<div class="box-body">
					<ul id="tree" class="ztree"></ul>
				</div>
				<div class="box-footer"></div>
			</div>
		</div>
		<div class="col-xs-8">
			<div class="box box-primary">

				<div class="box-header with-border">
					<h3 class="box-title">系统资源</h3>
				</div>

				<div class="box-body">
					<table id="tasktable"
						class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th style="text-align: center"><input id="checkAll"
									type="checkbox" onclick="selectAllcheck()"></th>
								<th style="text-align: center">ID</th>
								<th style="text-align: center">功能代码</th>
								<th style="text-align: center">功能名称</th>
								<th style="text-align: center">上级功能代码</th>
								<th style="text-align: center">层级</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div style="display: none">
						<form id="taskForm" name="taskForm" role="form">
							<input type="text" class="form-control" id="pcode" name="pcode"
								placeholder="功能ID" />
						</form>
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<div class="row">
						<div class="col-xs-6"></div>
						<div class="col-xs-2">
							<button type="button" class="btn btn-block btn-info" id="taskAdd">增加</button>
						</div>
						<div class="col-xs-2">
							<button type="button" class="btn btn-block btn-info"
								id="taskUpdate">修改</button>
						</div>
						<div class="col-xs-2">
							<button type="button" class="btn btn-block btn-info"
								id="taskDelete">删除</button>
						</div>
					</div>


				</div>

			</div>
		</div>
		<div class="col-xs-1"></div>


		<div class="modal fade" id="taskAddModal" tabindex="-1" role="dialog"
			aria-labelledby="taskAddModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">x</button>
						<h4 class="modal-title" id="taskAddModalLabel">新增资源</h4>
					</div>
					<div class="modal-body">

						<form role="form" id="taskAddForm">
							<div class="box-body">
								<div class="form-group">
									<label >功能代码<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="taskAddVo.taskcode" name="taskcode" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group">
									<label>功能名称<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="taskAddVo.taskcname" name="taskcname" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group" style="display: none">
									<label>上级功能代码</label>
                  					<input type="text" id="taskAddVo.parentcode" name="parentcode" class="form-control" placeholder="请输入 ...">
								</div>
							</div>
							<!-- /.box-body -->
							<div class="box-footer">
								<button type="button" class="btn btn-primary" id="saveTask">保存</button>
							</div>
						</form>


					</div>
				</div>
			</div>
		</div>
		
		
		<div class="modal fade" id="taskUpdateModal" tabindex="-1" role="dialog"
			aria-labelledby="taskUpdateModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">x</button>
						<h4 class="modal-title" id="taskUpdateModalLabel">修改资源</h4>
					</div>
					<div class="modal-body">

						<form role="form" id="taskUpdateForm">
							<div class="box-body">
								<div class="form-group">
									<label >功能代码<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="taskUpdateVo.taskcode" name="taskcode" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group">
									<label>功能名称<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="taskUpdateVo.taskcname" name="taskcname" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group" style="display: none">
									<label>task_id</label>
                  					<input type="text" id="taskUpdateVo.task_id" name="task_id" class="form-control" placeholder="请输入 ...">
								</div>
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<button type="button" class="btn btn-primary" id="updateTask">修改</button>
							</div>
						</form>


					</div>
				</div>
			</div>
		</div>




	</div>


	<script>
	var columns = [ 
	               {
	            	   "data": null,
	            	   "searchable" : false,
	        			"orderable" : false,
	        			"targets" : 0,
	        			"class" : "center" ,
	        			"render" : function(data, type, row) {				
	        				return '<input name="checkCode" type="checkbox" id="'+data.taskcode+'">';
	        			}
	            
	               },
	        	   {
	        		"data" : "task_id",
	        		"orderable" : false,
	        		"class" : "center"
	        	   },
	        	   {
	           		"data" : "taskcode",
	           		"orderable" : false,
	           		"class" : "center"
	           	   },			    
	        	   {
	        		"data" : "taskcname",
	        		"orderable" : false,
	        		"class" : "center"
	        	   },
	        	   {
	        			"data" : "parentcode",
	        			"class" : "center",
	        			"orderable" : false
	        	   },
	        	   {
	        			"data" : "lx",
	        			"class" : "center",
	        			"orderable" : false
	        			//,
        				//"render":function(data, type, row) {
        					//return new Date(data).Format("yyyy-MM-dd");
        				//}
	        	   }
	        	  ];
	
    var zTree;
    var demoIframe;
    //添加鼠标经过的选项按钮
    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
        var addStr = "<span class='button remove' id='removeBtn_" + treeNode.tId
                + "' title='add node' onfocus='this.blur();'></span>";
        addStr += "<span class='button add' id='addBtn_" + treeNode.tId + "'></span>";
        addStr += "<span class='button edit' id='editBtn_" + treeNode.tId + "'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_"+treeNode.tId);
        if (btn) btn.bind("click", function(){
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.addNodes(treeNode, {id:(1000 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
            return false;
        });
    };
	//删除鼠标经过的选项按钮
    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
        $("#removeBtn_"+treeNode.tId).unbind().remove();
        $("#editBtn_"+treeNode.tId).unbind().remove();
    };

    var setting = {
        check: {
            enable: false
        },
        view: {
            //addHoverDom: addHoverDom,
         	//removeHoverDom: removeHoverDom,
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
        
            }
        },
        callback: {
            onClick:taskOnClick
        }
    };
    
    //当前节点的task_code
    var nowSelectedTaskCode="0";
    //当前节点的leve
    var nowSelectedLevel="0";
    //单击菜单后在datagrid中显示菜单下的子菜单
    function taskOnClick(event, treeId, treeNode, clickFlag) {
    	nowSelectedTaskCode = treeNode.id;
    	nowSelectedLevel = treeNode.level;
    	getTaskByParentCode(treeNode.id);
    }
    
    //根据上级id查询菜单 并放置到datatable中
    function getTaskByParentCode(parentCode){
    	$("#pcode").val(parentCode);
    	//加载datatable
        var ajaxList = new AjaxList("#tasktable");
        ajaxList.targetUrl = '${pageContext.request.contextPath}' + '/task/queryTask';
        ajaxList.columns = columns;	
    	//ie 8 以上根据原有方法无法获取form的id，需要提供
    	ajaxList.formId = "taskForm";
    	ajaxList.rowCallback = rowCallback;
    	ajaxList.query();
    }
    
    
    //查询菜单树
    function getTaskTree(){
   	 $.ajax({
				type : "POST",
				url : "/task/getTaskJson",
				dataType:'text',
				success : function(obj) {
					var t = $("#tree");
			        t = $.fn.zTree.init(t, setting, eval("("+obj+")"));
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("获取资源树失败!");
				}
				
			});
    }
    
    //隐藏弹出框
    function hideAllMode(){
    	$('#taskAddModal').modal('hide');
    	$('#taskUpdateModal').modal('hide');

    }
    
    
    //菜单的json格式
    $(function () {
    	//默认隐藏弹出框
    	hideAllMode();
    	//初始化查出所有的一级菜单
    	getTaskByParentCode("gqis");
    	//获取菜单树
    	getTaskTree();
    });
    
    function rowCallback(row, data, displayIndex, displayIndexFull){
    	//alert(JSON.stringify(data));
    }
    
    //点击增加按钮触发的函数
    $("#taskAdd").click(function(){
    	$('#taskAddModal').modal("show");
    });
    
    
  //点击修改按钮触发的函数
    $("#taskUpdate").click(function(){
	  var codeList=$("input[name=checkCode]:checked");
	  	if(codeList==null||codeList.length==0){
	  		alert("请选择一条数据！");
	  		return ;
	  	}
	  	if(codeList.length>1){
	  		alert("只能选择一条数据！");
	  		return ;
	  	}
	  	
	  	$.ajax({
				type : "POST",
				url : "/task/getSelectedTask",
				data : {taskcode:codeList[0].id},
				dataType:'text',
				success : function(obj) {
					obj = JSON.parse(obj);
					$("[id='taskUpdateVo.taskcode']").val(obj.taskcode);
					$("[id='taskUpdateVo.taskcname']").val(obj.taskcname);
					$("[id='taskUpdateVo.task_id']").val(obj.task_id);
					$('#taskUpdateModal').modal("show");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("查询失败!");
				}
				
			});
	  	
	  	
	  	
	  	
	  	
	  
  });
  
  
  	//点击删除按钮触发的函数
    $("#taskDelete").click(function(){
  	  var codeList=$("input[name=checkCode]:checked");
  	  	if(codeList==null||codeList.length==0){
  	  		alert("请选择一条数据！");
  	  		return ;
  	  	}
  	  	
  	  //需要注意 这里的id是taskcode 而不是task_id 注意后台调用的方法	
  	  var idList="";
	  for(var i=0;i<codeList.length;i++){
		  idList+=codeList[i].id;
		  idList+=",";
	  }
  	  	
  	  	if(confirm("确认删除?")){
  	  		
  	  	 $.ajax({
				type : "POST",
				url : "/task/deleteTask",
				data : {idList:idList},
				dataType:'text',
				success : function(obj) {
					if(obj=="true"){
						alert("请先删除当前功能的子功能!");
					}else{
						//初始化查出所有的一级菜单
				    	getTaskByParentCode(nowSelectedTaskCode);
				    	//获取菜单树
				    	getTaskTree();
						alert("删除成功!");
					}
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("删除失败!");
				}
				
			});
  	  	}else{
  	  		
  	  	}
    });
  	
    //点击弹出框中的修改按钮触发的函数
    $("#updateTask").click(function(){
    	//非空校验
    	var tcode = $("[id='taskUpdateVo.taskcode']").val();
    	if(tcode==""||tcode==null||tcode==undefined){
    		alert("功能编码不允许为空!");
    		return false;
    	}
    	
    	var tname = $("[id='taskUpdateVo.taskcname']").val();
    	if(tname==""||tname==null||tname==undefined){
    		alert("功能名称不允许为空!");
    		return false;
    	}
    	
   	 	$.ajax({
			type : "POST",
			url : "/task/updateTask",
			data:$('#taskUpdateForm').serialize(),
			dataType:'text',
			success : function(obj) {
				alert("修改成功!");
				//初始化查出所有的一级菜单
		    	getTaskByParentCode(nowSelectedTaskCode);
		    	//获取菜单树
		    	getTaskTree();
		    	//隐藏model
		    	$('#taskUpdateModal').modal('hide');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("修改失败!");
				
			}
			
		});
    	
    	
    	
    	
    	
    });
    
    
    //点击弹出框中的保存按钮触发的函数
    $("#saveTask").click(function(){
    	
    	//非空校验
    	var tcode = $("[id='taskAddVo.taskcode']").val();
    	if(tcode==""||tcode==null||tcode==undefined){
    		alert("资源代码不允许为空!");
    		return false;
    	}
    	
    	var tname = $("[id='taskAddVo.taskcname']").val();
    	if(tname==""||tname==null||tname==undefined){
    		alert("资源名称不允许为空!");
    		return false;
    	}
    	 //设置父Id
    	 $("[id='taskAddVo.parentcode']").val(nowSelectedTaskCode);
    	 $.ajax({
				type : "POST",
				url : "/task/addTask",
				data:$('#taskAddForm').serialize(),
				dataType:'text',
				success : function(obj) {
					
					//0:保存成功 1:功能代码重复
					if(obj=='0'){
						alert("保存成功!");
						//初始化查出所有的一级菜单
				    	getTaskByParentCode(nowSelectedTaskCode);
				    	//获取菜单树
				    	getTaskTree();
				    	
					}else{
						alert("保存失败:功能代码重复!");
					}
					
					//隐藏model					
					$('#taskAddModal').modal('hide');
					
					
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("保存失败!");
					
				}
				
			});
  	  
    });
  	
</script>




</body>
</html>