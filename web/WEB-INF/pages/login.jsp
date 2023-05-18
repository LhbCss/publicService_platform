<%--
  Created by IntelliJ IDEA.
  User: 84623
  Date: 13/4/2023
  Time: 下午1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>公益服务平台 - 登录</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="login_box">
    <div class="login_l_img"><img src="${pageContext.request.contextPath}/img/login-img.png" /></div>
    <div class="login" id="app">
        <div class="login_logo"><img src="${pageContext.request.contextPath}/img/login_logo.png" /></div>
        <div class="login_name">
            <p>公益服务平台 - 后台系统登录</p>
        </div>
        <div>
            <input name="username" type="text" placeholder="用户名" v-model="admin.name">
            <input name="password" type="password" id="password" v-model="admin.password" placeholder="密码"/>
            <input style="width:100%;" @click="login" type="submit" value="登录">
            <p style="color: red;display: none" id="msg">{{login_msg}}</p>
        </div>
    </div>
</div>
<div class="copyright">广东工业大学华立学院©</div>
<div style="text-align:center;">

</div>
</body>

<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/axios-0.18.0.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>

<script>
    // 登录功能逻辑实现
    var v = new Vue({
        el: "#app",
        data(){
            return{
                admin:{
                    name:"",
                    password:""
                },
                login_msg:""
            }
        },
        methods:{
            login(){
                // 获取模型数据
                var that = this;
                // 设置相应 msg
                this.login_msg = "正在登录，请稍等...";
                document.getElementById("msg").style.display = "block";
                // 发送 axios 请求
                axios({
                    method:"post",
                    url:"adminLogin",
                    data: that.admin
                }).then(function (resp){
                    if(resp.data == "登录失败"){
                        // 通过 style 的 display 属性解决 {{login_msg}} 渲染问题，要隐藏时为 none，要显示时为 block
                        document.getElementById("msg").style.display = "block";
                        that.login_msg = "登陆失败，账号或密码错误！";
                        setTimeout("clean_login_msg()", 1000); /* 3 秒后错误提示消失 */
                    }
                    if(resp.data == "登录成功"){
                        window.location = "toMain";
                    }
                })
            }
        }
    })
    function clean_login_msg(){
        v.login_msg = "";
    }
</script>
</html>