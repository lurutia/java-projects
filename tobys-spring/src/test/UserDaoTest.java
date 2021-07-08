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

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        UserDao userDao = context.getBean("userDao", UserDao.class);
        User user = new User("user01", "유저01", "12345");
        User user2 = new User("user02", "유저02", "12345");

        userDao.delete();
        assertThat(userDao.getCount(), is(0));

        userDao.add(user);
        userDao.add(user2);
        assertThat(userDao.getCount(), is(2));

        User getUser1 = userDao.get(user.getId());
        assertThat(getUser1.getName(), is(user.getName()));
        assertThat(getUser1.getPassword(), is(user.getPassword()));

        User getUser2 = userDao.get(user2.getId());
        assertThat(user2.getName(), is(user2.getName()));
        assertThat(user2.getPassword(), is(user2.getPassword()));

//        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
//        System.out.println("Connection counter : " + ccm.getCounter());
    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao userDao = context.getBean("userDao", UserDao.class);
        User user1 = new User("user1", "유저1", "12345");
        User user2 = new User("user2", "유저2", "12345");
        User user3 = new User("user3", "유저3", "12345");

        userDao.delete();
        assertThat(userDao.getCount(), is(0));

        userDao.add(user1);
        assertThat(userDao.getCount(), is(1));

        userDao.add(user2);
        assertThat(userDao.getCount(), is(2));

        userDao.add(user3);
        assertThat(userDao.getCount(), is(3));
    }
}
