package checkcodedenglu.session;

import dao.UserDao;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author HuangHai
 * @date 2020/5/25 18:28
 */
@WebServlet("/denglu_Servlet")
public class Denglu_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkcode = request.getParameter("checkbook");
        HttpSession session = request.getSession();
        String checkcode_session = (String) session.getAttribute("checkcode1");
        session.removeAttribute("checkcode1");
        User user = new User();//进行封装,数据库判断
        user.setUsername(username);
        user.setPassword(password);
        UserDao userDao = new UserDao();
        User login = userDao.login(user);//传入刚刚封装的user调用login方法
        if (checkcode_session != null && checkcode_session.equalsIgnoreCase(checkcode)) {
            if (login!=null) {//数据库中是否有该用户
                session.setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/success.jsp");//重定向用response，需要虚拟目录
            } else {
                request.setAttribute("deng_error", "用户名或密码错误");
                request.getRequestDispatcher("/checkcodedenglu.jsp").forward(request, response);//转发用request，不需要虚拟目录
            }
        } else {
            request.setAttribute("check_error", "验证码错误");
            request.getRequestDispatcher("/checkcodedenglu.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
