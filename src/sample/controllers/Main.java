package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.service.NewScene;
import sample.utils.Pages;

public class Main  {

    @FXML
    private AnchorPane mainScene;

    @FXML
    private Text mainText;

    @FXML
    private Button forRegScene;

    @FXML
    private Button forAvtScene;

    public Button getForRegScene() {
        return forRegScene;
    }

    public Button getForAvtScene() {
        return forAvtScene;
    }

    public void setMainText(String mainText) {
        this.mainText.setText(mainText);
    }

    @FXML
    void goForAvtScene(ActionEvent event) {
        NewScene scene = new NewScene();
        scene.open(Pages.AVT_SCENE,forAvtScene,"Неизвестный пользователь");
    }

    @FXML
    void goForRegScene(ActionEvent event) {
        NewScene scene = new NewScene();
        scene.open(Pages.SIGN_SCENE,forRegScene,"forRegSceneНеизвестный пользователь");
    }

}
