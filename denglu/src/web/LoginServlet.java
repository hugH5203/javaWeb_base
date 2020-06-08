package web;

import dao.UserDao;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HuangHai
 * @date 2020/5/17 21:05
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置获取请求的消息，为防止乱码，设置为utf8
        String username = request.getParameter("username");//获取参数值
        String password = request.getParameter("password");
        User user = new User();//进行封装
        user.setUsername(username);
        user.setPassword(password);
        UserDao userDao = new UserDao();
        User login = userDao.login(user);//传入刚刚封装的user调用login方法
        if (login != null) {//登陆成功就转发到成功界面
          request.setAttribute("user", login);
           request.getRequestDispatcher("/successServlet").forward(request, response);
        } else {//登录失败就转发到失败界面
            request.getRequestDispatcher("/failServlet").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
