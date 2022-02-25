package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.service.NewScene;
import sample.utils.Pages;

public class NotesController {

    @FXML
    private Button addNotes;

    @FXML
    private Button exitButton;

    @FXML
    private AnchorPane sceneLogin;

    @FXML
    void black(MouseEvent event) {

    }

    @FXML
    void orange(ActionEvent event) {

    }

    @FXML
    void red(MouseEvent event) {

    }

    NewScene scene = new NewScene();

    Stage stage;

    public void logout(ActionEvent event) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION
        );
        alert.setTitle("logout");
        alert.setHeaderText("You are to logout ?");
        alert.setContentText("Do you want logout ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("Вы  нажали на кнопку logout");
            stage = (Stage) sceneLogin.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    void initialize() {

        addNotes.setOnAction(
                event -> {
                    System.out.println("Создать новую заметку");
                    stage = (Stage) sceneLogin.getScene().getWindow();
                    scene.show(Pages.NEW_NOTES_SCENE, stage.getTitle());
                }
        );
    }

}
