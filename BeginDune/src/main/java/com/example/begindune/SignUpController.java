package com.example.begindune;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private TextField tf_userName;
    @FXML
    private PasswordField tf_password;
    @FXML
    private TextField tf_name;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_name.getText().trim().isEmpty() && !tf_userName.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){
                    DBUtils.signUpUser(event, tf_userName.getText(), tf_password.getText(), tf_name.getText());
                }
                else{
                    System.out.println("Please fill in all info");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all info");
                    alert.show();
                }
            }
        });

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "loginPage.fxml", null, null, 0);
            }
        });
    }
}
