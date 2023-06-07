package br.ifsp.edu.lp1p2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;



    public void onLoginButtonClick(ActionEvent actionEvent) {
        welcomeText.setText("Dont look at me");
    }
}