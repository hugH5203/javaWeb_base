package JDBCUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author HuangHai
 * @date 2020/5/17 14:48
 * JDBC工具类，使用druid连接池
 */
public class JDBCUtils {

    static DataSource ds;//声明druid的数据源对象
    static {
        Properties properties = new Properties();//一个读取配置文件的对象
        try {
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);

//创建一个指定参数的druid数据库连接池
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource get_druid_DataSource(){
        return ds;

    }
    public static Connection druid_GteConnect() throws Exception {

        //获取空闲的连接对象
        Connection   connection = ds.getConnection();
        return connection;//返回它
    }
    /*
    //测试可获取的连接数上限（在配置文件里上限是20，应该到21就会报错）
            for (int i = 1; i <=21 ; i++) {
                Connection resultSet=ds.getConnection();
                System.out.println(resultSet+"\t"+"第"+i+"个连接对象");//打印连接对象的地址
            }
    */
//归还连接(不会关闭，只会回收)
    public static void druid_close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
