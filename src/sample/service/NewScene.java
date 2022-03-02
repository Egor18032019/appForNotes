package sample.service;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.controllers.Main;
import sample.controllers.NotesController;
import sample.entity.DatabaseHandler;
import sample.model.PersonNotes;
import sample.utils.Pages;

import java.io.IOException;

//Todo в один метод ?
public class NewScene {

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
        stage.setTitle(tittle);

        if (window.equals(Pages.NOTES_SCENE)) {
            DatabaseHandler dbHandler = new DatabaseHandler();
             ObservableList<PersonNotes> wordsList;
            wordsList = dbHandler.getNotes(user);

            NotesController controller = loader.getController();
            controller.setWordsList(wordsList);
            controller.setUser(user);
        }
        if (window.equals(Pages.MAIN_SCENE)) {
            Main controller = loader.getController();
            controller.getForAvtScene().setVisible(false);
            controller.getForRegScene().setVisible(false);
            DatabaseHandler dbHandler = new DatabaseHandler();
            int notes = dbHandler.getNotes(user).size();
            String info = "Количество сохраненных пользователем заметок = " + notes +".";
            controller.setMainText(info);
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


}
