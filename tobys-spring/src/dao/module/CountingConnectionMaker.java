package dao.module;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker {
    int counter = 0;
    private ConnectionMaker connectionMaker;

    public CountingConnectionMaker() {

    }

    public CountingConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public Connection makeConnection() throws SQLException, ClassNotFoundException {
        this.counter++;
        return connectionMaker.makeConnection();
    }

    public int getCounter() {
        return this.counter;
    }
}
