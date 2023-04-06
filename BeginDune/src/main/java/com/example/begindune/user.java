package com.example.begindune;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class user {
    private SimpleStringProperty username;
    private SimpleStringProperty name;
    private SimpleIntegerProperty score;

    public user(String username, String name, int score) {
        this.username = new SimpleStringProperty(username);
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score.set(score);
    }
}
