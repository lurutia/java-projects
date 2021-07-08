import dao.UserDao;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
    }
}
