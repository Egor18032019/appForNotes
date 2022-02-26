package sample.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.control.Button;
import sample.controllers.Exeption;
import sample.controllers.NotesController;
import sample.entity.DatabaseHandler;
import sample.model.PersonNotes;
import sample.utils.Pages;

public class NewScene {
    private ObservableList<PersonNotes> wordsList;

    public void open(String window, Button action, User user) {
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
        String tittle = user.getLogin();
        int userId = user.getId();
        stage.setTitle(tittle);
        if (userId > 0) {
            DatabaseHandler dbHandler = new DatabaseHandler();
            wordsList = dbHandler.getNotes(userId);
            NotesController controller = loader.getController();
            controller.setWordsList(wordsList);
            controller.setUserId(userId);
        }
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
            if (alert.showAndWait().get() == ButtonType.OK) {
                stage.close();
            }
        });
    }


    public void open(String window, Button action, String user) {
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
        String tittle = user;

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
            if (alert.showAndWait().get() == ButtonType.OK) {
                stage.close();
            }
        });
    }

    public void show(String window, String tittle) {

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
        System.out.println("Когда это будет выполнено ???");

        stage.setOnCloseRequest(event -> {
            event.consume();
            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION
            );
            alert.setTitle("close");
            alert.setHeaderText("You are to want close ?");
            alert.setContentText("Do you want close ?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                stage.close();
            }
        });
    }

    public void showInfo(String info) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Pages.INFO_SCENE));

        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("Ошибка в FXMLLoader");
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Информация");
        Exeption controller = loader.getController();

        controller.setExceptionText(info);
        stage.setScene(new Scene(root));
        stage.showAndWait();


//        stage.setOnCloseRequest(event -> {
//            event.consume();
//            Alert alert = new Alert(
//                    Alert.AlertType.CONFIRMATION
//            );
//            alert.setTitle("close");
//            alert.setHeaderText("You are to want close ?");
//            alert.setContentText("Do you want close ?");
//            if (alert.showAndWait().get() == ButtonType.OK) {
//                stage.close();
//            }
//        });
    }


}
