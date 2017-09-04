<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<html>
<head>
    <title>登录界面</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bs/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bs/login/login.css">
    <!-- jQuery 2.2.0 -->
	<script src="${pageContext.request.contextPath}/bs/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="${pageContext.request.contextPath}/bs/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-7">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-lock"></span> Login</div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" method="post" action="login/login">
                    <div class="form-group">
                        <label for="usercode" class="col-sm-3 control-label">
                            用户名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="usercode"  name="usercode" placeholder="用户名" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userpwd" class="col-sm-3 control-label">
                            密码 </label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="userpwd" name="userpwd" placeholder="密码" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                        </div>
                    </div>
                    <div class="form-group last">
                        <div class="col-sm-offset-3 col-sm-9">
                            <button type="submit" id="loginax" class="btn btn-success btn-sm" >
                                登录</button>
                        </div>
                    </div>
                    </form>
                </div>
                <div class="panel-footer">
                	
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

$(function () {

});


</script>
</body>
</html>
