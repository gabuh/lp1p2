package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.Main;
import br.ifsp.edu.lp1p2.dao.impl.UsuarioDaoImpl;
import br.ifsp.edu.lp1p2.model.UsuarioEntity;
import br.ifsp.edu.lp1p2.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;


public class LoginController {

    Parent root;
    Scene scene;
    Stage stage;


    @FXML
    public Label lbWaning;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;



    public void onLoginButtonClick(ActionEvent actionEvent) {
        if(!tfEmail.getText().isEmpty() && !tfPassword.getText().isEmpty()){
                String msg = Validator.emailValidator(tfEmail.getText());
                if (msg!=null){ lbWaning.setText(msg); return;}
                msg = Validator.passwordValidator(tfPassword.getText());
                if (msg!=null){ lbWaning.setText(msg); return;}

                UsuarioEntity usuario = new UsuarioDaoImpl().getUsuarioByEmail(tfEmail.getText());
                if(usuario != null && tfPassword.getText().equals(usuario.getSenhaUsuario()))
                    System.out.printf(usuario.getNomeUsuario());
                else
                    lbWaning.setText("Usuario não existe.");
        }
    }


    public void onCreateAccountClick(ActionEvent actionEvent) throws IOException {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/cadastro.fxml"));
            root = loader.load();
            stage = new Stage();
            CadastroController controller = loader.getController();

        if(!tfEmail.getText().isEmpty())
            controller.setTfEmail(tfEmail.getText());
        if (!tfPassword.getText().isEmpty())
            controller.setTfPassword(tfPassword.getText());

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    }


}