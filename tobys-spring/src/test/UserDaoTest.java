package test;

import model.User;
import dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
//@DirtiesContext
public class UserDaoTest {

    @Autowired
    UserDao userDao;

//    private UserDao userDao;
    private User user1;
    private User user2;
    private User user3;


    @Before
    public void setUp() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        this.userDao = context.getBean("userDao", UserDao.class);
//        DataSource dataSource = new SingleConnectionDataSource(
//                "jdbc:mysql://location/testdb", "spring", "book", true
//        );
//        userDao.setDataSource(dataSource);

        this.user1 = new User("user01", "유저01", "12345");
        this.user2 = new User("user02", "유저02", "12345");
        this.user3 = new User("user03", "유저3", "12345");
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
//        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        userDao.delete();
        assertThat(userDao.getCount(), is(0));

        userDao.add(user1);
        userDao.add(user2);
        assertThat(userDao.getCount(), is(2));

        User getUser1 = userDao.get(user1.getId());
        assertThat(getUser1.getName(), is(user1.getName()));
        assertThat(getUser1.getPassword(), is(user1.getPassword()));

        User getUser2 = userDao.get(user2.getId());
        assertThat(user2.getName(), is(user2.getName()));
        assertThat(user2.getPassword(), is(user2.getPassword()));

//        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
//        System.out.println("Connection counter : " + ccm.getCounter());
    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        userDao.delete();
        assertThat(userDao.getCount(), is(0));

        userDao.add(user1);
        assertThat(userDao.getCount(), is(1));

        userDao.add(user2);
        assertThat(userDao.getCount(), is(2));

        userDao.add(user3);
        assertThat(userDao.getCount(), is(3));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFail() throws SQLException, ClassNotFoundException {
        userDao.delete();
        assertThat(userDao.getCount(), is(0));

        userDao.get("unknown_id");
    }

    @Test
    public void getAll() throws SQLException {
        userDao.delete();

        List<User> users0 = userDao.getAll();
        assertThat(users0.size(), is(0));

        userDao.add(user1); // id user01
        List<User> users1 = userDao.getAll();
        assertThat(users1.size(), is(1));
        checkSomeUser(user1, users1.get(0));

        userDao.add(user2); // id user02
        List<User> users2 = userDao.getAll();
        assertThat(users2.size(), is(2));
        checkSomeUser(user1, users1.get(0));
        checkSomeUser(user2, users2.get(1));

        userDao.add(user3); // id user03
        List<User> users3 = userDao.getAll();
        assertThat(users3.size(), is(3));
        checkSomeUser(user1, users1.get(0));
        checkSomeUser(user2, users2.get(1));
        checkSomeUser(user3, users3.get(2));
    }

    private void checkSomeUser(User user1, User user2) {
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
    }
}
