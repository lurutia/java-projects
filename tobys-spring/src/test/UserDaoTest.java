package test;

import dao.UserDao;
import dao.module.DaoFactory;
import model.User;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserDaoTest {
    public static void main(String[] args) {
        JUnitCore.main("test.UserDaoTest");
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.delete();

        User user = new User();
        user.setId("2");
        user.setName("user02");
        user.setPassword("12345");

        userDao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = userDao.get(user.getId());
        user2.setName("user03");

        assertThat(user2.getName(), is(user.getName()));
        assertThat(user2.getPassword(), is(user.getPassword()));
//        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
//        System.out.println("Connection counter : " + ccm.getCounter());
    }
}
