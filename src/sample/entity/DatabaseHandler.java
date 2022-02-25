package sample.entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.PersonNotes;
import sample.service.User;

import java.sql.*;
import java.util.Date;
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

    public int getUserId(User user) {
        ResultSet resSet = null;
        PreparedStatement prSt = null;
        int counter = 0;
        int userId = 0;

        String select = "SELECT * FROM users WHERE login=(?) AND password=(?)";

        try {
            prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                System.out.println("idusers " + resSet.getString("idusers"));
                userId = Integer.parseInt(resSet.getString("idusers"));
                counter++;
            } else {
                System.out.println("ненашли");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("Ошибка в процессе выполнения SQL запроса");
            throwables.printStackTrace();
        }
        System.out.println(resSet != null ? resSet.toString() : "ненашли");
        return userId;
    }

    public void saveNotes(String notes, String user) {
        int user_id = getUserId(user);
        System.out.println(user);
        /*
        Каждый блок заметки на странице со списком заметок, кроме самого текста
        заметки,
         содержит дату и время создания заметки
         */
        String insert = "INSERT INTO user_notes(user_id,user_notes,timestamptz)VALUES(?,?,?)";
        Date current = new Date();
        Timestamp currentDataForSql = new Timestamp(current.getTime());
        System.out.println(current);
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, user_id);
            prSt.setString(2, notes);
            prSt.setTimestamp(3, currentDataForSql);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getUserId(String user) {
        int id = 0;
        ResultSet resSet = null;
        PreparedStatement prSt = null;

        String select = "SELECT * FROM users WHERE login=(?)";
        try {
            prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                id = (resSet.getInt("idusers"));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("Ненашли юзера ");
            throwables.printStackTrace();
        }
        System.out.println(resSet != null ? resSet.toString() : "ненашли");

        return id;
    }

    public ObservableList<PersonNotes> getNotes(int id) {
        ObservableList<PersonNotes> wordsList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        PreparedStatement prSt = null;

        System.out.println("id " + id);
        String select = "SELECT * FROM user_notes WHERE user_id=(?)";

        try {
            prSt = getDbConnection().prepareStatement(select);
            prSt.setInt(1, id);
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                String note = resSet.getString("user_notes");
                System.out.println("note " + note);
                String date = resSet.getString("timestamptz");
                System.out.println("date " + date);
                wordsList.add(new PersonNotes(note, date));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("Ошибка при поиске в БД  ");
            throwables.printStackTrace();
        }
        System.out.println(resSet != null ? resSet.toString() : "нет заметок");

        return wordsList;
    }
}

