<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>主界面</title>
    <meta charset="utf-8">
</head>
<body>
<div class="row">
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">系统概况</h3>
        </div>
        <div class="box-body">


            <!-- 账单统计开始-->
            <div class="row">
                <div class="col-xs-3">

                    <div class="small-box bg-red">
                        <div class="inner">
                            <h3 id="asd">100,120,11<sup style="font-size: 20px">CNY</sup></h3>
                            <shiro:hasPermission name="admin">
                            <p>保费收入统计</p>
                            </shiro:hasPermission>
                        </div>
                        <div class="icon">
                            <i class="ion ion-stats-bars"></i>
                        </div>
                        <a href="#" class="small-box-footer" id="asd1">
                            刷新 <i class="fa fa-refresh"></i>
                        </a>
                    </div>

                </div>
                <div class="col-xs-3">

                    <div class="small-box bg-green">
                        <div class="inner">
                            <h3 id="a1sd">0<sup style="font-size: 20px">EUR</sup></h3>
                            <shiro:hasRole name="4分保">
                                <p>保费收入统计</p>
                            </shiro:hasRole>
                        </div>
                        <div class="icon">
                            <i class="ion ion-stats-bars"></i>
                        </div>
                        <a href="#" class="small-box-footer" id="as2d1">
                            刷新 <i class="fa fa-refresh"></i>
                        </a>
                    </div>

                </div>
                <div class="col-xs-3">

                    <div class="small-box bg-green">
                        <div class="inner">
                            <h3 id="asd2">12<sup style="font-size: 20px">USD</sup></h3>
                            <p>保费收入统计</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-stats-bars"></i>
                        </div>
                        <a href="#" class="small-box-footer" id="asd13">
                            刷新 <i class="fa fa-refresh"></i>
                        </a>
                    </div>

                </div>

            </div>
            <!-- 账单统计结束-->



            <!-- 账单统计开始-->
            <div class="row">
                <div class="col-xs-3">

                    <div class="small-box bg-green">
                        <div class="inner">
                            <h3 id="allacc">0<sup style="font-size: 20px">条</sup></h3>
                            <p>账单统计</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-stats-bars"></i>
                        </div>
                        <a href="#" class="small-box-footer" id="refreshacc">
                            刷新 <i class="fa fa-refresh"></i>
                        </a>
                    </div>

                </div>

                <div class="col-xs-9">
                    <div class="row">
                        <a class="btn btn-app" id="a_undeal" href="/monitor/showAccDetail/0">
                            <span class="badge bg-red" id="undealedacc">0</span>
                            <i class="fa   fa-file-o"></i> 未处理
                        </a>
                        <a class="btn btn-app" id="a_sff" href="/monitor/showAccDetail/2">
                            <span class="badge bg-yellow" id="sffedacc">0</span>
                            <i class="fa  fa-file"></i> 已转收付费
                        </a>
                    </div>
                    <div class="row">

                        <a class="btn btn-app" id="a_settle" href="/monitor/showAccDetail/1">
                            <span class="badge bg-green" id="settledacc">0</span>
                            <i class="fa fa-file-text"></i> 已转结算
                        </a>

                    </div>
                </div>
            </div>
            <!-- 账单统计结束-->

            <!-- 结算单统计开始-->
            <div class="row">
                <div class="col-xs-3">

                    <div class="small-box bg-blue">
                        <div class="inner">
                            <h3 id="allflow">0<sup style="font-size: 20px">条</sup></h3>
                            <p>结算单统计</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-stats-bars"></i>
                        </div>
                        <a href="#" class="small-box-footer" id="refreshflow">
                            刷新 <i class="fa fa-refresh"></i>
                        </a>
                    </div>

                </div>

                <div class="col-xs-9">
                    <div class="row">
                        <a class="btn btn-app" id="a_running" href="/monitor/showAccDetail/0">
                            <span class="badge bg-red" id="s_running">0</span>
                            <i class="fa   fa-file-o"></i> 执行中
                        </a>
                        <a class="btn btn-app" id="a_close" href="/monitor/showAccDetail/2">
                            <span class="badge bg-yellow" id="s_close">0</span>
                            <i class="fa  fa-file"></i> 执行完毕
                        </a>
                    </div>
                    <div class="row">

                        <a class="btn btn-app"  href="/monitor/showAccDetail/1">
                            <span class="badge bg-green" >0</span>
                            <i class="fa fa-file-text"></i> 其他
                        </a>

                    </div>
                </div>
            </div>
            <!-- 结算单统计结束-->


        </div>

    </div>
</div>

<script>

    //初始化 --begin
    $(function () {
        //refreshacc();
    });
    //初始化 --end

    //点击账单统计->刷新 触发的函数 --begin
    $("#refreshacc").click(function(){
        refreshacc();
    });
    //点击账单统计->刷新 触发的函数 --end

    //刷新账单统计 --begin
    function refreshacc(){


        $.ajax({
            type : "POST",
            url : "/monitor/getAccCondition",
            data : {},
            dataType:'json',
            success : function(obj) {
                $("#allacc").html(obj.all+'<sup style="font-size: 20px">条</sup>');
                $("#undealedacc").html(obj.undeal);
                $("#sffedacc").html(obj.sff);
                $("#settledacc").html(obj.settle);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert("查询失败!"+errorThrown);
            }

        });




    }
    //刷新账单统计 --end


    $("#asd1").click(function(){
        $.ajax({
            type : "POST",
            url : "/redis/test",
            data : {},
            dataType:'text',
            success : function(obj) {
                //alert(obj.all);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert("查询失败!"+errorThrown);
            }

        });
    });

</script>
</body>
</html>
