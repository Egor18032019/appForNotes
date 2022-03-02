package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.animations.Shake;
import sample.entity.DatabaseHandler;
import sample.service.NewScene;
import sample.service.User;
import sample.utils.Pages;

public class Controller {

    @FXML
    private TextField loginField;
    @FXML
    private Text erorText;

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
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();
            if (!loginText.equals("") && !loginPassword.equals("")) {
                loginUser(loginText, loginPassword, loginEnterButton);
            } else {
                String info = "Login or Password is empty !";
                erorText.setText(info);
            }
        });

        loginSignUp.setOnAction(event -> {
            NewScene scene = new NewScene();
            scene.open(Pages.SIGN_SCENE, loginSignUp, "Неизвестный пользователь");
        });
    }

    private void loginUser(String loginText, String loginPassword, Button action) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = dbHandler.getUser(loginText, loginPassword);
        if (user != null) {
            System.out.println("нашли Success");
            NewScene scene = new NewScene();
            scene.open(Pages.NOTES_SCENE, action, user);
        } else {
            String info = "Login or Password is wrong !";
            erorText.setText(info);
            Shake userLoginAnim = new Shake(loginField);
            Shake userPasswordAnim = new Shake(passwordField);
            userLoginAnim.playAnim();
            userPasswordAnim.playAnim();
        }

    }


}
