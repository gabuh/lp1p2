package br.ifsp.edu.lp1p2.controller.item;

import br.ifsp.edu.lp1p2.Main;
import br.ifsp.edu.lp1p2.dao.ModeloDao;
import br.ifsp.edu.lp1p2.dao.PecaDao;
import br.ifsp.edu.lp1p2.dao.TecidoDao;
import br.ifsp.edu.lp1p2.dao.impl.ModeloDaoImpl;
import br.ifsp.edu.lp1p2.dao.impl.PecaDaoImpl;
import br.ifsp.edu.lp1p2.dao.impl.TecidoDaoImpl;
import br.ifsp.edu.lp1p2.model.AdicionalEntity;
import br.ifsp.edu.lp1p2.model.ModeloEntity;
import br.ifsp.edu.lp1p2.model.PecaEntity;
import br.ifsp.edu.lp1p2.model.TecidoEntity;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CriarItemController implements Initializable {

    private final PecaDao pecaDao;
    private final ModeloDao modeloDao;
    private final TecidoDao tecidoDao;

    public CriarItemController() {
        pecaDao = new PecaDaoImpl();
        modeloDao = new ModeloDaoImpl();
        tecidoDao = new TecidoDaoImpl();
//        Platform.runLater(() ->{
//
//        });
    }
    @FXML
    public TextField tfValorTotal;
    private void updateDados(){

    }



    /**
     * Tamanhos ------------------------------ Cores -----------------------
     */
    @FXML
    public ComboBox<Tamanho> cbTamanhos;
    private void populateCbTamanhos(){
        cbTamanhos.getItems().setAll(Tamanho.values());
        cbTamanhos.getSelectionModel().select(1);
    }
    @FXML
    public ComboBox<Cor> cbCores;
    private void pupulateCbCores(){
        cbCores.getItems().addAll(Cor.values());
        cbCores.getSelectionModel().select(1);
    }


    /**
     * ---------------------------------------------- PECA ============ PECA
     */


    @FXML
    public Button btCriarPeca;
    public void onCriarPecaClick(){
        try{
            btCriarPeca.setDisable(true);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/criarpeca.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();
            btCriarPeca.setDisable(false);
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    public ComboBox<PecaEntity> cbPecas;
    private void populateCbPecas(){
        if (cbPecas.getItems().isEmpty()){
            cbPecas.getItems().addAll( pecaDao.getPecas() );
        }else{
            cbPecas.getItems().clear();
            cbPecas.getItems().addAll( pecaDao.getPecas() );
        }
    }
    private PecaEntity selectedPeca;
    public void onCbPecasClick(){
        if (!cbPecas.getSelectionModel().isEmpty()){
            selectedPeca = cbPecas.getSelectionModel().getSelectedItem();
        };
    }



//-------------------------------- MODELO --------------------------- MODELO

    @FXML
    public Button btCriarModelo;

    @FXML ComboBox<ModeloEntity> cbModelos;
    public void populateModelos(){
        if (cbModelos.getItems().isEmpty()){
            cbModelos.getItems().addAll(modeloDao.getModelos());
        } else {
            cbModelos.getItems().clear();
            cbModelos.getItems().addAll(modeloDao.getModelos());
        }
    }

    public void onCriarModeloClick(){
        try{
            btCriarModelo.setDisable(true);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/criarmodelo.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();
            btCriarModelo.setDisable(false);
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void onCbModelosClick(){

    }



//------------------------------------- TECIDO ---------------------------------------- TECIDO

    @FXML
    public Button btCriarTecido;


    @FXML
    public ComboBox<TecidoEntity> cbTecidos;
    private void populateTecidos(){
        if (cbTecidos.getItems().isEmpty()){
            cbTecidos.getItems().addAll(tecidoDao.getTecidos());
        }else{
            cbTecidos.getItems().clear();
            cbTecidos.getItems().addAll(tecidoDao.getTecidos());
        }
    }



    public void onCbTecidosClick(){

    }

    public void onCriarTecidoClick(){
        try{
            btCriarTecido.setDisable(true);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/criartecido.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();
            btCriarTecido.setDisable(false);
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }



//----------------------------- ADICIONAL ---------------------------------- ADICIONAL -------------

    @FXML
    public Button btAdicionarAdicionais;

    public void onAdicionarAdicionaisClick(){
        try{
            btAdicionarAdicionais.setDisable(true);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/adicionalitem.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AdicionalItemController adicionalItemController = loader.getController();
            adicionalItemController.setStage(stage);
            if (!tvAdicionais.getItems().isEmpty()) adicionalItemController.setTvAdicionais(tvAdicionais.getItems());
            Scene scene = new Scene(root);
            stage.setOnCloseRequest( t-> setAdicionais(adicionalItemController.getAdicionais()));
            stage.setOnHiding(t-> setAdicionais(adicionalItemController.getAdicionais()));
            stage.setScene(scene);
            stage.showAndWait();
            btAdicionarAdicionais.setDisable(false);
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    @FXML
    public TableView<AdicionalEntity> tvAdicionais;
    @FXML
    public TableColumn<AdicionalEntity, String> tbAdicionalNome;
    @FXML
    public TableColumn<AdicionalEntity, String> tbAdicionalMultiplicador;

    protected void setAdicionais(ObservableList<AdicionalEntity> adicionais){
        tvAdicionais.getItems().clear();
        tvAdicionais.getItems().addAll(adicionais);
    };




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbAdicionalMultiplicador.setCellValueFactory(a -> new SimpleStringProperty( a.getValue().getMultiplicador().toString() ));
        tbAdicionalNome.setCellValueFactory(a -> new SimpleStringProperty( a.getValue().getNome()));

        populateCbTamanhos();
        pupulateCbCores();
        populateCbPecas();
        populateModelos();
        populateTecidos();
    }
}
