package sample.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Button;
import sample.utils.Pages;

public class NewScene {
    public void open (String window,Button action, String tittle) {
        // получаем сцену на которой было нажата кнопка и прячем её
        action.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("Ошибка в FXMLLoader");
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle(tittle);
        stage.setScene(new Scene(root));
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION
            );
            alert.setTitle("close");
            alert.setHeaderText("You are to want close ?");
            alert.setContentText("Do you want close ?");
            if(alert.showAndWait().get()== ButtonType.OK){
                stage.close();
            }
        });
    }

    public void show (String window,String tittle) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("Ошибка в FXMLLoader");
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle(tittle);
        stage.setScene(new Scene(root));
        stage.showAndWait();


        stage.setOnCloseRequest(event -> {
            event.consume();
            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION
            );
            alert.setTitle("close");
            alert.setHeaderText("You are to want close ?");
            alert.setContentText("Do you want close ?");
            if(alert.showAndWait().get()== ButtonType.OK){
                stage.close();
            }
        });
    }

}
