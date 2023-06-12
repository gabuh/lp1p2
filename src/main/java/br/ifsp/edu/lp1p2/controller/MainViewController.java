package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.Main;
import br.ifsp.edu.lp1p2.config.SystemSetting;
import br.ifsp.edu.lp1p2.dao.impl.TecidoDaoImpl;
import br.ifsp.edu.lp1p2.model.TecidoEntity;
import br.ifsp.edu.lp1p2.model.UsuarioEntity;
import br.ifsp.edu.lp1p2.util.Validator;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;


public class MainViewController {

    UsuarioEntity user;

    @FXML
    public Menu itemMenuAccount;
    @FXML
    public Label lbTecidoWarning;
    @FXML
    public TextField tfTecidoUpdateNome;
    @FXML
    public TextField tfTecidoUpdatePreco;
    @FXML
    private TextField tfTecidoNome;
    @FXML
    private TextField tfTecidoPreco;
    @FXML
    private TableView<TecidoEntity> tvTecido;
    @FXML
    private TableColumn<TecidoEntity, String> tbTecidoNome;
    @FXML
    private TableColumn<TecidoEntity, String> tbTecidoPreco;

    public MainViewController() {
        Platform.runLater(() ->{
            populateTvTecido();
            updateUserMenu();
        });
    }

    public void populateTvTecido(){
        if (!tvTecido.getItems().isEmpty()) tvTecido.getItems().clear();
        tbTecidoNome.setCellValueFactory( t -> new SimpleStringProperty(t.getValue().getNome()) );
        tbTecidoPreco.setCellValueFactory(t -> new SimpleStringProperty( "R$ "+t.getValue().getPreco().toString()) );
        for (TecidoEntity tecido: new TecidoDaoImpl().getTecidos()) {
            tvTecido.getItems().add(tecido);
        }
    }


    public void onSelectTecidoTable(){
        if(tvTecido.getSelectionModel().getSelectedItem() != null)
            tfTecidoUpdatePreco.setText(tvTecido.getSelectionModel().getSelectedItem().getPreco().toString()); tfTecidoUpdateNome.setText(tvTecido.getSelectionModel().getSelectedItem().getNome());
    }



    public void onAddTecidoClick(){
        var tecido = validateTecido();
        if (tecido == null)
            new Alert(Alert.AlertType.WARNING,"Por favor, Verifique os campos").show();
        else {
            new TecidoDaoImpl().create(tecido);
            populateTvTecido();
            new Alert(Alert.AlertType.INFORMATION,"Tecido Adicionado !").show();
        }
    }

    public TecidoEntity validateTecido(){
        if (!tfTecidoNome.getText().isEmpty() && !tfTecidoPreco.getText().isEmpty()){
            String msg = Validator.nameValidator(tfTecidoNome.getText());
            if (msg != null){
                lbTecidoWarning.setText(msg);
                return null;
            }
            msg = Validator.moneyValidator(tfTecidoPreco.getText());
            if (msg != null){
                lbTecidoWarning.setText(msg);
                return null;
            }
            TecidoEntity tecido = new TecidoEntity();
            tecido.setNome(tfTecidoNome.getText());
            tecido.setPreco(new BigDecimal(tfTecidoPreco.getText()));
            return tecido;
        }
        return null;
    }


    public void onUpdateTecidoClick() {
        TecidoEntity tecido = null;
        if (!tvTecido.getSelectionModel().isEmpty()) { tecido = tvTecido.getSelectionModel().getSelectedItem(); }
            if (tecido != null  && !tfTecidoUpdatePreco.getText().equals(tecido.getPreco().toString())) {
                if (Validator.moneyValidator(tfTecidoUpdatePreco.getText()) == null){
                    tecido.setPreco(new BigDecimal(tfTecidoUpdatePreco.getText()));
                    new TecidoDaoImpl().update(tecido);
                    populateTvTecido();
                }else{
                    new Alert(Alert.AlertType.WARNING,tfTecidoUpdatePreco.getText()+ "Inválido verifique o valor").show();
                }
            }
    }

    public void setUser(UsuarioEntity user) {
        this.user = user;
    }

    private void updateUserMenu(){
        try{
            itemMenuAccount.setText(user.getNomeUsuario());
            new Alert(Alert.AlertType.INFORMATION,"You have successfully logged in! \nWelcome "+ user.getNomeUsuario()).show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.toString()).show();
            System.exit(666);
        }
    };

    public void onMenuCloseClick() {
        System.exit(0);
    }

    public void onMenuLogoutClick(ActionEvent actionEvent) throws IOException {
        this.user=null;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SystemSetting.getScreenWidth() - 300, SystemSetting.getScreenHeight() - 300);
        Stage stage = ((Stage) ((MenuItem)actionEvent.getSource()).getParentPopup().getOwnerWindow().getScene().getWindow());
        stage.close();
        stage.setTitle("Programming Language - Project 2 !");
        stage.setScene(scene);
        stage.show();
    }




}
