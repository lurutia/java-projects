package test;

import dao.UserDao;
import dao.module.ConnectionMaker;
import dao.module.SimpleConnectionMaker;
import model.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionMaker connectionMaker = new SimpleConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        userDao.delete();

        User user = new User();
        user.setId("2");
        user.setName("user02");
        user.setPassword("12345");

        userDao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = userDao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }
}
