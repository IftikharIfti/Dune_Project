package com.example.begindune;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public static String USERNAME;
    public  static int HIGHSCORE;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField tf_userName;
    @FXML
    private PasswordField tf_password;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_userName.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
                    USERNAME = tf_userName.getText();
                    System.out.println(USERNAME);
                    DBUtils.logInUser(event, USERNAME, tf_password.getText());
                }
                else{
                    System.out.println("Please fill in all info");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all info");
                    alert.show();
                }
            }
        });
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "signUp.fxml", null, null,  0);
            }
        });

    }
}
