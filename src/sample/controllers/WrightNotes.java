package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.entity.DatabaseHandler;

public class WrightNotes {

    @FXML
    private Button myNewNotesButton;
    @FXML
    private TextField myTextFieldForNewNotes ;
    @FXML
    private AnchorPane sceneNewNotes;

    String notes;
    Stage stage;

    @FXML
    void submitNewNotes(ActionEvent event) {
        notes = myTextFieldForNewNotes.getText();
        System.out.println(notes);
        DatabaseHandler dbHandler = new DatabaseHandler();

        stage = (Stage) sceneNewNotes.getScene().getWindow();
        System.out.println("title = " + stage.getTitle() + sceneNewNotes.getId());
        dbHandler.saveNotes(notes,stage.getTitle());
        stage.close();
    }

}
