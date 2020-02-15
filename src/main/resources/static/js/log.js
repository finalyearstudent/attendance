$(function() {

   $(".input input").focus(function() {

      $(this).parent(".input").each(function() {
         $("label", this).css({
            "line-height": "18px",
            "font-size": "18px",
            "font-weight": "100",
            "top": "0px"
         })
         $(".spin", this).css({
            "width": "100%"
         })
      });
   }).blur(function() {
      $(".spin").css({
         "width": "0px"
      })
      if ($(this).val() == "") {
         $(this).parent(".input").each(function() {
            $("label", this).css({
               "line-height": "60px",
               "font-size": "24px",
               "font-weight": "300",
               "top": "10px"
            })
         });

      }
   });

   $("#login").click(function(e) {
      that = this

      //获取表单数据加密
      const account = md5($("#account").get(0).value)
      const password = md5($("#password").get(0).value)

      $.ajax({
         contentType : "application/x-www-form-urlencoded; charset=UTF-8",
         dataType : "json",
         data : {
            account : account,
            password : password
         },
         url : "/log/user",
         success : function (res) {
            console.log(res)
            if(res.code == "0"){
               changeBtn(that, e)
               window.location.href = "/index"
            }else{
               alert(res.info)
            }
         },
         error : function (res) {
            alert("登录失败，请重试！")
         },
         method : "post"
      })


   })

   $("#register").click(function(e) {
      that = this

      //获取表单数据加密
      const account = md5($("#regname").get(0).value)
      const password = md5($("#regpass").get(0).value)
      const cpassword = md5($("#reregpass").get(0).value)

      // 两次密码不一致
      if(password != cpassword){
         $("#reregpass").focus()
         return
      }

      $.ajax({
         contentType : "application/json; charset=UTF-8",
         dataType : "json",
         data : JSON.stringify({
            "account" : account,
            "password" : password})
         ,
         url : "/register/newaccount",
         success : function (res) {
            if(res.code == 0){
               alert("注册成功")
               changeBtn(that, e)
               window.location.href = "/index"
            }else{
              alert(res.info)
            }
         },
         error : function (res) {
            alert("注册失败，请重试！")
         },
         method : "post"
      })
   })

   function changeBtn(that, e){
      // 点击按钮后的特效
      var pX = e.pageX,
          pY = e.pageY,
          oX = parseInt($(that).offset().left),
          oY = parseInt($(that).offset().top);

      $(that).append('<span class="click-efect x-' + oX + ' y-' + oY + '" style="margin-left:' + (pX - oX) + 'px;margin-top:' + (pY - oY) + 'px;"></span>')
      $('.x-' + oX + '.y-' + oY + '').animate({
         "width": "500px",
         "height": "500px",
         "top": "-250px",
         "left": "-250px",

      }, 600);
      $("button", that).addClass('active');
   }

   $(".alt-2").click(function() {
      if (!$(this).hasClass('material-button')) {
         $(".shape").css({
            "width": "100%",
            "height": "100%",
            "transform": "rotate(0deg)"
         })

         setTimeout(function() {
            $(".overbox").css({
               "overflow": "initial"
            })
         }, 600)

         $(this).animate({
            "width": "140px",
            "height": "140px"
         }, 500, function() {
            $(".box").removeClass("back");

            $(this).removeClass('active')
         });

         $(".overbox .title").fadeOut(300);
         $(".overbox .input").fadeOut(300);
         $(".overbox .button").fadeOut(300);

         $(".alt-2").addClass('material-buton');
      }

   })

   $(".material-button").click(function() {

      if ($(this).hasClass('material-button')) {
         setTimeout(function() {
            $(".overbox").css({
               "overflow": "hidden"
            })
            $(".box").addClass("back");
         }, 200)
         $(this).addClass('active').animate({
            "width": "700px",
            "height": "700px"
         });

         setTimeout(function() {
            $(".shape").css({
               "width": "50%",
               "height": "50%",
               "transform": "rotate(45deg)"
            })

            $(".overbox .title").fadeIn(300);
            $(".overbox .input").fadeIn(300);
            $(".overbox .button").fadeIn(300);
         }, 700)

         $(this).removeClass('material-button');

      }

      if ($(".alt-2").hasClass('material-buton')) {
         $(".alt-2").removeClass('material-buton');
         $(".alt-2").addClass('material-button');
      }

   });

});