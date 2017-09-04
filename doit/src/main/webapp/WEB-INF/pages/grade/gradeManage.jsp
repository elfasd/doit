<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>岗位管理</title>
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
					<h3 class="box-title">系统岗位</h3>
				</div>
				<div class="box-body">
					<ul id="tree" class="ztree"></ul>
				</div>
				<div class="box-footer">
					<div class="row">
						<div class="col-xs-4">
							<button type="button" class="btn btn-block btn-info" id="gradeAdd">增加</button>
						</div>
						<div class="col-xs-4">
							<button type="button" class="btn btn-block btn-info"
								id="gradeUpdate">修改</button>
						</div>
						<div class="col-xs-4">
							<button type="button" class="btn btn-block btn-info"
								id="gradeDelete">删除</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-8">
			<div class="box box-primary">

				<div class="box-header with-border">
					<h3 class="box-title">岗位资源</h3>
				</div>

				<div class="box-body">
					<ul id="gradetasktree" class="ztree"></ul>
				</div>
				
				<!-- /.box-body -->

				<div class="box-footer">
					<div class="row">
						<div class="col-xs-3">
						</div>
						<div class="col-xs-3">
						</div>
						<div class="col-xs-3">
						</div>
						<div class="col-xs-3">
							<button type="button" class="btn btn-block btn-info"
								id="gradeTaskManage">绑定资源</button>
						</div>
					</div>
				</div>

			</div>
		</div>
		<div class="col-xs-1"></div>


		<div class="modal fade" id="gradeAddModal" tabindex="-1" role="dialog"
			aria-labelledby="gradeAddModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">x</button>
						<h4 class="modal-title" id="gradeAddModalLabel">新增岗位</h4>
					</div>
					<div class="modal-body">

						<form role="form" id="gradeAddForm">
							<div class="box-body">
								<div class="form-group">
									<label >岗位名称<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="gradeAddVo.gradecname" name="gradecname" class="form-control" placeholder="请输入 ...">
								</div>
							</div>
							<!-- /.box-body -->
							<div class="box-footer">
								<button type="button" class="btn btn-primary" id="saveGrade">保存</button>
							</div>
						</form>


					</div>
				</div>
			</div>
		</div>
		
		
		<div class="modal fade" id="gradeUpdateModal" tabindex="-1" role="dialog"
			aria-labelledby="gradeUpdateModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">x</button>
						<h4 class="modal-title" id="gradeUpdateModalLabel">修改岗位</h4>
					</div>
					<div class="modal-body">

						<form role="form" id="gradeUpdateForm">
							<div class="box-body">
								<div class="form-group">
									<label >岗位名称<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="gradeUpdateVo.gradecname" name="gradecname" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group" style="display: none">
									<label >岗位ID<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="gradeUpdateVo.g_id" name="g_id" class="form-control" placeholder="请输入 ...">
								</div>
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<button type="button" class="btn btn-primary" id="updateGrade">修改</button>
							</div>
						</form>


					</div>
				</div>
			</div>
		</div>

	</div>


	<script>
    var setting = {
        check: {
            enable: false
        },
        view: {
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
            onClick:gradeOnClick
        }
    };
    
    //当前节点的g_id
    var nowSelectedTaskCode="0";
    function gradeOnClick(event, treeId, treeNode, clickFlag) {
    	nowSelectedTaskCode = treeNode.id;
    	//点击之后初始化资源树
    	getGradeTaskTree();
    }
    
    
    //岗位资源树
    var taskSetting = {
            check: {
                enable: true
            },
            view: {
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
                onClick:gradeTaskOnClick
            }
        };
    
    
    function gradeTaskOnClick(event, treeId, treeNode, clickFlag) {
    }
    
    
    
    
    
    
    //获取资源树
    function getGradeTree(){
   	 $.ajax({
				type : "POST",
				url : "/grade/getGradeJson",
				dataType:'text',
				success : function(obj) {
					var t = $("#tree");
			        t = $.fn.zTree.init(t, setting, eval("("+obj+")"));
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("获取岗位树失败!");
				}
				
			});
    }
    
  
    //查询菜单树
    function getGradeTaskTree(){
   	 $.ajax({
				type : "POST",
				url : "/grade/getGradeTaskJson",
				data : {g_id:nowSelectedTaskCode},
				dataType:'text',
				success : function(obj) {
					var t1 = $("#gradetasktree");
			        t1 = $.fn.zTree.init(t1, taskSetting, eval("("+obj+")"));
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("获取资源树失败!");
				}
				
			});
    }
    
    //隐藏弹出框
    function hideAllMode(){
    	$('#gradeAddModal').modal('hide');
    	$('#gradeUpdateModal').modal('hide');

    }
    
    
    //菜单的json格式
    $(function () {
    	//默认隐藏弹出框
    	//hideAllMode();
    	//获取菜单树
    	getGradeTree();
    });
    
    function rowCallback(row, data, displayIndex, displayIndexFull){
    	//alert(JSON.stringify(data));
    }
    
    //点击增加按钮触发的函数
    $("#gradeAdd").click(function(){
    	$('#gradeAddModal').modal("show");
    });
    
    
    //点击绑定资源后触发的函数
    $("#gradeTaskManage").click(function(){
    	
    	 if(nowSelectedTaskCode=="0"){
        	 alert("请选择一个岗位!");
        	 return ;
         }	
    	
    	if(confirm("确认绑定?")){
    		var treeObj=$.fn.zTree.getZTreeObj("gradetasktree");
    		//获取所有选中的taskCode
    		var selectedNodes = treeObj.getCheckedNodes(true);
            var taskCodes="";
             for(var i=0;i<selectedNodes.length;i++){
            	 taskCodes+=selectedNodes[i].id + ",";
             }
             
    		$.ajax({
				type : "POST",
				url : "/grade/saveGradeTaskBind",
				data:{taskCodes:taskCodes,g_id:nowSelectedTaskCode},
				dataType:'text',
				success : function(obj) {
					getGradeTaskTree();
					alert("绑定成功!");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("绑定失败!");
				}
				
			});
    		
    	}
    });
    
    
    
  //点击修改按钮触发的函数
    $("#gradeUpdate").click(function(){
    	
     if(nowSelectedTaskCode=="0"){
    	 alert("请选择一个岗位!");
    	 return ;
     }	
     
	  	
	  	$.ajax({
				type : "POST",
				url : "/grade/getSelectedGrade",
				data : {g_id:nowSelectedTaskCode},
				dataType:'text',
				success : function(obj) {
					obj = JSON.parse(obj);
					$("[id='gradeUpdateVo.gradecname']").val(obj.gradecname);
					$("[id='gradeUpdateVo.g_id']").val(obj.g_id);
					$('#gradeUpdateModal').modal("show");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("查询失败!");
				}
				
			});
	  	
	  	
	  	
	  	
	  	
	  
  });
  
  
  	//点击删除按钮触发的函数
    $("#gradeDelete").click(function(){
    	 if(nowSelectedTaskCode=="0"){
        	 alert("请选择一个岗位!");
        	 return ;
         }	
  	  	
  	  	if(confirm("确认删除?")){
  	  		
  	  	 $.ajax({
				type : "POST",
				url : "/grade/deleteGrade",
				data:{g_id:nowSelectedTaskCode},
				dataType:'text',
				success : function(obj) {
					if(obj=="true"){
						alert("请先删除当前菜单的子菜单!");
					}else{
						//这里应该根据标志位判断岗位下是否有绑定的用户
						if(obj=='0'){
							getGradeTree();
							//清空资源树
							nowSelectedTaskCode="0";
					    	var t1 = $("#gradetasktree");
					        t1 = $.fn.zTree.init(t1, taskSetting, eval(""));
							alert("删除成功!");
						}else{
							alert("删除失败:请先删除岗位下绑定的用户!");
						}
						
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
    $("#updateGrade").click(function(){
    	//非空校验
    	var tcode = $("[id='gradeUpdateVo.gradecname']").val();
    	if(tcode==""||tcode==null||tcode==undefined){
    		alert("岗位名称不允许为空!");
    		return false;
    	}
    	
   	 	$.ajax({
			type : "POST",
			url : "/grade/updateGrade",
			data:$('#gradeUpdateForm').serialize(),
			dataType:'text',
			success : function(obj) {
				if(obj=='0'){
					alert("修改成功!");
			    	//获取菜单树
			    	getGradeTree();
				}else{
					alert("修改失败:岗位重复!");
					
				}
		    	//隐藏model
		    	$('#gradeUpdateModal').modal('hide');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("修改失败!");
				
			}
			
		});
    	
    	
    	
    	
    	
    });
    
    
    //点击弹出框中的保存按钮触发的函数
    $("#saveGrade").click(function(){
    	
    	//非空校验
    	var tcode = $("[id='gradeAddVo.gradecname']").val();
    	if(tcode==""||tcode==null||tcode==undefined){
    		alert("岗位名称不允许为空!");
    		return false;
    	}
    	
    	 $.ajax({
				type : "POST",
				url : "/grade/addGrade",
				data:$('#gradeAddForm').serialize(),
				dataType:'text',
				success : function(obj) {
					
					//0:保存成功 1:功能代码重复
					if(obj=='0'){
						alert("保存成功!");
				    	//获取菜单树
				    	getGradeTree();
				    	
					}else{
						alert("保存失败:岗位名称重复!");
					}
					
					//隐藏model					
					$('#gradeAddModal').modal('hide');
					
					
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("保存失败!");
					
				}
				
			});
  	  
    });
  	
</script>




</body>
</html>