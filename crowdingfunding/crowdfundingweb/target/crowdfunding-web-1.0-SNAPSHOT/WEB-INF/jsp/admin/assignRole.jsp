<%--
  Created by IntelliJ IDEA.
  User: 15229
  Date: 2020/3/5
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <%@ include file="/WEB-INF/common/css.jsp" %>
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
    </style>
</head>

<body>

<jsp:include page="/WEB-INF/common/top.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="/WEB-INF/common/sidebar.jsp"></jsp:include>
    </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form" class="form-inline">
                        <div class="form-group">
                            <label for="exampleInputPassword1">未分配角色列表</label><br>
                            <select id="leftRoleList" class="form-control" multiple size="10" style="width:250px;overflow-y:auto;">
                                <c:forEach items="${unassignRole}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="leftToRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="rightToLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="exampleInputPassword1">已分配角色列表</label><br>
                            <select id="rightRoleList" class="form-control" multiple size="10" style="width:250px;overflow-y:auto;">

                                <c:forEach items="${assignRole}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>

                            </select>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/common/js.jsp" %>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });


    $("#leftToRightBtn").click(function() {

        var leftRoleSelected = $("#leftRoleList option:selected");


        if (leftRoleSelected.length == 0){
            layer.msg("请选择后在分配!",{icon:5,time:2000});
            return false;
        }

        var str = '';
        //str = "id=4&id=5&adminid=3"
        $.each(leftRoleSelected,function (i, e) {
            var roleid = e.value;
           str+="roleId="+roleid+"&";
        });

        str+="adminId=${param.id}";

        $.ajax({
            type:"post",
            url:"${PATH}/admin/doAssign",
            data:str,
            success:function(result){
                if ("ok"==result){

                    layer.msg("分配成功",{icon:6,time:1000},function () {
                        $("#rightRoleList").append(leftRoleSelected.clone());
                        leftRoleSelected.remove();
                    });

                }else {
                    layer.msg("分配失败",{icon:5,time:1000});
                }
            }



        });

    });

    $("#rightToLeftBtn").click(function () {

       var rightRoleSelected =  $("#rightRoleList option:selected");





        if (rightRoleSelected.length == 0){
            layer.msg("请选择后在取消分配!",{icon:5,time:2000});
            return false;
        }

        var str = '';
        //str = "id=4&id=5&adminid=3"
        $.each(rightRoleSelected,function (i, e) {

            var roleid = e.value;
            str+="roleId="+roleid+"&";
        });

        str+="adminId=${param.id}";

        $.ajax({
            type:"post",
            url:"${PATH}/admin/doUnAssign",
            data:str,
            success:function(result){
                if ("ok"==result){

                    layer.msg("取消分配成功",{icon:6,time:1000},function () {
                        $("#leftRoleList").append(rightRoleSelected.clone());

                        rightRoleSelected.remove();
                    });

                }else {
                    layer.msg("取消分配失败",{icon:5,time:1000});
                }
            }



        });

    });
</script>
</body>
</html>

