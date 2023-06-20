package br.ifsp.edu.lp1p2.controller.item;

import br.ifsp.edu.lp1p2.Main;
import br.ifsp.edu.lp1p2.dao.PecaDao;
import br.ifsp.edu.lp1p2.dao.impl.PecaDaoImpl;
import br.ifsp.edu.lp1p2.model.PecaEntity;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CriarItemController implements Initializable {

    private PecaDao pecaDao;

    public CriarItemController() {
        pecaDao = new PecaDaoImpl();
//        Platform.runLater(() ->{
//
//        });
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
    public void onCbPecasClick(){

    }







    @FXML
    public Button btAdicionarAdicionais;

    public void onAdicionarAdicionaisClick(){
        try{
            btAdicionarAdicionais.setDisable(true);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/adicionalitem.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();
            btAdicionarAdicionais.setDisable(false);
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateCbTamanhos();
        pupulateCbCores();
        populateCbPecas();
    }
}
