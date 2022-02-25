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

    public void saveNotes(String notes, String user) {
        int user_id = getUserId(user);
        System.out.println(user);
        String insert = "INSERT INTO user_notes(user_id,user_notes)VALUES(?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, user_id);
            prSt.setString(2, notes);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getUserId(String user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM users WHERE login=(?)";

        PreparedStatement prSt = null;
        int id = 0;
        try {
            prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user);
            resSet = prSt.executeQuery();
            if(resSet.next()){
            id = (resSet.getInt("idusers"));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("Ненашли юзера ");
            throwables.printStackTrace();
        }
        System.out.println(resSet != null ? resSet.toString() : "ненашли");

        return id;
    }
}

