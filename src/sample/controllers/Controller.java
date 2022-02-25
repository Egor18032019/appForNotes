package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.animations.Shake;
import sample.utils.Pages;
import sample.entity.DatabaseHandler;
import sample.service.NewScene;
import sample.service.User;

public class Controller {
    NewScene scene = new NewScene();
    @FXML
    private TextField loginField;

    @FXML
    private Button loginSignUp;

    @FXML
    private AnchorPane loginSingButton;

    @FXML
    private Button loginEnterButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        loginEnterButton.setOnAction(event -> {
            System.out.println("Вы нажали на кнопку войти");
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();
            if (!loginText.equals("") && !loginPassword.equals("")) {
                loginUser(loginText, loginPassword, loginEnterButton);
            } else {
                //TODO сделать обработку если незаполнил
                System.out.println("Login or Password is empty !");
            }
        });

        loginSignUp.setOnAction(event -> {
            scene.open(Pages.SIGN_SCENE, loginSignUp, "Неизвестный пользователь");
        });
    }

    private void loginUser(String loginText, String loginPassword, Button action) {
        System.out.println("loginText: " + loginText + ", loginPassword : " + loginPassword);
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User(loginText, loginPassword);
        ResultSet resul = dbHandler.getUser(user);
        //TODO херня какаято . переделать
        int counter = 0;
        try {
            if (resul.next()) {
                System.out.println("login " + resul.getString("login")  );
                counter++;
                System.out.println("resul " + resul.toString());
            } else {
                System.out.println("ненашли");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (counter >= 1) {
            System.out.println("нашли Success");
            scene.open(Pages.NOTES_SCENE, action, loginText);
        } else {
            Shake userLoginAnim = new Shake(loginField);
            Shake userPasswordAnim = new Shake(passwordField);
            userLoginAnim.playAnim();
            userPasswordAnim.playAnim();
        }

    }


}
