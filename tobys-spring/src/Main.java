import dao.UserDao;
import model.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("2");
        user.setName("user02");
        user.setPassword("12345");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }
}
