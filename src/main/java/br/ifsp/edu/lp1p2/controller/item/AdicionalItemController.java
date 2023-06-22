package br.ifsp.edu.lp1p2.controller.item;

import br.ifsp.edu.lp1p2.Main;
import br.ifsp.edu.lp1p2.controller.CriarAdicionalController;
import br.ifsp.edu.lp1p2.dao.AdicionalDao;
import br.ifsp.edu.lp1p2.dao.impl.AdicionalDaoImpl;
import br.ifsp.edu.lp1p2.model.AdicionalEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdicionalItemController implements Initializable {

    private final AdicionalDao adicionalDao;
    private Stage stage;

    public AdicionalItemController() {
        adicionalDao = new AdicionalDaoImpl();
    }

    protected void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbAdicionalNome.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getNome()));
        tbAdicionalMultiplicador.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getMultiplicador().toString()));
        tbAdicionalNomeAvailable.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getNome()));
        tbAdicionalMultiplicadorAvailable.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getMultiplicador().toString()));
        populateAdicionaisAvailable();
    }

    private void populateAdicionaisAvailable(){
        if(tvAdicionaisAvailable.getItems().isEmpty()){
            tvAdicionaisAvailable.getItems().addAll(adicionalDao.getAdicionais());
        }else{
            tvAdicionaisAvailable.getItems().clear();
            tvAdicionaisAvailable.getItems().addAll(adicionalDao.getAdicionais());
        }
    }


    @FXML
    public TableView<AdicionalEntity> tvAdicionaisAvailable;
    @FXML
    public TableColumn<AdicionalEntity, String> tbAdicionalMultiplicadorAvailable;

    @FXML
    public TableColumn<AdicionalEntity, String> tbAdicionalNomeAvailable;


    public void onAddAdicionalAvailableClick() {
        if (tvAdicionaisAvailable.getSelectionModel().getSelectedItem() != null){
            if(tvAdicionais.getItems().contains(tvAdicionaisAvailable.getSelectionModel().getSelectedItem()))
                new Alert(Alert.AlertType.WARNING,"Já foi Adicionado!").show();
            else{
                tvAdicionais.getItems().add(tvAdicionaisAvailable.getSelectionModel().getSelectedItem());
            }
        }
    }

    @FXML
    public TableView<AdicionalEntity> tvAdicionais;
    @FXML
    public TableColumn<AdicionalEntity, String> tbAdicionalNome;
    @FXML
    public TableColumn<AdicionalEntity, String> tbAdicionalMultiplicador;

    public void onRemoveAdicionalClick() {
        if (tvAdicionais.getSelectionModel().getSelectedItem() != null){
            tvAdicionais.getItems().remove(tvAdicionais.getSelectionModel().getSelectedItem());
        }
    }

    protected void setTvAdicionais(ObservableList<AdicionalEntity> adicionais){
        this.tvAdicionais.getItems().addAll(adicionais);
    }

    public ObservableList<AdicionalEntity> getAdicionais(){
        return tvAdicionais.getItems();
    }

    public void onConcluirClick() {
        try {
            stage.hide();
            stage.close();
        }catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void addAdicional(AdicionalEntity adicional){
        if (adicional!=null)
            tvAdicionais.getItems().add(adicional);
    }

    @FXML
    public Button btCriarAdicional;

    public void onCriarAdicionalClick(){
        try{
            btCriarAdicional.setDisable(true);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/criaradicional.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            CriarAdicionalController criarAdicionalController = loader.getController();
            criarAdicionalController.setStage(stage);
            Scene scene = new Scene(root);
            stage.setOnCloseRequest( t-> addAdicional(criarAdicionalController.getAdicional()));
            stage.setOnHiding(t-> addAdicional(criarAdicionalController.getAdicional()));
            stage.setScene(scene);
            stage.showAndWait();
            btCriarAdicional.setDisable(false);
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


}
