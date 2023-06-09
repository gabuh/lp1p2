package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.dao.impl.UsuarioDaoImpl;
import br.ifsp.edu.lp1p2.model.UsuarioEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        else
            new UsuarioDaoImpl().create(usuario);
    }

    private UsuarioEntity validate(){
        if(!tfName.getText().isEmpty() && !tfEmail.getText().isEmpty() && !tfPassword.getText().isEmpty() && !tfRetryPassword.getText().isEmpty()){
            if (!tfRetryPassword.getText().equals(tfPassword.getText())){
                lbWarning.setText("Senha incopativel"); return null;
            }
            String nameRegex = "^[a-zA-Z ]{6,18}+$";
            if (!tfName.getText().matches(nameRegex)){
                lbWarning.setText("Nome Invalido deve ter entre 6 a 18 digitos não numéricos"); return null;
            }
            String emailRegex = "^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
            if (!tfEmail.getText().matches(emailRegex)) {
                lbWarning.setText(tfEmail.getText()+" é invalida]"); return null;
            }
            String passwordRegex = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$";
            if (!tfPassword.getText().matches(passwordRegex)){
                lbWarning.setText("A senha deve conter 1 número (0-9), 1 letra maiúscula, 1 letra minúscula, 1 número não alfanumérico, 8-16 caracteres sem espaço"); return null;
            }
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setEmailUsuario(tfEmail.getText());
            usuario.setSenhaUsuario(tfPassword.getText());
            usuario.setNomeUsuario(tfName.getText());
            return usuario;
        }
        return null;
    }

}
