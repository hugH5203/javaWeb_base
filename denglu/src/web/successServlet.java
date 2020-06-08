package web;

import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HuangHai
 * @date 2020/5/17 21:20
 */
@WebServlet("/successServlet")
public class successServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//老样子先设置字符编码，再写话
        User user =(User) request.getAttribute("user");//获取共享数据
        if (user!=null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("登录成功，" + user.getUsername() + ",欢迎您"+"\t");
            response.getWriter().write("<a href=biaobai_520.html>表白效果</a>  ");
            response.getWriter().write("<a href=the_sky_huojian.html>蓝天火箭</a>   ");
            response.getWriter().write("<a href=the_water_ball.html>水球</a>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
this.doPost(request,response);
    }
}
