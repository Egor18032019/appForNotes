package sample.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.entity.DatabaseHandler;
import sample.model.PersonNotes;
import sample.service.NewScene;
import sample.service.User;
import sample.utils.Pages;

public class NotesController {

    private Stage stage;
    private ObservableList<PersonNotes> wordsList;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private Button forMainScene;

    @FXML
    private Button addNotes;

    @FXML
    private Button exitButton;

    @FXML
    private ListView<PersonNotes> listView;

    @FXML
    private AnchorPane notesScene;

    @FXML
    private AnchorPane sceneLogin;


    @FXML
    void forwardForMainScene(ActionEvent event) {
        NewScene scene = new NewScene();
        scene.open(Pages.MAIN_SCENE, forMainScene, user);
    }


    public void setWordsList(ObservableList<PersonNotes> wordsList) {
        listView.setItems(wordsList);
        this.wordsList = wordsList;
    }

    @FXML
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
            NewScene scene = new NewScene();
            scene.show(Pages.AVT_SCENE, "Неизвестный пользователь");
        }

    }

    @FXML
    void addNewNotes(ActionEvent event) {
        stage = (Stage) sceneLogin.getScene().getWindow();
        NewScene scene = new NewScene();
        scene.show(Pages.NEW_NOTES_SCENE, stage.getTitle());
        DatabaseHandler dbHandler = new DatabaseHandler();
        wordsList = dbHandler.getNotes(user);
        setWordsList(wordsList);
    }


}
