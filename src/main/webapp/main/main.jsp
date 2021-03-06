<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="../boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="../jqgrid/css/jquery-ui.css">
    <link rel="stylesheet" href="../jqgrid/css/trirand/ui.jqgrid-bootstrap.css">

    <script src="../login/assets/js/jquery-3.3.1.min.js"></script>
    <script src="../login/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="../boot/js/ajaxfileupload.js"></script>
    <script src="../kindeditor/kindeditor-all-min.js"></script>
    <script src="../kindeditor/lang/zh-CN.js"></script>
    <script src="../echarts/echarts.min.js"></script>
    <script src="../echarts/china.js"></script>
    <script src=" http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script>
        $(function () {
            $("#myCarousel").carousel({
                interval: 3000,  //毫秒
            })
        })

    </script>


</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <h2 style="color: #9d9d9d">持明法州管理系统</h2>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <c:if test="${sessionScope.username == null}">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/login/login.jsp"><h5>登录</h5></a></li>
                </ul>
            </c:if>
            <c:if test="${sessionScope.username != null}">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><h5>欢迎管理员: <span id="dl" style="color: #55acee">${sessionScope.username}</span></h5>
                    </a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/exit"><h6>退出登录<span
                            class="glyphicon glyphicon-share"></span></h6></a></li>
                </ul>
            </c:if>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <!--左侧手风琴-->
        <div class="col-sm-2">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <a href="javascript:$('#rrr').load('${app}/main/echartsMap1.jsp')">用户地区分布图</a>

                        </div>
                        <div class="panel-body">
                            <a href="javascript:$('#rrr').load('${app}/main/echarts.jsp')">用户注册七天走势图</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                上师管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="">上师列表</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#rrr').load('${app}/main/article.jsp')">文章列表</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#rrr').load('${app}/main/album.jsp')">专辑列表</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive">
                                轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#rrr').load('${app}/main/index.jsp')">轮播图列表</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-10" id="rrr">
            <div class="jumbotron">
                <h3>欢迎来到持明法州后台管理系统</h3>
            </div>
            <!--右侧默认轮播图-->
            <div id="myCarousel" class="carousel slide">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="../img/shouye.jpg" style="width:100%" alt="Second slide">
                    </div>
                </div>
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container">
        <center>@百知教育baizhi@zparkhr.com.cn</center>
    </div>
</nav>
</body>
</html>