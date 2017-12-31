var health = {
    URL: {

    },

    list: {
        init : function () {

            $('.updateFrequency').click(function () {
                console.log('in init');
                var tr = $(this).closest("tr");

                var patientId = tr.find("td:eq(0)").text();
                console.log(patientId);

                health.frequency(patientId);
            });

            $('.updateBound').click(function () {
                console.log('in init');
                var tr = $(this).closest("tr");

                var patientId = tr.find("td:eq(0)").text();
                console.log(patientId);

                health.bound(patientId);
            });

        },

        getPatientId: function () {
            $('#updateFrequency').click(function () {
                var tr = $(this).closest("tr");

                var patientId = tr.find("td:eq(0)").text();
                console.log(patientId);
                return patientId;
            })
        }
    },

    add: {
        init: function () {
            console.log("add init");
            $('#patientSubmit').click(function () {
                var params = $('#patientForm').serializeArray();
                console.log(params);
                console.log("ajax send");
                $.ajax({
                    type: "POST",
                    url: "/health/patient",
                    data: params,
                    dataType : 'json',
                    success: function(result){
                        console.log(result);
                        console.log("in success")
                        if (result && result['success']) {
                            var data = result['data'];
                            console.log(data['insertSuccess']);
                            if (!data['insertSuccess']) {
                                alert(data['info']);
                            } else {
                                window.location.href = '/health/list';
                            }
                        }
                        else {
                            alert(result['error']);
                        }
                    },
                    error: function (jqXHR, exception) {
                        var msg = '';
                        if (jqXHR.status === 0) {
                            msg = 'Not connect.\n Verify Network.';
                        } else if (jqXHR.status == 404) {
                            msg = 'Requested page not found. [404]';
                        } else if (jqXHR.status == 500) {
                            msg = 'Internal Server Error [500].';
                        } else if (exception === 'parsererror') {
                            msg = 'Requested JSON parse failed.';
                        } else if (exception === 'timeout') {
                            msg = 'Time out error.';
                        } else if (exception === 'abort') {
                            msg = 'Ajax request aborted.';
                        } else {
                            msg = 'Uncaught Error.\n' + jqXHR.responseText;
                        }
                        console.log('msg:' + msg);
                    }
                });
            })
        }
    },

    // 修改频率
    frequency: function (patientId) {
        if (patientId == null) {
            alert("未获取id");
            return;
        }
        var frequencyModal = $('#frequencyModal');
        frequencyModal.modal({
            show: true,
            keyboard: false // 关闭键盘事件
        });
        $('#updateFrequencyButton').click(function () {
            var frequency = parseInt($('#frequencyVal').val());
            console.log(frequency);
            if (!isNaN(frequency) && frequency < 3600 && frequency > 1) {
                $.post(patientId+'/frequency', {"frequency": frequency}, function(result){
                    console.log(result);
                    console.log("in success")
                    if (result && result['success']) {
                        var data = result['data'];
                        console.log('updateSuccess: ' + data['updateSuccess']);
                        if (!data['updateSuccess']) {
                            alert(data['info']);
                        } else {
                            // window.location.href = '/health/list';
                        }
                    }
                    else {
                        alert(result['error']);
                    }
                    }, "json");
            } else {
                $('#updateFrequencyMsg').hide().html('<label class="label label-danger">输入值不符合规范!</label>').show(200);
            }
        })
    },

    // 修改上下限值
    bound: function (patientId) {
        if (patientId == null) {
            alert("未获取id");
            return;
        }
        var boundModal = $('#boundModal');
        boundModal.modal({
            show: true,
            keyboard: false // 关闭键盘事件
        });
        $('#updateBoundButton').click(function () {
            var params = $('#updateBoundForm').serializeArray();
            console.log(params);
            console.log(health.getFormData(params));
            $.ajax({
                type: "POST",
                url: patientId+'/bound',
                data: JSON.stringify(health.getFormData(params)),   // 传递json string格式必须用JSON.stringify
                contentType : 'application/json; charset=utf-8',
                dataType : 'json',
                success: function(result){
                    console.log(result);
                    console.log("in success")
                    if (result && result['success']) {
                        var data = result['data'];
                        console.log('updateSuccess: ' + data['updateSuccess']);
                        if (!data['updateSuccess']) {
                            alert(data['info']);
                        } else {
                            // window.location.href = '/health/list';
                        }
                    }
                    else {
                        alert(result['error']);
                    }
                },
                error: function (jqXHR, exception) {
                    var msg = '';
                    if (jqXHR.status === 0) {
                        msg = 'Not connect.\n Verify Network.';
                    } else if (jqXHR.status == 404) {
                        msg = 'Requested page not found. [404]';
                    } else if (jqXHR.status == 500) {
                        msg = 'Internal Server Error [500].';
                    } else if (exception === 'parsererror') {
                        msg = 'Requested JSON parse failed.';
                    } else if (exception === 'timeout') {
                        msg = 'Time out error.';
                    } else if (exception === 'abort') {
                        msg = 'Ajax request aborted.';
                    } else {
                        msg = 'Uncaught Error.\n' + jqXHR.responseText;
                    }
                    console.log('msg:' + msg);
                }
            });
        })
    },

    // 获取表单的json格式数据
    getFormData: function objectifyForm(formArray) {//serialize data function
        var returnArray = {};
        for (var i = 0; i < formArray.length; i++){
            if ("" != formArray[i]['value'])        // 去除值为空的部分
                returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        return returnArray;
    }
}