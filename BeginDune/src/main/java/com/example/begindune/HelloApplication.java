package com.example.begindune;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setMaximized(true);
        stage.setTitle("Dune!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        MainMenuController mainMenuController = new MainMenuController();
        launch();
//        if(mainMenuController.startPlaying){
       //   if(true){

     //   }


    }
}