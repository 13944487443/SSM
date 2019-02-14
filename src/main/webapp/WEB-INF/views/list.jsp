<%--
  Created by IntelliJ IDEA.
  User: Mr.DayDream
  Date: 2019/2/13
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/resource/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/bootstrap-table.css">
    <link rel="stylesheet" href="/resource/css/bootstrap-editable.css">
    <link rel="stylesheet" href="/resource/css/bootstrap-table.min.css">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="/resource/js/bootstrap.min.js"></script>
    <script src="/resource/js/ga.js"></script>
    <script src="/resource/js/bootstrap-table.js"></script>
    <script src="/resource/js/bootstrap-table.min.js"></script>
    <script src="/resource/js/locale/bootstrap-table-zh-CN.js"></script>
    <script src="/tests.js"></script>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <a href="person/add">新增</a>
            <a href="#" id="edit">编辑</a>
            <a href="#" id="delete">删除</a>
        </div>
    </div>
    <div class="row">
        <table class="table table-condensed table-bordered table-hover" id="listTable"
               data-mobile-responsive="true">
            <thead>
            <tr>
                <th data-radio="true"></th>
                <th data-field="id">ID</th>
                <th data-field="name">名称</th>
                <th data-field="sex">性别</th>
                <th data-field="age">年龄</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
<script>
    $(function () {
        $('#listTable').bootstrapTable({
            url: 'json_list',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            pagination: true,                   //是否显示分页（*）
            dataType:'json',
            pageNumber: 1,                              //当前第几页
            pageSize: 10,
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pagination:true,
            pageList: [10, 25, 50, 100],      //可供选择的每页的行数（*）
            height:400,
            queryParamsType:'',
            queryParames:queryParamsByBegin
        });

        $('#edit').click(function () {
            if($('#listTable').bootstrapTable('getSelections').length!=0){
                window.location.href="person/edit?id="+$('#listTable').bootstrapTable('getSelections')[0].id;
            }else {
                alert("没有选中项");
            }

        });

        $('#delete').click(function () {
            if($('#listTable').bootstrapTable('getSelections').length!=0){
                $.ajax({
                    type:'get',
                    url:'person/delete?id='+$('#listTable').bootstrapTable('getSelections')[0].id,
                    async:false,
                    success:function (data) {
                        if(data=='success'){
                            alert('删除成功');
                            $("#listTable").bootstrapTable('refresh');
                        }else {
                            alert('删除失败');
                        }
                    }

                })
            }else {
                alert("没有选中项");
            }
        })

    });
    function queryParamsByBegin(params){
        return{
            pageSize: params.pageSize,
            pageNumber: params.pageNumber
        }
    }

</script>
</html>
