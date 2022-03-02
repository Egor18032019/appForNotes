package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.entity.DatabaseHandler;

public class WrightNotes {
    String notes;
    Stage stage;
    @FXML
    private Button myNewNotesButton;
    @FXML
    private TextField myTextFieldForNewNotes;
    @FXML
    private AnchorPane sceneNewNotes;
    @FXML
    private Text notesErorText;


    @FXML
    void submitNewNotes(ActionEvent event) {
        notes = myTextFieldForNewNotes.getText();
        System.out.println(notes);
        boolean emptyNoes = notes.trim().length() > 0;
        if (emptyNoes) {
            DatabaseHandler dbHandler = new DatabaseHandler();
            stage = (Stage) sceneNewNotes.getScene().getWindow();
            dbHandler.saveNotes(notes, stage.getTitle());
            stage.close();
        } else {
            String info = "Зачем вы хотите сохранить пустое сообщение ?";
            notesErorText.setText(info);
        }
    }

}
