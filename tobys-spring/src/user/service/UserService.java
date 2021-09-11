package user.service;

import model.User;

import java.sql.SQLException;

public interface UserService {
    void add(User user);
    void upgradeLevels();
}
