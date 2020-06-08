<%--
  Created by IntelliJ IDEA.
  User: hh176
  Date: 2020/5/25
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <script>
        window.onload = function () {//点击图片换验证码
            document.getElementById("img").onclick = function () {
                this.src = "/checkcode_Servlet?"+ new Date().getTime();
            }
        }
    </script>
    <style>
        .mima, .check {
            color: red;
        }
    </style>
</head>
<body>
<form action="/denglu_Servlet" method="post">
<table align="center">
    <tr>
        <td>用户名</td>
        <td><input type="text" name="username" placeholder="输入用户名"></td>
    </tr>
    <tr>
        <td>密码</td>
        <td><input type="password" name="password" placeholder="输入密码"></td>
        <td class="mima"><%=request.getAttribute("deng_error") == null ? "" : request.getAttribute("deng_error")%></td>
    </tr>
    <tr>
        <td>验证码</td>
        <td><input type="text" name="checkbook" placeholder="输入验证码"></td>
        <td class="check"><%=request.getAttribute("check_error") == null ? "" : request.getAttribute("check_error")%>
        </td>
    </tr>
    <tr>
        <td colspan="2"><img id="img" src="/checkcode_Servlet"></td>
    </tr>
    <tr>
        <td colspan="2"><input type="submit" value="登录"></td>
    </tr>
</table>
</form>
</body>
</html>
