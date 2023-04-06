package com.example.begindune;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class controller implements Initializable{
    @FXML
    public Button button_back;
    @FXML
    private TableColumn<user, String> Username;
    @FXML
    private TableColumn<user,String> Name;
    @FXML
    private TableColumn<user, Integer> Score;
    @FXML
    private Label welcomeText;
    @FXML
    private Label score;
    @FXML
    private TableView<user> table;

    ObservableList<user> list=FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/UserInfo", "root", "")) {
            // Prepare SQL statement
            String sql = "SELECT username, name, highscore FROM USERINFO ORDER BY highscore DESC";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute query and retrieve results
            ResultSet resultSet = statement.executeQuery();

            // Create observable list and add results to it
            ObservableList<user> observableList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String name = resultSet.getString("name");
                int score = resultSet.getInt("highscore");
                user user = new user(username, name, score);
                observableList.add(user);
            }

            // Set observable list as data source for table view
            table.setItems(observableList);
            //  Username.setStyle("-fx-background-image: url(\"Arrakis.jpg\"); -fx-background-size: cover;");

            // Bind user properties to columns

            Username.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
            Name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            Score.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"mainMenu.fxml",MainMenuController.USERNAME,null,0);

            }
        });

    }

//    public void setUserInfo(String userName, int highScore){
//        MainMenuController.USERNAME = userName;
//        welcomeText.setText("Welcome "+ MainMenuController.USERNAME + "!");
//        score.setText("Your best score: "+ highScore +"\nChallenge yourself now!!");
//    }


}
