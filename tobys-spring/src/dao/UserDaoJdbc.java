package dao;

import infra.exception.DuplicateUserIdException;
import model.Level;
import model.User;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    //    private ConnectionMaker dataSource;
    private DataSource dataSource;
    private JdbcContext jdbcContext;
    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setLevel(Level.valueOf(rs.getInt("level")));
            user.setLogin(rs.getInt("login"));
            user.setRecommend(rs.getInt("recommend"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    };

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcContext = new JdbcContext();
        jdbcContext.setDataSource(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

//    public void setJdbcContext(JdbcContext jdbcContext) {
//        this.jdbcContext = jdbcContext;
//    }

    //    public void setConnectionMaker(ConnectionMaker dataSource) {
//        this.dataSource = dataSource;
//    }
    //    public UserDao(ConnectionMaker dataSource) {
//        this.dataSource = dataSource;
//    }

    public void add(User user) throws DuplicateUserIdException {
//        try {
//            this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
//                @Override
//                public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                    PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
//                    ps.setString(1, user.getId());
//                    ps.setString(2, user.getName());
//                    ps.setString(3, user.getPassword());
//
//                    return ps;
//                }
//            });
//        }
//        catch (SQLException e) {
//            if(e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY) {
//                throw new DuplicateUserIdException(e);
//            }
//            else {
//                throw new RuntimeException(e);
//            }
//        }
        this.jdbcTemplate.update("insert into users(id, name, password, level, login, recommend, email) values(?, ?, ?, ?, ?, ?, ?)",
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getLevel().intValue(),
                user.getLogin(),
                user.getRecommend(),
                user.getEmail()
        );
    }

    public User get(String id) {
        return this.jdbcTemplate.queryForObject("select * from users where id = ?",
                new Object[]{id},
                this.userMapper);
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id",
                this.userMapper);
    }

    public void delete() {
//        StatementStrategy st = new DeleteAllStatement();
//        this.jdbcContext.workWithStatementStrategy(st);
//        this.jdbcContext.executeSql("delete from users");
        this.jdbcTemplate.update("delete from users");
    }

    public int getCount() {
        return this.jdbcTemplate.queryForInt("select count(*) from users");
//        Connection c = dataSource.getConnection();
//
//        PreparedStatement ps = c.prepareStatement("select count(*) from users");
//
//        ResultSet rs = ps.executeQuery();
//        rs.next();
//        int count = rs.getInt(1);
//
//        rs.close();
//        ps.close();
//        c.close();
    }

    public void update(User user) {
        this.jdbcTemplate.update("update users set name = ?, password = ?, level = ?, login = ?, recommend = ?, email = ? " +
                "where id = ?",
                user.getName(),
                user.getPassword(),
                user.getLevel().intValue(),
                user.getLogin(),
                user.getRecommend(),
                user.getEmail(),
                user.getId());
    }
}
