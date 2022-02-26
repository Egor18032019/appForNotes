package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.entity.DatabaseHandler;
import sample.service.NewScene;
import sample.service.User;
import sample.utils.Pages;

public class SignUpController {
    private NewScene scene = new NewScene();
    @FXML
    private Text exceptionLoginText;

    @FXML
    private Text exceptionPasswordText;

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

            DatabaseHandler dbHandler = new DatabaseHandler();
            boolean haveUser = dbHandler.haveUser(login,password1);
            if (!haveUser) {
                User user= dbHandler.signUpUser(login,password1);
                scene.open(Pages.NOTES_SCENE,signUpButton,user);
            } else {
                String info = "Такой логин уже есть. ";
                exceptionLoginText.setText(info);
            }
        } else {
            String info = "Пароли не совпадают.";
            exceptionPasswordText.setText(info);
            //Todo обработку сделать
         }
    }
}
