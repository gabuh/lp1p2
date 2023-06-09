package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.dao.impl.UsuarioDaoImpl;
import br.ifsp.edu.lp1p2.model.UsuarioEntity;
import br.ifsp.edu.lp1p2.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CadastroController {

    @FXML
    public TextField tfName;
    @FXML
    public TextField tfEmail;
    @FXML
    public PasswordField tfPassword;
    @FXML
    public PasswordField tfRetryPassword;
    @FXML
    public Label lbWarning;

    public void onClickRegisterButton(ActionEvent actionEvent) {
        var usuario = validate();
        if (usuario == null)
            new Alert(Alert.AlertType.WARNING,"Por favor, Verifique os campos").show();
        else {
            new UsuarioDaoImpl().create(usuario);
            new Alert(Alert.AlertType.INFORMATION,"Account Successfully Created").show();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    private UsuarioEntity validate(){
        if(!tfName.getText().isEmpty() && !tfEmail.getText().isEmpty() && !tfPassword.getText().isEmpty() && !tfRetryPassword.getText().isEmpty()){
            if (!tfRetryPassword.getText().equals(tfPassword.getText())){
                lbWarning.setText("Senha incompatível"); return null;
            }
            String msg = Validator.nameValidator(tfName.getText());
            if (msg!=null) { lbWarning.setText(msg); return null; }

            msg = Validator.emailValidator(tfEmail.getText());
            if (msg!=null) { lbWarning.setText(msg); return null; }

            var user = new UsuarioDaoImpl().getUsuarioByEmail(tfEmail.getText());
            if (user!=null)
                if (tfEmail.getText().equals(user.getEmailUsuario())){ lbWarning.setText("Usuario ja existente, Verifique os dados ou recupere sua conta"); return null; }

            msg = Validator.passwordValidator(tfPassword.getText());
            if (msg!=null){ lbWarning.setText(msg); return null; }

            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setEmailUsuario(tfEmail.getText());
            usuario.setSenhaUsuario(tfPassword.getText());
            usuario.setNomeUsuario(tfName.getText());
            return usuario;
        }
        return null;
    }

    public void setTfPassword(String name){
        tfPassword.setText(name);
    }
    public void setTfEmail(String email){
        tfEmail.setText(email);
    }


}
