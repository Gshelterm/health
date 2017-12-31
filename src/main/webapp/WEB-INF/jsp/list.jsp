<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>病人列表页</title>
    <%@include file="common/head.jsp"%>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="">所有病员列表</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="/health/patient" target="_blank">添加病员</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>病员列表</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover" id="table">
                <thead>
                <tr>
                    <th>病历号</th>
                    <th>血压</th>
                    <th>呼吸</th>
                    <th>体温</th>
                    <th>血压上限</th>
                    <th>血压下限</th>
                    <th>呼吸上限</th>
                    <th>呼吸下限</th>
                    <th>体温上限</th>
                    <th>体温下限</th>
                    <th>监测频率</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="patient" items="${list}">
                    <tr>
                        <td>${patient.patientId}</td>
                        <td>${patient.pressure}</td>
                        <td>${patient.breath}</td>
                        <td>${patient.temperature}</td>
                        <td>${patient.pressureHigh}</td>
                        <td>${patient.pressureLow}</td>
                        <td>${patient.breathHigh}</td>
                        <td>${patient.breathLow}</td>
                        <td>${patient.temperatureHigh}</td>
                        <td>${patient.temperatureLow}</td>
                        <td>${patient.frequency}</td>
                            <%--<td>--%>
                            <%--<fmt:formatDate value="${patient.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                            <%--</td>--%>
                            <%--<td>--%>
                            <%--<fmt:formatDate value="${patient.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                            <%--</td>--%>
                            <%--<td>--%>
                            <%--<fmt:formatDate value="${patient.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                            <%--</td>--%>
                        <td>
                            <a class="btn btn-info updateBound">
                                修改安全值
                            </a>
                        </td>
                        <td>
                            <button class="btn btn-info updateFrequency" type="submit">
                                修改频率
                            </button>
                        </td>
                        <td>
                            <a class="btn btn-info" href="/patient/${patient.patientId}/log">查看日志</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

<div class="modal fade" id="boundModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h3 class="modal-title" id="lineModalLabel">修改病员安全阈值</h3>
            </div>
            <div class="modal-body">

                <!-- content goes here -->
                <form id="updateBoundForm">
                    <div class="form-group">
                        <label for="pressureHigh">血压上限</label>
                        <input type="text" class="form-control" id="pressureHigh" name="pressureHigh"
                               placeholder="输入血压上限值">
                    </div>
                    <div class="form-group">
                        <label for="pressureLow">血压下限</label>
                        <input type="text" class="form-control" id="pressureLow" name="pressureLow"
                               placeholder="输入血压上限值">
                    </div>
                    <div class="form-group">
                        <label for="breathHigh">呼吸上限</label>
                        <input type="text" class="form-control" id="breathHigh" name="breathHigh"
                               placeholder="输入呼吸上限值">
                    </div>
                    <div class="form-group">
                        <label for="breathLow">呼吸下限</label>
                        <input type="text" class="form-control" id="breathLow" name="breathLow"
                               placeholder="输入呼吸下限值">
                    </div>
                    <div class="form-group">
                        <label for="temperatureHigh">体温上限</label>
                        <input type="text" class="form-control" id="temperatureHigh" name="temperatureHigh"
                               placeholder="输入体温上限值">
                    </div>
                    <div class="form-group">
                        <label for="temperatureLow">体温下限</label>
                        <input type="text" class="form-control" id="temperatureLow" name="temperatureLow"
                               placeholder="输入体温下限值">
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <div class="btn-group btn-group-justified" role="group" aria-label="group button">
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default" data-dismiss="modal"  role="button">关闭</button>
                    </div>
                    <div class="btn-group" role="group">
                        <button type="button" id="updateBoundButton" class="btn btn-default btn-hover-green" data-action="save" role="button">确认修改</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="frequencyModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    <span class="glyphicon glyphicon-time"></span>修改检测频率
                </h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" name="newFrequency" id="frequencyVal"
                               placeholder="输入时间长度单位秒(1--3600)" class="form-control">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <%--验证信息--%>
                <span id="updateFrequencyMsg" class="glyphicon"></span>
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
                <button type="button" id="updateFrequencyButton" class="btn btn-success">
                    <span class="glyphicon glyphicon-time"></span>
                    确认
                </button>
            </div>
        </div>
    </div>
</div>

</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/script/health.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        // 使用el表达式传入参数
        health.list.init();
    });
</script>
</html>