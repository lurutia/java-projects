package dao;

import model.User;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
//    private ConnectionMaker dataSource;
    private DataSource dataSource;
    private JdbcContext jdbcContext;

    public UserDao() {

    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcContext = new JdbcContext();
        jdbcContext.setDataSource(dataSource);
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

    public void add(User user) throws SQLException {
        this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());

                return ps;
            }
        });
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        User user = null;
        if(rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        c.close();

        if(user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

    public boolean delete() throws SQLException {
//        StatementStrategy st = new DeleteAllStatement();
//        this.jdbcContext.workWithStatementStrategy(st);
        this.jdbcContext.executeSql("delete from users");
        return true;
    }

    public int getCount() throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return count;
    }


}
