package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sample.service.NewScene;
import sample.utils.Pages;

public class Master {

    @FXML
    private Button forAvtScene;

    @FXML
    private Button forMainScene;

    @FXML
    private Button forRegScene;

    @FXML
    private AnchorPane loginSingButton;

    @FXML
    void goForAvtScene(ActionEvent event) {
          NewScene scene = new NewScene();
          scene.open(Pages.AVT_SCENE,forAvtScene,"Неизвестный пользователь");
    }

    @FXML
    void goForMainScene(ActionEvent event) {
        NewScene scene = new NewScene();
        scene.open(Pages.MAIN_SCENE,forMainScene,"Неизвестный пользователь");
    }

    @FXML
    void goForRegScene(ActionEvent event) {
        NewScene scene = new NewScene();
        scene.open(Pages.SIGN_SCENE,forMainScene,"Неизвестный пользователь");
    }

}
