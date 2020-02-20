$(function () {
    //获取今天的考勤数据
    loading()
    $.ajax({
        url: "/attendance/data",
        method: "get",
        dataType: "json",
        success: function (res) {

            //    显示保存成功
            setAllFormInformation(res)
            showPopUp("success", "接收消息成功")
        },
        error: function (e) {
            errorHandler()
        },
        complete:function () {
            unloding()
        }

    })
    // 获取历史考勤数据，绘制图表
    showHistoryData()

    //添加监听
    $("#save").click(function () {
        if (validate()) {
            sendData("/attendance/makeaattendance", showHistoryData)
        }
    })

    $("#logOut").click(function () {
        loading()
        $.ajax("/log/out", {
            dataType: "json",
            success: function (res) {
                if (res.code == "0") {
                    window.location.href = "/log"
                } else {
                    showPopUp("warn", "注销失败！")
                }
            },
            error: function (res) {
                showPopUp("error", "登录已失效，请重新登录!")
                setTimeout(function () {
                    window.location.href = "/log"
                }, 1000)
            },
            complete:function () {
                unloding()
            }
        })
    })

//    日历生成
    var date = new Date()
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: "yyyy/mm/dd",
        initialDate: date
    }).on('changeDate', function (ev) {

        var selectDate = new Date(getCalendarDate())
        if (getDateString(selectDate) == "1970/1/1") {
            setAllFormInformation(null)
            return
        }
        var dateStr = selectDate.getFullYear() + "-" + (selectDate.getMonth() + 1) + "-" + selectDate.getDate()
        $.ajax("/attendance/history/oneday", {
            method: "post",
            data: {
                date: dateStr
            },
            dataType: "json",
            success: function (res) {
                setAllFormInformation(res)
            },
            error: function re(res) {
                errorHandler()
            }
        })
    });

    $('input.form-control').val(getDateString(date))

})

function getConstant(percentage) {
    var map = {
        "极差": "0",
        "差": "1",
        "一般": "2",
        "优秀": "3"
    }
    return map[percentage]
}

function getCharcters(status) {
    var map = {
        "0": "极差",
        "1": "差",
        "2": "一般",
        "3": "优秀"
    }
    return map[status]
}

function setPercentageOfTask(percentage) {
    $("#percentage").val(percentage)
        .attr("constant", getConstant(percentage))
}

//检查所有填写项
function validate() {
    return true
}

// 显示已保存的内容
function setAllFormInformation(jsonObj) {
    if (jsonObj) {
        if (jsonObj.task) {
            $("#text1").val(jsonObj.task)
        }else{
            $("#text1").val("")
        }
        if (jsonObj.activity) {
            $("#text2").val(jsonObj.activity)
        }else{
            $("#text2").val("")
        }
        if (jsonObj.status) {
            $("#percentage").val(getCharcters(jsonObj.status))
        }else{
            $("#percentage").val("")
        }
    } else {
        $("#text1").val("")
        $("#text2").val("")
        $("#percentage").val("")
    }
}

// 获取所有表单数据
function getAllData() {
    var date = getCalendarDate()

    return {
        task: $("#text1").val(),
        activity: $("#text2").val(),
        status: $("#percentage").attr("constant"),
        date: date == null ? getDateString(new Date()) : getDateString(date)
    }
}

// 发送所有数据
function sendData(url, callback) {
    loading()
    const data = JSON.stringify(getAllData())
    $.ajax({
        url: url,
        data: data,
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function (res) {

            if (callback) {
                callback()
            }

//    显示保存成功
            showPopUp("success", "保存成功")
        },
        error: function (e) {

        },
        complete:function(){
          unloding()
        },
        method: "post"
    })
}

function showPopUp(type, text) {
    const map = {
        "warn": "#pwarn",
        "success": "#psuccess",
        "error": "#perror",
        "info": "#pinfo"
    }
    $(map[type]).text(text)
        .css("display", "block")

    setTimeout(function () {
        $(map[type]).hide()
    }, 1500)
}

function errorHandler() {
//检查cookie是否过期
    if (!document.cookie.match(/ *loginUser* /)) {
        showPopUp("warn", "登录状态已失效，请重新登录。")
        window.location.href = "/log"
    }

    showPopUp("error", "发送失败，请联系管理员！")
}

function showChart(datas) {
    var dates = []
    var series = [{
        name: '完成度',
        type: 'bar',
        data: [],
        barMinHeight: 5,
        label: {
            backgroundColor: '#123234'

        },
        itemStyle: {
            normal: {
                color: '#4ad2ff'
            }
        },
    }]

    var legend = {
        data: ['完成度']
    }

    for (var index in datas) {
        var data = datas[index]
        dates.push(data.date)
        series[0].data.push(getCharcters(data.status))
    }

    var myChart = echarts.init(document.getElementById('chart'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '历史目标'
        },
        tooltip: {
            formatter: '{c}'
        },
        legend: legend,
        xAxis: {
            type: "category",
            data: dates,
            max: 13,
            axisLabel: {
                interval: 0,
                rotate: 30
            },
        },
        yAxis: {
            type: "category",
            data: ["极差", "差", "一般", "优秀"],
            scale: true,
        },
        series: series
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}


function showHistoryData() {
    loading()
    $.ajax({
        url: "/attendance/history/data",
        method: "get",
        dataType: "json",
        success: function (res) {
            showChart(res);
        },
        error: function (e) {
            errorHandler()
        },
        complete:function () {
            unloding()
        }
    })
}

function getCalendarDate() {
    return $(".form_date").data("datetimepicker").getDate()
}

function getDateString(date) {
    return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate()
}

function loading() {
    $("#mask").css("display", "flex")

}
function unloding() {
    $("#mask").css("display", "none")
}