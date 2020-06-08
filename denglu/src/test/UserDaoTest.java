package test;

import dao.UserDao;
import domain.User;
import org.junit.Test;

/**
 * @author HuangHai
 * @date 2020/5/17 15:30
 */
public class UserDaoTest {
@Test
public void testLogin(){
    User login=new User(1,"hh","123");
    User user = new UserDao().login(login);
    System.out.println(user);
}
}
