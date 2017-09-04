<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>账单列表</title>
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
                <h3 class="box-title">账单列表</h3>
            </div>

            <div class="box-body">
                <table id="fzaccTable"
                       class="table table-bordered table-hover table-striped">
                    <thead>
                    <tr>
                        <th style="text-align: center"><input id="checkAll"
                                                              type="checkbox" onclick="selectAllcheck()"></th>
                        <th style="text-align: center">状态</th>
                        <th style="text-align: center">转财务时间</th>
                        <th style="text-align: center">账单编号</th>
                        <th style="text-align: center">分入/分出</th>
                        <th style="text-align: center">临分/合约</th>
                        <th style="text-align: center">类型</th>
                        <th style="text-align: center">机构</th>
                        <th style="text-align: center">合约号</th>
                        <th style="text-align: center">分项</th>
                        <th style="text-align: center">业务号</th>
                        <th style="text-align: center">生成人</th>
                        <th style="text-align: center">生成时间</th>
                        <th style="text-align: center">更新人</th>
                        <th style="text-align: center">更新时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <div style="display: none">
                    <form id="accInfoForm" name="accInfoForm" role="form">
                        <input type="text" class="form-control" id="dealFlag" name="dealFlag"
                               placeholder="dealflag" />
                    </form>
                </div>
            </div>
            <!-- /.box-body -->

            <div class="box-footer">
                <div class="row">
                    <div class="col-xs-6">
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
                return '<input name="checkCode" type="checkbox" id="'+data.accNo+'">';
            }

        },
        {
            "data" : "dealFlag",
            "orderable" : false,
            "class" : "center",
            "render":function(data, type, row) {
                var flag = row.dealFlag.substring(0,1);
                if(flag=='0'){
                    return "未处理";
                }else if(flag=='1'){
                    return "已结算";
                }else if(flag=='2'){
                    return "转财务";
                }else{
                    return "其他";
                }
            }
        },
        {
            "data" : "accPayTime",
            "class" : "center",
            "orderable" : false,
            "render":function(data, type, row) {
                return new Date(data).Format("yyyy-MM-dd HH:mm:ss");
            }
        },
        {
            "data" : "accNo",
            "orderable" : false,
            "class" : "center"
        },
        {
            "data" : "optType",
            "class" : "center",
            "orderable" : false,
            "render":function(data, type, row) {
                var ot = row.optType;
                if(ot=='0'){
                    return "分出";
                }else if(ot=='1'){
                    return "分入";
                }else{
                    return "其他";
                }
            }
        },
        {
            "data" : "accType",
            "class" : "center",
            "orderable" : false,
            "render":function(data, type, row) {
                var ot = row.accType;
                if(ot=='11'){
                    return "合约";
                }else if(ot=='10'){
                    return "临分";
                }else{
                    return "其他";
                }
            }
        },

        {
            "data" : "accKind",
            "class" : "center",
            "orderable" : false,
            "render":function(data, type, row) {
                var ot = row.accKind;
                if(ot=='P'){
                    return "保单";
                }else if(ot=='E'){
                    return "批单";
                }else if(ot=='C'){
                    return "赔款";
                }else if(ot=='D'){
                    return "赔款";
                }else if(ot=='A'){
                    return "保费";
                }else if(ot=='I'){
                    return "预付";
                }else if(ot=='F'){
                    return "调整";
                }else if(ot=='H'){
                    return "超赔";
                }else if(ot=='G'){
                    return "合约";
                }else{
                    return "其他";
                }
            }
        },
        {
            "data" : "comCode",
            "class" : "center",
            "orderable" : false
        },
        {
            "data" : "treatyId",
            "class" : "center",
            "orderable" : false
        },
        {
            "data" : "sectionNo",
            "class" : "center",
            "orderable" : false
        },
        {
            "data" : "recertifyNo",
            "class" : "left",
            "orderable" : false
        },
        {
            "data" : "createrCode",
            "class" : "center",
            "orderable" : false
        },
        {
            "data" : "createDate",
            "class" : "center",
            "orderable" : false,
            "render":function(data, type, row) {
                return new Date(data).Format("yyyy-MM-dd HH:mm:ss");
            }
        },
        {
            "data" : "updaterCode",
            "class" : "center",
            "orderable" : false
        },
        {
            "data" : "updateDate",
            "class" : "center",
            "orderable" : false,
            "render":function(data, type, row) {
                return new Date(data).Format("yyyy-MM-dd HH:mm:ss");
            }
        }
    ];





    //菜单的json格式
    $(function () {
        //
        getDataTable();
    });






    //填充datatable
    function getDataTable(){

        $("#dealFlag").val(${requestScope.typo});
        //加载datatable
        var ajaxList = new AjaxList("#fzaccTable");
        ajaxList.targetUrl = '${pageContext.request.contextPath}' + '/monitor/getFzAccPages';
        ajaxList.columns = columns;
        //ie 8 以上根据原有方法无法获取form的id，需要提供
        ajaxList.formId = "accInfoForm";
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