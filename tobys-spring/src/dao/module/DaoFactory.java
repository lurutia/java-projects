package dao.module;

import dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao(connectionMaker());

        return userDao;
    }

    public ConnectionMaker connectionMaker() {
        return new SimpleConnectionMaker();
    }
}
