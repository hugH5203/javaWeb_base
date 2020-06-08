<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: hh176
  Date: 2020/5/25
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
    <script>
        var s = confirm(<%=request.getSession().getAttribute("username")%>+"欢迎回来");
        setTimeout(fun, 5000)

        function fun() {
            if (s) {
                open("/biaobai_520.html");
            }
        }
    </script>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();//获取所有cookie
    boolean flag = false;//一个标记，标记是否为第一次访问
    if (cookies != null && cookies.length > 0) {//不是第一次登陆
        for (Cookie cookie : cookies) {
            String name = cookie.getName();//获取cookie 的名字
            if ("lastTime".equals(name)) {//如果cookie的名字是lastTime则不是第一次登陆
                flag = true;//修改标记
                String value = cookie.getValue();//获取cookie值
                value = URLDecoder.decode(value, "utf-8");//将url的cookie值用url 解码成utf-8编码,因为日期格式里有一个空格，算作特殊字符，tomcat8虽然支持了中文，但是不支持特殊字符
                out.write("<h1>"+request.getSession().getAttribute("username") + "欢迎回来，您上次访问的时间是" + value + "</h1>");
                //response.getWriter().write("<a href=biaobai_520.html>爱你哟</a> ");//对做出回应
                Date date = new Date();//获取日期对象
                SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//获取指定格式对象
                String format = sim.format(date);//将日期对象完格式化
                String encode = URLEncoder.encode(format, "utf-8");    //对这个日期对象进行url编码
                cookie.setMaxAge(60 * 60 * 24 * 30);//设置在浏览器硬盘中缓存一个月
                cookie.setValue(encode);//将这个编码了的日期设置为cookie的值
                response.addCookie(cookie);//添加到cookie数组里去
                break;//找到想要的就不找了
            }
        }
    }
    if (cookies == null || cookies.length == 0 || flag == false) {
        Date date = new Date();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String format = sim.format(date);
        String encode = URLEncoder.encode(format, "utf-8");    //url编码
        Cookie cookie = new Cookie("lastTime", encode);
        cookie.setMaxAge(60 * 60 * 24 * 30);//能缓存一个月
        response.addCookie(cookie);
        out.write("<h1>欢迎，首次来到这的你<h1>，<a href=xindong_biaobai520.html>祝您每天都有好生活</a> ");
    }%>
</body>
</html>
