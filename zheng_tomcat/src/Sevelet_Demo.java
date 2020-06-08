import javax.servlet.*;
import java.io.IOException;

/**
 * @author HuangHai
 * @date 2020/5/13 13:20
 */
public class Sevelet_Demo implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("我是初始化方法，只会加载一次哦");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }


//提供服务的方法
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("hello,Servlet");
    }

    @Override
    public String getServletInfo() {

        return null;
    }

    @Override
    public void destroy() {
        System.out.println("我是销毁方法，只有销毁的时候回调用");
    }
}
