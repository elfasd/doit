<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<html>
<head>
<title>菜单管理</title>
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
					<h3 class="box-title">系统菜单</h3>
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
					<h3 class="box-title">菜单管理</h3>
				</div>

				<div class="box-body">
					<table id="menutable"
						class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th style="text-align: center"><input id="checkAll"
									type="checkbox" onclick="selectAllcheck()"></th>
								<th style="text-align: center">ID</th>
								<th style="text-align: center">上级ID</th>
								<th style="text-align: center">功能代码</th>
								<th style="text-align: center">菜单名称</th>
								<th style="text-align: center">URL</th>
								<th style="text-align: center">英文名称</th>
								<th style="text-align: center">有效标志</th>
								<th style="text-align: center">序号</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div style="display: none">
						<form id="menuForm" name="menuForm" role="form">
							<input type="text" class="form-control" id="menuId" name="menuId"
								placeholder="菜单ID" />
						</form>
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<div class="row">
						<div class="col-xs-6"></div>
						<div class="col-xs-2">
							<shiro:hasPermission name="gqis_config_menu_add">  
								<button type="button" class="btn btn-block btn-info" id="menuAdd">增加</button>
							</shiro:hasPermission>
						</div>
						<div class="col-xs-2">
							<shiro:hasPermission name="gqis_config_menu_update"> 
								<button type="button" class="btn btn-block btn-info"
									id="menuUpdate">修改</button>
							 </shiro:hasPermission>
						</div>
						<div class="col-xs-2">
							<button type="button" class="btn btn-block btn-info"
								id="menuDelete">删除</button>
						</div>
					</div>


				</div>

			</div>
		</div>
		<div class="col-xs-1"></div>


		<div class="modal fade" id="menuAddModal" tabindex="-1" role="dialog"
			aria-labelledby="menuAddModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">x</button>
						<h4 class="modal-title" id="menuAddModalLabel">新增菜单</h4>
					</div>
					<div class="modal-body">

						<form role="form" id="menuAddForm">
							<div class="box-body">
								<div class="form-group">
									<label > 菜单中文名<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="menuAddVo.menucname" name="menucname" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group">
									<label>菜单英文名称</label>
                  					<input type="text" id="menuAddVo.menuename" name="menuename" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group">
									<label>URL</label>
                  					<input type="text" id="menuAddVo.actionurl" name="actionurl" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group">
									<label>资源编码<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="menuAddVo.taskcode" name="taskcode" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group">
									<label>显示序号<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="number" value="0" id="menuAddVo.displayno" name="displayno" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group" style="display: none">
									<label>上级ID</label>
                  					<input type="text" id="menuAddVoupperid" name=upperid class="form-control" placeholder="请输入 ...">
								</div>
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<button type="button" class="btn btn-primary" id="saveMenu">保存</button>
							</div>
						</form>


					</div>
				</div>
			</div>
		</div>
		
		
		<div class="modal fade" id="menuUpdateModal" tabindex="-1" role="dialog"
			aria-labelledby="menuUpdateModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">x</button>
						<h4 class="modal-title" id="menuUpdateModalLabel">修改菜单</h4>
					</div>
					<div class="modal-body">

						<form role="form" id="menuUpdateForm">
							<div class="box-body">
								<div class="form-group">
									<label > 菜单中文名<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="menuUpdateVo.menucname" name="menucname" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group">
									<label>菜单英文名称</label>
                  					<input type="text" id="menuUpdateVo.menuename" name="menuename" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group">
									<label>URL</label>
                  					<input type="text" id="menuUpdateVo.actionurl" name="actionurl" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group">
									<label>资源编码<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="menuUpdateVo.taskcode" name="taskcode" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group">
									<label>显示序号<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="number"   id="menuUpdateVo.displayno" name="displayno" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group" style="display: none">
									<label>ID</label>
                  					<input type="text" id="menuUpdateVo.m_id" name="m_id" class="form-control" placeholder="请输入 ...">
								</div>
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<button type="button" class="btn btn-primary" id="updateMenu">修改</button>
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
	        				return '<input name="checkCode" type="checkbox" id="'+data.m_id+'">';
	        			}
	            
	               },
	        	   {
	        		"data" : "m_id",
	        		"orderable" : false,
	        		"class" : "center"
	        	   },
	        	   {
	           		"data" : "upperid",
	           		"orderable" : false,
	           		"class" : "center"
	           	   },			    
	        	   {
	        		"data" : "taskcode",
	        		"orderable" : false,
	        		"class" : "center"
	        	   },
	        	   {
	        			"data" : "menucname",
	        			"class" : "center",
	        			"orderable" : false
	        	   },
	        	   {
	        			"data" : "actionurl",
	        			"class" : "center",
	        			"orderable" : false
	        	   },
	        		  
	        	   {
	        			"data" : "menuename",
	        			"class" : "center",
	        			"orderable" : false
	        	   },
	        	   
	        	   {
	        			"data" : "validind",
	        			"orderable" : false,
	        			"class" : "center"
	        			//,
	        				//"render":function(data, type, row) {
	        					//return new Date(data).Format("yyyy-MM-dd");
	        				//}
	        	   },
	        	   {
	        			"data" : "displayno",
	        			"orderable" : false,
	        			"class" : "center"
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
            onClick:menuOnClick
        }
    };
    
    //当前节点的m_id
    var nowSelectedPId="0";
    //当前节点的leve
    var nowSelectedLevel="0";
    //单击菜单后在datagrid中显示菜单下的子菜单
    function menuOnClick(event, treeId, treeNode, clickFlag) {
    	nowSelectedPId = treeNode.id;
    	nowSelectedLevel = treeNode.level;
    	getMenuByUpperId(treeNode.id);
    }
    
    //根据上级id查询菜单 并放置到datatable中
    function getMenuByUpperId(upperid){
    	$("#menuId").val(upperid);
    	//加载datatable
        var ajaxList = new AjaxList("#menutable");
        ajaxList.targetUrl = '${pageContext.request.contextPath}' + '/menu/queryMenu';
        ajaxList.columns = columns;	
    	//ie 8 以上根据原有方法无法获取form的id，需要提供
    	ajaxList.formId = "menuForm";
    	ajaxList.rowCallback = rowCallback;
    	ajaxList.query();
    }
    
    
    //查询菜单树
    function getMenuTree(){
   	 $.ajax({
				type : "POST",
				url : "/menu/getMenuJson",
				dataType:'text',
				success : function(obj) {
				    //	alert(obj);
					var t = $("#tree");
			        t = $.fn.zTree.init(t, setting, eval("("+obj+")"));
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(XMLHttpRequest+"获取菜单树失败!" +errorThrown);
				}
				
			});
    }
    
    //隐藏弹出框
    function hideAllMode(){
    	$('#menuAddModal').modal('hide');
    	$('#menuUpdateModal').modal('hide');

    }
    
    
    //菜单的json格式
    $(function () {
    	
    	
    	
    	
    	//默认隐藏弹出框
    	hideAllMode();
    	//初始化查出所有的一级菜单
    	getMenuByUpperId("0");
    	//获取菜单树
    	getMenuTree();
    });
    
    function rowCallback(row, data, displayIndex, displayIndexFull){
    	//alert(JSON.stringify(data));
    }
    
    //点击增加按钮触发的函数
    $("#menuAdd").click(function(){
    	
    	//二级菜单下不允许新建菜单
    	if(nowSelectedLevel>1){
    	
    		alert("二级菜单下不允许建立子菜单!");
    		return false;
    	}
    	
    	$('#menuAddModal').modal("show");
    });
    
    
  //点击修改按钮触发的函数
    $("#menuUpdate").click(function(){
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
				url : "/menu/getSelectedMenu",
				data : {pk:codeList[0].id},
				dataType:'text',
				success : function(obj) {
					obj = JSON.parse(obj);
					$("[id='menuUpdateVo.menucname']").val(obj.menucname);
					$("[id='menuUpdateVo.menuename']").val(obj.menuename);
					$("[id='menuUpdateVo.taskcode']").val(obj.taskcode);
					$("[id='menuUpdateVo.actionurl']").val(obj.actionurl);
					$("[id='menuUpdateVo.displayno']").val(obj.displayno);
					$("[id='menuUpdateVo.m_id']").val(obj.m_id);
					$('#menuUpdateModal').modal("show");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("查询失败!");
				}
				
			});
	  	
	  	
	  	
	  	
	  	
	  
  });
  
  
  	//点击删除按钮触发的函数
    $("#menuDelete").click(function(){
  	  var codeList=$("input[name=checkCode]:checked");
  	  	if(codeList==null||codeList.length==0){
  	  		alert("请选择一条数据！");
  	  		return ;
  	  	}
  	  var idList="";
	  for(var i=0;i<codeList.length;i++){
		  idList+=codeList[i].id;
		  idList+=",";
	  }
  	  	

  	  	
  	  	if(confirm("确认删除?")){
  	  		
  	  	 $.ajax({
				type : "POST",
				url : "/menu/deleteMenu",
				data : {idList:idList},
				dataType:'text',
				success : function(obj) {
					if(obj=="true"){
						alert("请先删除当前菜单的子菜单!");
					}else{
						//初始化查出所有的一级菜单
				    	getMenuByUpperId(nowSelectedPId);
				    	//获取菜单树
				    	getMenuTree();
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
    $("#updateMenu").click(function(){
    	//非空校验
    	var mcname = $("[id='menuUpdateVo.menucname']").val();
    	if(mcname==""||mcname==null||mcname==undefined){
    		alert("菜单中文名不允许为空!");
    		return false;
    	}
    	
    	var tcode = $("[id='menuUpdateVo.taskcode']").val();
    	if(tcode==""||tcode==null||tcode==undefined){
    		alert("资源编码不允许为空!");
    		return false;
    	}
    	
   	 	$.ajax({
			type : "POST",
			url : "/menu/updateMenu",
			data:$('#menuUpdateForm').serialize(),
			dataType:'text',
			success : function(obj) {
				alert("修改成功!");
				//初始化查出所有的一级菜单
		    	getMenuByUpperId(nowSelectedPId);
		    	//获取菜单树
		    	getMenuTree();
		    	//隐藏model
		    	$('#menuUpdateModal').modal('hide');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("修改失败!");
				
			}
			
		});
    	
    	
    	
    	
    	
    });
    
    
    //点击弹出框中的保存按钮触发的函数
    $("#saveMenu").click(function(){
    	
    	//非空校验
    	var mcname = $("[id='menuAddVo.menucname']").val();
    	if(mcname==""||mcname==null||mcname==undefined){
    		alert("菜单中文名不允许为空!");
    		return false;
    	}
    	
    	var tcode = $("[id='menuAddVo.taskcode']").val();
    	if(tcode==""||tcode==null||tcode==undefined){
    		alert("资源编码不允许为空!");
    		return false;
    	}
    	
    	 //设置父Id
    	 $("#menuAddVoupperid").val(nowSelectedPId);
    	 $.ajax({
				type : "POST",
				url : "/menu/addMenu",
				data:$('#menuAddForm').serialize(),
				dataType:'text',
				success : function(obj) {
					alert("保存成功!");
					//初始化查出所有的一级菜单
			    	getMenuByUpperId(nowSelectedPId);
			    	//获取菜单树
			    	getMenuTree();
			    	//隐藏model
			    	$('#menuAddModal').modal('hide');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("保存失败!"+errorThrown);
					
				}
				
			});
  	  
    });
  	
</script>




</body>
</html>