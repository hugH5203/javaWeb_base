package dao;

import JDBCUtils.JDBCUtils;
import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author HuangHai
 * @date 2020/5/17 14:28
 */
public class UserDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.get_druid_DataSource());

    /**
     * 登录方法
     *
     * @param loginUser 只有用户名与密码
     * @return user包含用户所有数据
     */
    public User login(User loginUser) {
        try {
            User user = template.queryForObject("select * from admin where username=? and password=?",
                    new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("查不到");
            return null;
        }
    }
    }