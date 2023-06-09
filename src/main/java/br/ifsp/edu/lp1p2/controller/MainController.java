package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.dao.impl.UsuarioDaoImpl;
import br.ifsp.edu.lp1p2.model.UsuarioEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private UsuarioDaoImpl usuarioDao;

    @FXML
    public Label lbWaning;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;



    public void onLoginButtonClick(ActionEvent actionEvent) {
        if(!tfEmail.getText().isEmpty() && !tfPassword.getText().isEmpty()){
                UsuarioEntity usuario = usuarioDao.getUsuarioByEmail(tfEmail.getText());
                if(usuario != null && tfPassword.getText().equals(usuario.getSenhaUsuario()))
                    System.out.printf(usuario.getNomeUsuario());
                else
                    lbWaning.setText("There is no one with those information.");
        }
    }


    public void onCreateAccountClick(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioDao = new UsuarioDaoImpl();
    }

}