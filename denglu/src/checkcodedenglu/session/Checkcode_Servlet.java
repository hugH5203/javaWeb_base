package checkcodedenglu.session;

import javafx.util.Builder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author HuangHai
 * @date 2020/5/20 12:29
 */
@WebServlet("/checkcode_Servlet")
public class Checkcode_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 200;//图片的宽
        int height = 100;//图片的高
        //在内存中画图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //美化图片
        Graphics graph = image.getGraphics();//画笔对象
        graph.setColor(Color.cyan);//设置颜色
        graph.fillRect(0, 0, width, height);//从（0,0）画到指定的高于宽

        //画边框
        graph.setColor(Color.BLACK);//重新设置边框的颜色
        graph.drawRect(0, 0, width - 1, height - 1);//从（0，0）画到指定的高宽

        //验证码中的随机数创建
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";//所有可能出现的字母与数字
        Random random = new Random();//随机数对象
        StringBuilder builder = new StringBuilder();//
        for (int x = 1; x < 6; x++) {
            int i = random.nextInt(s.length());//随机生成一个从0到(s.lengths-1)的整数，注：取不到s.length
            char c = s.charAt(i);//用i做角标达到取得随机数
            builder.append(c);//加入到该容器里面去
            graph.drawString(c + "", (width / 6) * x, height / 2);//在图片里面画字符串，位置规定好
        }
        String checkcode1 = builder.toString();//变成字符串
        System.out.println(checkcode1);//输出看看
        request.getSession().setAttribute("checkcode1", checkcode1);//存入到session 中
        //接下来画干扰线
        graph.setColor(Color.red);//干扰线红颜色
        for (int a1 = 0; a1 < 10; a1++) {//画十条干扰线
            int x1 = random.nextInt(width);//起点横坐标
            int x2 = random.nextInt(width);//终点横坐标
            int y1 = random.nextInt(height);//起点纵坐标
            int y2 = random.nextInt(height);//终点纵坐标
            graph.drawLine(x1, y1, x2, y2);//根据起点与终点的坐标画线
        }


        //将在内存中画的图片输出到浏览器上
        ImageIO.write(image, "jpg", response.getOutputStream());//参数为图片对象，图片格式，浏览器的字节输出刘


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
