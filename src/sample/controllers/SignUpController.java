package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.entity.DatabaseHandler;
import sample.service.User;

public class SignUpController {
    @FXML
    private AnchorPane loginSingButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpEmail;

    @FXML
    private TextField signUpLoginField;

    @FXML
    private PasswordField signUpPasswordField0;

    @FXML
    private PasswordField signUpPasswordField1;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        signUpButton.setOnAction(event -> {
            System.out.println("Зарегистрироваться ");

            signUpNewUser();
        });
    }

    private void signUpNewUser() {
        String login = signUpEmail.getText();
        String password1 = signUpPasswordField0.getText();
        String password2 = signUpPasswordField1.getText();
        System.out.println("loginEmail: " + login + ", password1 : " + password1 + " , password2: " + password2);

        if (password1.equals(password2)) {
            User user = new User(login,password1);
            DatabaseHandler dbHandler = new DatabaseHandler();
            dbHandler.signUpUser(user);
        } else {
            //Todo обработку сделать
            System.out.println("Пароли не совпадают");
        }
    }
}
