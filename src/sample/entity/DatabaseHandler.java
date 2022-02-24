package sample.entity;

import sample.service.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Properties;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "222324");
        props.setProperty("ssl", "false");
        dbConnection = DriverManager.getConnection(connectionString, props);

        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO users(login,password)VALUES(?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;
        String login =user.getLogin();
        String password = user.getPassword();
        String select = "SELECT * FROM users WHERE login=(?) AND password=(?)";

        PreparedStatement prSt = null;

        try {
            prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("Ненашли юзера ");
            throwables.printStackTrace();
        }
        System.out.println(resSet != null ? resSet.toString() : "ненашли");
        return resSet;
    }
}

