<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>用户管理</title>
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
					<h3 class="box-title">用户</h3>
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
					<h3 class="box-title">用户</h3>
				</div>

				<div class="box-body">
					<table id="usertable"
						class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th style="text-align: center"><input id="checkAll"
									type="checkbox" onclick="selectAllcheck()"></th>
								<th style="text-align: center">用户代码</th>
								<th style="text-align: center">用户名称</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div style="display: none">
						<form id="userForm" name="userForm" role="form">
							<input type="text" class="form-control" id="gid" name="gid"
								placeholder="岗位ID" />
						</form>
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-2">
							<button type="button" class="btn btn-block btn-info" id="userAdd">增加</button>
						</div>
						<div class="col-xs-2">
							<button type="button" class="btn btn-block btn-info"
								id="userUpdate">修改</button>
						</div>
						<div class="col-xs-2">
							<button type="button" class="btn btn-block btn-info"
								id="userDelete">删除</button>
						</div>
						<div class="col-xs-2">
							<button type="button" class="btn btn-block btn-info"
								id="clearCache">清除缓存</button>
						</div>
					</div>


				</div>

			</div>
		</div>
		<div class="col-xs-1"></div>


		<div class="modal fade" id="userAddModal" tabindex="-1" role="dialog"
			aria-labelledby="userAddModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">x</button>
						<h4 class="modal-title" id="userAddModalLabel">新增用户</h4>
					</div>
					<div class="modal-body">

						<form role="form" id="userAddForm">
							<div class="box-body">
								<div class="form-group">
									<label >用户代码<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="userAddVo.usercode" name="usercode" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group">
									<label>用户名称<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="userAddVo.username" name="username" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group" >
									<label>用户密码<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="password" id="userAddVo.userpwd" name="userpwd" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group" >
									<label>确认密码<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="password" id="userAddVo.userpwdconfirm" name="userpwdconfirm" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group" style="display: none">
									<label>岗位ID<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="password" id="userAddVo.gradeid" name="gradeid" class="form-control" placeholder="请输入 ...">
								</div>
							</div>
							<!-- /.box-body -->
							<div class="box-footer">
								<button type="button" class="btn btn-primary" id="saveUser">保存</button>
							</div>
						</form>


					</div>
				</div>
			</div>
		</div>
		
		
		<div class="modal fade" id="userUpdateModal" tabindex="-1" role="dialog"
			aria-labelledby="userUpdateModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">x</button>
						<h4 class="modal-title" id="userUpdateModalLabel">修改用户</h4>
					</div>
					<div class="modal-body">

						<form role="form" id="userUpdateForm">
							<div class="box-body">
								<div class="form-group">
									<label >用户代码<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="userUpdateVo.usercode" name="usercode" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group">
									<label>用户名称<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="userUpdateVo.username" name="username" class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group" style="display:none">
									<label>原始密码<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="userUpdateVo.oldpwd" name=oldpwd class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group" >
									<label>新密码<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="password" id="userUpdateVo.userpwd" name=userpwd class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group" >
									<label>确认密码<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="password" id="userUpdateVo.userpwdconfirm" name=userpwdconfirm class="form-control" placeholder="请输入 ...">
								</div>
								<div class="form-group" style="display:none">
									<label>U_ID<i class="fa fa-fw fa-exclamation-circle"></i></label>
                  					<input type="text" id="userUpdateVo.u_id" name=u_id class="form-control" placeholder="请输入 ...">
								</div>
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<button type="button" class="btn btn-primary" id="updateUser">修改</button>
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
	        				return '<input name="checkCode" type="checkbox" id="'+data.u_id+'">';
	        			}
	            
	               },
	        	   {
	        		"data" : "usercode",
	        		"orderable" : false,
	        		"class" : "center"
	        	   },
	        	   {
	           		"data" : "username",
	           		"orderable" : false,
	           		"class" : "center"
	           	   }
	        	  ];
	
   

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
                pIdKey: "parentId",
                rootPId: ""
        
            }
        },
        callback: {
            onClick:gradeUserOnClick
        }
    };
    
    //当前节点的task_code
    var nowSelectedCode="0";
    //当前选中的树层级
    var nowSelectedLevel = "0";
    //单击菜单后在datagrid中显示菜单下的子菜单
    function gradeUserOnClick(event, treeId, treeNode, clickFlag) {
    	nowSelectedCode = treeNode.id;
    	nowSelectedLevel = treeNode.level;
    	getTaskByParentCode(treeNode.id);
    }
    
    //根据上级id查询菜单 并放置到datatable中
    function getTaskByParentCode(parentCode){
    	//当选中的是岗位
    	if(nowSelectedLevel =="1" ){
    		$("#gid").val(nowSelectedCode);
    	}else{
    		$("#gid").val("");
    	}
    	//加载datatable
        var ajaxList = new AjaxList("#usertable");
        ajaxList.targetUrl = '${pageContext.request.contextPath}' + '/user/queryUser';
        ajaxList.columns = columns;	
    	//ie 8 以上根据原有方法无法获取form的id，需要提供
    	ajaxList.formId = "userForm";
    	ajaxList.rowCallback = rowCallback;
    	ajaxList.query();
    }
    
    
    //查询菜单树
    function getGradeUserJson(){
   	 $.ajax({
				type : "POST",
				url : "/user/getGradeUserJson",
				dataType:'text',
				success : function(obj) {
					var t = $("#tree");
			        t = $.fn.zTree.init(t, setting, eval("("+obj+")"));
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("获取用户树失败!");
				}
				
			});
    }
    
    //隐藏弹出框
    function hideAllMode(){
    	$('#userAddModal').modal('hide');
    	$('#userUpdateModal').modal('hide');
    }
    
    
    //菜单的json格式
    $(function () {
    	//默认隐藏弹出框
    	hideAllMode();
    	//初始化查出所有的岗位
    	getTaskByParentCode("gqis");
    	//获取菜单树
    	getGradeUserJson();
    });
    
    function rowCallback(row, data, displayIndex, displayIndexFull){
    	//alert(JSON.stringify(data));
    }
    
    //点击增加按钮触发的函数
    $("#userAdd").click(function(){
    	if(nowSelectedLevel=="0"){
			alert("不能在根节点下增加用户,请选择一个岗位!");
    		return;
    	}
    	if(nowSelectedLevel=="2"){
			alert("不能在用户下增加用户,请选择一个岗位!");
    		return;
    	}
    	$('#userAddModal').modal("show");
    });
    
    
  //点击修改按钮触发的函数
    $("#userUpdate").click(function(){
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
				url : "/user/getSelectedUser",
				data : {u_id:codeList[0].id},
				dataType:'text',
				success : function(obj) {
					obj = JSON.parse(obj);
					$("[id='userUpdateVo.usercode']").val(obj.usercode);
					$("[id='userUpdateVo.username']").val(obj.username);
					$("[id='userUpdateVo.u_id']").val(obj.u_id);
					$('#userUpdateModal').modal("show");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("查询失败!");
				}
				
			});
	  	
	  	
	  	
	  	
	  	
	  
  });
  
  
  	//点击删除按钮触发的函数
    $("#userDelete").click(function(){
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
				url : "/user/deleteUser",
				data : {idList:idList},
				dataType:'text',
				success : function(obj) {
					getTaskByParentCode(nowSelectedCode);
			    	//获取菜单树
			    	getGradeUserJson();
					alert("删除成功!");
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("删除失败!");
				}
				
			});
  	  	}else{
  	  		
  	  	}
    });
  	
    //点击弹出框中的修改按钮触发的函数
    $("#updateUser").click(function(){
    	//非空校验
    	var ucode = $("[id='userUpdateVo.usercode']").val();
    	if(ucode==""||ucode==null||ucode==undefined){
    		alert("用户代码不允许为空!");
    		return false;
    	}
    	var uname = $("[id='userUpdateVo.username']").val();
    	if(uname==""||uname==null||uname==undefined){
    		alert("用户名称不允许为空!");
    		return false;
    	}
    	
    	var upwd = $("[id='userUpdateVo.userpwd']").val();
    	if(upwd==""||upwd==null||upwd==undefined){
    		alert("用户密码不允许为空!");
    		return false;
    	}
    	
    	//判断密码的一致性
    	var upwdconfirm = $("[id='userUpdateVo.userpwdconfirm']").val();
    	if(upwd!=upwdconfirm){
    		alert("两次密码不一致!");
    		return false;
    	}
    	
   	 	$.ajax({
			type : "POST",
			url : "/user/updateUser",
			data:$('#userUpdateForm').serialize(),
			dataType:'text',
			success : function(obj) {
				
				if(obj=='0'){
					alert("修改成功!");
					//初始化查出所有的一级菜单
			    	getTaskByParentCode(nowSelectedCode);
			    	//获取菜单树
			    	getGradeUserJson();
				}else{
					alert("修改失败:用户代码重复!");
					
				}
		    	//隐藏model
		    	$('#userUpdateModal').modal('hide');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("修改失败!");
				
			}
			
		});
    	
    	
    	
    	
    	
    });
 
    //点击弹出框中的保存按钮触发的函数
    $("#saveUser").click(function(){
    	
    	//非空校验
    	var ucode = $("[id='userAddVo.usercode']").val();
    	if(ucode==""||ucode==null||ucode==undefined){
    		alert("用户代码不允许为空!");
    		return false;
    	}
    	var uname = $("[id='userAddVo.username']").val();
    	if(uname==""||uname==null||uname==undefined){
    		alert("用户名称不允许为空!");
    		return false;
    	}
    	
    	var upwd = $("[id='userAddVo.userpwd']").val();
    	if(upwd==""||upwd==null||upwd==undefined){
    		alert("用户密码不允许为空!");
    		return false;
    	}
    	
    	//判断密码的一致性
    	var upwdconfirm = $("[id='userAddVo.userpwdconfirm']").val();
    	if(upwd!=upwdconfirm){
    		alert("两次密码不一致!");
    		return false;
    	}
    	
    	 //设置父Id
    	 $("[id='userAddVo.gradeid']").val(nowSelectedCode);
    	 $.ajax({
				type : "POST",
				url : "/user/addUser",
				data:$('#userAddForm').serialize(),
				dataType:'text',
				success : function(obj) {
					
					//0:保存成功 1:功能代码重复
					if(obj=='0'){
						alert("保存成功!");
						//初始化查出所有的一级菜单
				    	getTaskByParentCode(nowSelectedCode);
				    	//获取菜单树
				    	getGradeUserJson();
				    	
					}else{
						alert("保存失败:用户代码重复!");
					}
					
					//隐藏model					
					$('#userAddModal').modal('hide');
					
					
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("保存失败!");
					
				}
				
			});
  	  
    });
  	
    
    //清除缓存 清除shiro的缓存
      $("#clearCache").click(function(){
      	
    	  $.ajax({
				type : "POST",
				url : "/user/clearCache",
				dataType:'text',
				success : function(obj) {
					alert("清除成功");					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("清除失败!");
					
				}
				
			});
    	  
    	  
    	  
      });
</script>




</body>
</html>