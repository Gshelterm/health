<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加病员</title>
    <%@include file="common/head.jsp"%>
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">所有病员列表</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="/health/patient/form">添加病员</a></li>
            </ul>
        </div>
    </div>
</nav>


<form role="form" onsubmit="return false" id="patientForm" method="post" >
    <div class="form-group">
        <label for="patientId">病历号</label>
        <input type="text" class="form-control" id="patientId" name="patientId"
               placeholder="请输入病历号">
    </div>
    <div class="form-group">
        <label for="pressureHigh">血压上限值</label>
        <input type="text" class="form-control" id="pressureHigh" name="pressureHigh"
               placeholder="请输入血压上限值">
    </div>
    <div class="form-group">
        <label for="pressureLow">血压下限值</label>
        <input type="text" class="form-control" id="pressureLow" name="pressureLow"
               placeholder="请输入血压下限值">
    </div>
    <div class="form-group">
        <label for="breathHigh">呼吸上限值</label>
        <input type="text" class="form-control" id="breathHigh" name="breathHigh"
               placeholder="请输入呼吸上限值">
    </div>
    <div class="form-group">
        <label for="breathLow">呼吸下限值</label>
        <input type="text" class="form-control" id="breathLow" name="breathLow"
               placeholder="请输入呼吸下限值">
    </div>
    <div class="form-group">
        <label for="temperatureHigh">体温上限值</label>
        <input type="text" class="form-control" id="temperatureHigh" name="temperatureHigh"
               placeholder="请输入体温上限值">
    </div>
    <div class="form-group">
        <label for="temperatureLow">体温下限值</label>
        <input type="text" class="form-control" id="temperatureLow" name="temperatureLow"
               placeholder="请输入体温下限值">
    </div>
    <div class="form-group">
        <label for="frequency">检测频率</label>
        <input type="text" class="form-control" id="frequency" name="frequency"
               placeholder="请输入检测频率">
    </div>
    <button class="btn btn-primary" id="patientSubmit">提交</button>
</form>
</body>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<%--${pageContext.request.contextPath} 获取当前应用的根路径--%>
<script src="${pageContext.request.contextPath}/resources/script/health.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        // 使用el表达式传入参数
        health.add.init();
    });
</script>
</html>
