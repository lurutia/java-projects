package test;

import dao.UserDao;
import model.Level;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import user.service.UserService;
import user.service.UserServiceImpl;
import user.service.UserServiceTx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static user.service.UserServiceImpl.MIN_LOGOUT_FOR_SILVER;
import static user.service.UserServiceImpl.MIN_RECCOMEND_FOR_GOLD;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/test-applicationContext.xml"})
@DirtiesContext
public class UserServiceImplTest {
    @Autowired
    UserDao userDao;

    @Autowired
    MailSender mailSender;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    UserService userService;

    @Autowired
    UserServiceImpl userServiceImpl;

    List<User> users;

    @Before
    public void setUp() {
        users = Arrays.asList(
                new User("bumjin", "박범진", "p1", Level.BASIC, MIN_LOGOUT_FOR_SILVER-1, 0, "bumjin@email.com"),
                new User("joytouch", "강명성", "p2", Level.BASIC, MIN_LOGOUT_FOR_SILVER, 0, "joytouch@email.com"),
                new User("erwins", "신승한", "p3", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD-1, "erwins@email.com"),
                new User("madnite1", "이상호", "p4", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD, "madnite1@email.com"),
                new User("green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE, "green@email.com")
        );
    }

    @Test
    public void upgradeLevels() throws SQLException {
//        userDao.delete();
//        for(User user : users) {
//            userDao.add(user);
//        }

        UserServiceImpl userServiceImpl = new UserServiceImpl();

        MockUserDao mockUserDao = new MockUserDao(this.users);
        userServiceImpl.setUserDao(mockUserDao);

        MockMailSender mockMailSender = new MockMailSender();
        userServiceImpl.setMailSender(mockMailSender);

        userServiceImpl.upgradeLevels();

        List<User> updated = mockUserDao.getUpdated();
        assertThat(updated.size(), is(2));
        checkUserAndLevel(updated.get(0), "joytouch", Level.SILVER);
        checkUserAndLevel(updated.get(1), "madnite1", Level.GOLD);

//        checkLevelUpgraded(users.get(0), false);
//        checkLevelUpgraded(users.get(1), true);
//        checkLevelUpgraded(users.get(2), false);
//        checkLevelUpgraded(users.get(3), true);
//        checkLevelUpgraded(users.get(4), false);

        List<String> request = mockMailSender.getRequests();
        assertThat(request.size(), is(2));
        assertThat(request.get(0), is(users.get(1).getEmail()));
        assertThat(request.get(1), is(users.get(3).getEmail()));

//        checkLevel(users.get(0), Level.BASIC);
//        checkLevel(users.get(1), Level.SILVER);
//        checkLevel(users.get(2), Level.SILVER);
//        checkLevel(users.get(3), Level.GOLD);
//        checkLevel(users.get(4), Level.GOLD);
    }

    @Test
    public void add() {
        userDao.delete();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userServiceImpl.add(userWithLevel);
        userServiceImpl.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
        assertThat(userWithoutLevelRead.getLevel(), is(userWithoutLevelRead.getLevel()));
    }

    private void checkLevel(User user, Level expectedLevel) {
        User userUpdate = userDao.get(user.getId());
        assertThat(userUpdate.getLevel(), is(expectedLevel));
    }

    private void checkLevelUpgraded(User user, boolean upgraded) {
        User userUpdate = userDao.get(user.getId());
        if (upgraded) {
            assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
        }
        else {
            assertThat(userUpdate.getLevel(), is(user.getLevel()));
        }
    }

    private void checkUserAndLevel(User updated, String expectedId, Level expectedLevel) {
        assertThat(updated.getId(), is(expectedId));
        assertThat(updated.getLevel(), is(expectedLevel));
    }

    @Test
    public void upgradeAllOrNothing() {
        UserServiceImpl testUserServiceImpl = new TestUserServiceImpl(users.get(3).getId());
        testUserServiceImpl.setUserDao(this.userDao);
        testUserServiceImpl.setMailSender(this.mailSender);

        UserServiceTx txUserService = new UserServiceTx();
        txUserService.setUserService(testUserServiceImpl);
        txUserService.setTransactionManager(transactionManager);
        userDao.delete();
        for (User user : users) userDao.add(user);

        try {
            txUserService.upgradeLevels();
            fail("TestUserServiceException expected");
        }
        catch (TestUserServiceException e) {
        }

        checkLevelUpgraded(users.get(1), false);
    }

    static class TestUserServiceImpl extends UserServiceImpl {
        private String id;

        private TestUserServiceImpl(String id) {
            this.id = id;
        }

        protected void upgradeLevel(User user) {
            if (user.getId().equals(this.id)) throw new TestUserServiceException();
            super.upgradeLevel(user);
        }
    }

    static class TestUserServiceException extends RuntimeException {
    }

    static class MockMailSender implements MailSender {
        private List<String> requests = new ArrayList<String>();

        public List<String> getRequests() {
            return requests;
        }

        public void send(SimpleMailMessage mailMessage) throws MailException {
            requests.add(mailMessage.getTo()[0]);
        }

        public void send(SimpleMailMessage[] mailMessage) throws MailException {

        }
    }
}
