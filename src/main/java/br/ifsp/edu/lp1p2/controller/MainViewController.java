package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.Main;
import br.ifsp.edu.lp1p2.config.SystemSetting;
import br.ifsp.edu.lp1p2.dao.ClienteDao;
import br.ifsp.edu.lp1p2.dao.OrcamentoDao;
import br.ifsp.edu.lp1p2.dao.impl.ClienteDaoImpl;
import br.ifsp.edu.lp1p2.dao.impl.OrcamentoDaoImpl;
import br.ifsp.edu.lp1p2.dao.impl.TecidoDaoImpl;
import br.ifsp.edu.lp1p2.dao.impl.UsuarioDaoImpl;
import br.ifsp.edu.lp1p2.model.ItempedidoEntity;
import br.ifsp.edu.lp1p2.model.OrcamentoEntity;
import br.ifsp.edu.lp1p2.model.TecidoEntity;
import br.ifsp.edu.lp1p2.model.UsuarioEntity;
import br.ifsp.edu.lp1p2.util.Usuarioinfo;
import br.ifsp.edu.lp1p2.util.Validator;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MainViewController implements Initializable {


    private final OrcamentoDao orcamentoDao;
    private final ClienteDao clienteDao;

    public void setUser(UsuarioEntity user){
        Usuarioinfo.setUser(user);
    }



    // --------------------------------- INITIALIZER -------------------------------------- CONSTRUCTOR -------------------

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbTecidoNome.setCellValueFactory( t -> new SimpleStringProperty(t.getValue().getNome()) );
        tbTecidoPreco.setCellValueFactory(t -> new SimpleStringProperty( "R$ "+t.getValue().getPreco().toString()) );

        tbOrcamentoClienteNome.setCellValueFactory( o -> new SimpleStringProperty( (clienteDao.read(o.getValue().getClienteId())).getNome() ));
        tbOrcamentoPreco.setCellValueFactory( o -> new SimpleStringProperty( "R$ "+o.getValue().getValorTotal().toString() ));
        tbOrcamentoData.setCellValueFactory( o -> new SimpleStringProperty( o.getValue().getDataCriacao().toString()));
    }

    public MainViewController() {
        orcamentoDao = new OrcamentoDaoImpl();
        clienteDao = new ClienteDaoImpl();
        Platform.runLater(() ->{
            settingUser();
            populateTvTecido();
            updateUserMenu();
            populateTvOrcamento();
        });
    }
    private void settingUser(){
        orcamentos = orcamentoDao.getOrcamentos(Usuarioinfo.id);
    }


    // ------------------------------------

    /**
     * ORÇAMENTO TAB - SPACE TO MANAGE THE TAB ORCAMENTO -------------------------------- ORCAMENTO ------------------------------
     */

    @FXML
    public Label lbOrcamentoWarning;
    @FXML
    public TableView<OrcamentoEntity> tvOrcamento;
    @FXML
    public TableColumn<OrcamentoEntity, String> tbOrcamentoClienteNome;
    @FXML
    public TableColumn<OrcamentoEntity, String> tbOrcamentoPreco;
    @FXML
    public TableColumn<OrcamentoEntity, String> tbOrcamentoData;
    @FXML
    public Button btAdicionarItens;
    private ArrayList<OrcamentoEntity> orcamentos;
    private ArrayList<ItempedidoEntity> itempedidos;

    public void setItempedidos(ArrayList<ItempedidoEntity> itempedidos) {
        this.itempedidos = itempedidos;
    }

    public void populateTvOrcamento(){
        if(!tvOrcamento.getItems().isEmpty()) tvOrcamento.getItems().clear();
        for (OrcamentoEntity orcamento: orcamentos){
            tvOrcamento.getItems().add(orcamento);
        }
    }

    public void onAdicionarItensClick(){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/itemocamento.fxml"));
            btAdicionarItens.setDisable(true);
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();
            btAdicionarItens.setDisable(false);
        } catch (IOException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void onUpdateOrcamentoClick(ActionEvent actionEvent) {
    }


    /**
     * TOOLBAR --------- SPACE TO MANAGE TOOLBAR -----------------------------------TOOLBAR-----
     */

    @FXML
    public Menu itemMenuAccount;

    public void onMenuCloseClick() {
        System.exit(0);
    }

    private void updateUserMenu(){
        try{
            itemMenuAccount.setText(Usuarioinfo.name);
            new Alert(Alert.AlertType.INFORMATION,"You have successfully logged in! \nWelcome "+ Usuarioinfo.name).show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.toString()).show();
            System.exit(666);
        }
    }

    public void onMenuLogoutClick(ActionEvent actionEvent) throws IOException {
        Usuarioinfo.clearInfo();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SystemSetting.getScreenWidth() - 300, SystemSetting.getScreenHeight() - 300);
        Stage stage = ((Stage) ((MenuItem)actionEvent.getSource()).getParentPopup().getOwnerWindow().getScene().getWindow());
        stage.close();
        stage.setTitle("Programming Language - Project 2 !");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * TAB TECIDO - SPACE TO MANAGE THE TECIDO TAB  -------------------------------TECIDO TAB--------------
     */
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


    public void onSelectTecidoTable(){
        if(tvTecido.getSelectionModel().getSelectedItem() != null){
            tfTecidoUpdatePreco.setText(tvTecido.getSelectionModel().getSelectedItem().getPreco().toString());
            tfTecidoUpdateNome.setText(tvTecido.getSelectionModel().getSelectedItem().getNome());
        }
    }

    public void populateTvTecido(){
        if (!tvTecido.getItems().isEmpty()) tvTecido.getItems().clear();
        for (TecidoEntity tecido: new TecidoDaoImpl().getTecidos()) {
            tvTecido.getItems().add(tecido);
        }
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



}
