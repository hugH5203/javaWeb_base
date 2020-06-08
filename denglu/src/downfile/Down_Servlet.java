package downfile;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author HuangHai
 * @date 2020/5/21 20:48
 */
@WebServlet("/Down_Servlet")
public class Down_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String filename  = request.getParameter("filename");//获取参数中的文件名
        ServletContext servletContext = this.getServletContext();//获取web对象
        String realPath = servletContext.getRealPath("img/" + filename);//获取要上传的图片的绝对路径
        FileInputStream fileInputStream=new FileInputStream(realPath);//根据绝对路径用字节流来读取
        String mimeType = servletContext.getMimeType(filename);//获取该文件类型对应mime响应头类型
        response.setContentType(mimeType);//告诉浏览器的搜索引擎用对应的mime类型去解析它
        String agent = request.getHeader("user-agent");//获取浏览器的类型
        filename = DownLoadUtils.getFileName(agent, filename);//解决中文名乱码问题,让对应的浏览器使用对应的文件名编码
        response.setHeader("content-disposition","attachment;filename="+filename);//让浏览器使用弹窗模式，告诉浏览器弹出的数据类型，与对应的文件名
        //最后读取写入到浏览器要的位置
        ServletOutputStream outputStream = response.getOutputStream();//服务b器响应的字节流
        byte[] bytes=new  byte[1024*8];//定义一个缓冲区
        int len=0;
        while ((len=fileInputStream.read())!=-1){//读取写入
            outputStream.write(bytes,0,len);
        }
        fileInputStream.close();//关闭读取流，因为它是我们创建的，而写入流不用关，它是tomcat获取的，会随着服务器的关闭而关闭。




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
