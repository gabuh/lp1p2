package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.Main;
import br.ifsp.edu.lp1p2.config.SystemSetting;
import br.ifsp.edu.lp1p2.dao.impl.UsuarioDaoImpl;
import br.ifsp.edu.lp1p2.util.Validator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


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



    public void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
        if(!tfEmail.getText().isEmpty() && !tfPassword.getText().isEmpty()){
                String msg = Validator.emailValidator(tfEmail.getText());
                if (msg!=null){ lbWaning.setText(msg); return;}
                msg = Validator.passwordValidator(tfPassword.getText());
                if (msg!=null){ lbWaning.setText(msg); return;}


                if( new UsuarioDaoImpl().checkUserByEmail(tfEmail.getText())){
                    if (new UsuarioDaoImpl().checkUserByEmailAndPassword(tfEmail.getText(),tfPassword.getText())){
                        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/mainview.fxml"));
                        root = loader.load();
                        stage = new Stage();
                        MainViewController controller = loader.getController();
                        controller.setUser(new UsuarioDaoImpl().getUsuarioByEmailAndPassword(tfEmail.getText(),tfPassword.getText()));
                        scene = new Scene(root, SystemSetting.getScreenWidth(),SystemSetting.getScreenHeight()-30);
                        stage.setScene(scene);
                        stage.setTitle("Programming Language - Project 2 !");
                        stage.setOnCloseRequest(t -> {
                            Platform.exit();
                            System.exit(0);
                        });
                        stage.show();
                        ((Stage) ((Node)actionEvent.getSource()).getScene().getWindow()).close();
                    }else
                        lbWaning.setText("dados inválidos, tente novamente");
                }else
                    lbWaning.setText("Usuario não existe.");

        }
    }


    public void onCreateAccountClick() throws IOException {
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