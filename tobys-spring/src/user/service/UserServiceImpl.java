package user.service;

import dao.UserDao;
import model.Level;
import model.User;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

public class UserServiceImpl implements UserService {
    public static final int MIN_LOGOUT_FOR_SILVER = 50;
    public static final int MIN_RECCOMEND_FOR_GOLD = 30;

    private UserDao userDao;

    private MailSender mailSender;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void upgradeLevels() {
        List<User> users = userDao.getAll();
        for(User user : users) {
            if (canUpgradeLevel(user)) {
                upgradeLevel(user);
            }
        }
    }

    protected void upgradeLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);
        try {
            sendUpgradeEmail(user);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();

        switch(currentLevel) {
            case BASIC: return (user.getLogin() >= MIN_LOGOUT_FOR_SILVER);
            case SILVER: return (user.getRecommend() >= MIN_RECCOMEND_FOR_GOLD);
            case GOLD: return false;
            default: throw new IllegalArgumentException("Unknown Level: ".concat(currentLevel.toString()));
        }
    }

    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userDao.add(user);
    }

    private void sendUpgradeEmail(User user) throws Exception {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("useradmin@ksung.org");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Upgrade 안내");
        mailMessage.setText(String.format("사용자님의 등급이 {}로 업그레이드 되었습니다.", user.getEmail()));

        this.mailSender.send(mailMessage);
    }
}
