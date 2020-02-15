$(function () {
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
            //检查cookie是否过期
            if (!document.cookie.match(/ *loginUser* /)) {
                showPopUp("warn", "登录状态已失效，请重新登录。")
                window.location.href = "/index"
            }

            showPopUp("error", "接收信息失败，请联系管理员！")
        }

    })
    //添加监听
    $("#save").click(function () {
        if (validate()) {
            sendData("/attendance/makeaattendance")
        }
    })
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
    $("#text1").val(jsonObj.task)
    $("#text2").val(jsonObj.activity)
    $("#percentage").val(getCharcters(jsonObj.status))
}

// 获取所有表单数据
function getAllData() {
    return {
        task: $("#text1").val(),
        activity: $("#text2").val(),
        status: $("#percentage").attr("constant")
    }
}

// 发送所有数据
function sendData(url) {
    const data = JSON.stringify(getAllData())
    $.ajax({
        url: url,
        data: data,
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function (res) {
            //    显示保存成功
            showPopUp("success", "保存成功")
        },
        error: function (e) {
            //检查cookie是否过期
            if (!document.cookie.match(/ *loginUser* /)) {
                showPopUp("warn", "登录状态已失效，请重新登录。")
                window.location.href = "/index"
            }

            showPopUp("error", "发送失败，请联系管理员！")
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
    }, 3000)
}

