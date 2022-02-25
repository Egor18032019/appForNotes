package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.model.PersonNotes;
import sample.service.NewScene;
import sample.utils.Pages;
import sample.entity.DatabaseHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class NotesController {
    private NewScene scene = new NewScene();
    private Stage stage;
    private ObservableList<PersonNotes> wordsList;
    private int userId;


    @FXML
    private Button addNotes;

    @FXML
    private Button exitButton;

    @FXML
    private ListView<PersonNotes> listView;

    @FXML
    private AnchorPane sceneLogin;

    @FXML
    void black(MouseEvent event) {
        System.out.println("black");
    }

    @FXML
    void red(MouseEvent event) {
        System.out.println("red");
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        }

    }

    @FXML
    void addNewNotes(ActionEvent event) {
        System.out.println("Создать новую заметку");
        stage = (Stage) sceneLogin.getScene().getWindow();
        scene.show(Pages.NEW_NOTES_SCENE, stage.getTitle());
    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println("init");
//        DatabaseHandler dbHandler = new DatabaseHandler();
//        System.out.println("dbHandle");
//        stage = (Stage) sceneLogin.getScene().getWindow();
//        System.out.println("stage");
//        String userLogin = stage.getTitle();
//        System.out.println("title");
//        ObservableList<PersonNotes> wordsList = dbHandler.getNotes(userLogin);
//        wordsList.add(new PersonNotes("Albert", "Einstein"));
//        wordsList.add(new PersonNotes("Ludwig", "Boltzmann"));
//        listView.setItems(wordsList);
//    }
}
