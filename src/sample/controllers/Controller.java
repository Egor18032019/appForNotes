package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.animations.Shake;
import sample.entity.DatabaseHandler;
import sample.service.NewScene;
import sample.service.User;
import sample.utils.Pages;

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
            scene.open(Pages.SIGN_SCENE, loginSignUp, "Неизвестный пользователь",0);
        });
    }

    private void loginUser(String loginText, String loginPassword, Button action) {
        System.out.println("loginText: " + loginText + ", loginPassword : " + loginPassword);
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User(loginText, loginPassword);
        int userId = dbHandler.getUserId(user);
        if (userId>0) {
            System.out.println("нашли Success");
            scene.open(Pages.NOTES_SCENE, action, loginText,userId);
        } else {
            Shake userLoginAnim = new Shake(loginField);
            Shake userPasswordAnim = new Shake(passwordField);
            userLoginAnim.playAnim();
            userPasswordAnim.playAnim();
        }

    }


}
