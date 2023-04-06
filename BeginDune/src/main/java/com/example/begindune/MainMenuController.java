package com.example.begindune;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
public class MainMenuController implements Initializable, MainMenuController1 {
    public static String USERNAME;
    @FXML
    private Button logoutButton;
    @FXML
    private Button playButton;
    @FXML
    private Label welcomeText;
    @FXML
    private Label score;
    @FXML
    private  Button leaderboard;
    public boolean startPlaying = false;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        welcomeText.setText("Welcome "+ LoginController.USERNAME + "!");
        score.setText("Your best score: "+ LoginController.HIGHSCORE +"\nChallenge yourself now!!");
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // startPlaying = true;

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                JFrame window = new JFrame();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("DUNE!!");

                GamePanel gamePanel = new GamePanel();
                window.add(gamePanel);

                window.pack();

                window.setLocationRelativeTo(null);
                window.setVisible(true);

                gamePanel.setupGame();
                gamePanel.startGameThread();
//                GamePanel gp = new GamePanel();
//                gp.startGameThread();
            }
        });
        leaderboard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"leaderboard.fxml",null,null,0);
            }
        });

        logoutButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "loginPage.fxml", null, null, 0);
            }
        });
    }
    public  void BacktoMainMenu(KeyEvent event) throws IOException {
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();

        FXMLLoader loader=new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Scene scene= new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }


    public void setUserInfo(String userName, int highScore){
        System.out.println(LoginController.USERNAME);
        welcomeText.setText("Welcome "+ LoginController.USERNAME + "!");
        score.setText("Your best score: "+ highScore +"\nChallenge yourself now!!");
    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }
}
