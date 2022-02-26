package sample.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class Exeption {

    @FXML
    private Text exceptionText;

    public void setExceptionText(String exceptionText) {
        this.exceptionText.setText(exceptionText);
    }
}
