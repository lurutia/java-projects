package test;

import model.Level;
import model.User;
import dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

        this.user1 = new User("user01", "유저01", "12345", Level.BASIC, 1, 0, "user01@email.com");
        this.user2 = new User("user02", "유저02", "12345", Level.SILVER, 55, 10, "user02@email.com");
        this.user3 = new User("user03", "유저3", "12345", Level.GOLD, 100, 40, "user03@email.com");
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
        checkSameUser(getUser1, user1);

        User getUser2 = userDao.get(user2.getId());
        checkSameUser(getUser2, user2);

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
        checkSameUser(user1, users1.get(0));

        userDao.add(user2); // id user02
        List<User> users2 = userDao.getAll();
        assertThat(users2.size(), is(2));
        checkSameUser(user1, users1.get(0));
        checkSameUser(user2, users2.get(1));

        userDao.add(user3); // id user03
        List<User> users3 = userDao.getAll();
        assertThat(users3.size(), is(3));
        checkSameUser(user1, users1.get(0));
        checkSameUser(user2, users2.get(1));
        checkSameUser(user3, users3.get(2));
    }

    @Test
    public void update() {
        userDao.delete();

        userDao.add(user1);

        user1.setName("체인지");
        user1.setPassword("spring6");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);
        userDao.update(user1);

        User user1update = userDao.get(user1.getId());
        checkSameUser(user1, user1update);
    }

    @Test(expected = DuplicateKeyException.class)
    public void duplicateKey() {
        this.userDao.delete();

        this.userDao.add(user1); // key user01
        this.userDao.add(user1); // key user01
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
        assertThat(user1.getLevel(), is(user2.getLevel()));
        assertThat(user1.getLogin(), is(user2.getLogin()));
        assertThat(user1.getRecommend(), is(user2.getRecommend()));

    }
}
