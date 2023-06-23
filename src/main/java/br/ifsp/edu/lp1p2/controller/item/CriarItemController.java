package br.ifsp.edu.lp1p2.controller.item;

import br.ifsp.edu.lp1p2.Main;
import br.ifsp.edu.lp1p2.controller.CriarPecaController;
import br.ifsp.edu.lp1p2.controller.CriarTecidoController;
import br.ifsp.edu.lp1p2.dao.ItempedidoDao;
import br.ifsp.edu.lp1p2.dao.ModeloDao;
import br.ifsp.edu.lp1p2.dao.PecaDao;
import br.ifsp.edu.lp1p2.dao.TecidoDao;
import br.ifsp.edu.lp1p2.dao.impl.ItempedidoDaoImpl;
import br.ifsp.edu.lp1p2.dao.impl.ModeloDaoImpl;
import br.ifsp.edu.lp1p2.dao.impl.PecaDaoImpl;
import br.ifsp.edu.lp1p2.dao.impl.TecidoDaoImpl;
import br.ifsp.edu.lp1p2.model.*;
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
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class CriarItemController implements Initializable {


    Stage stage;



    private final PecaDao pecaDao;
    private final ModeloDao modeloDao;
    private final TecidoDao tecidoDao;


//    private final ItempedidoDao itempedidoDao;

    public CriarItemController() {
        pecaDao = new PecaDaoImpl();
        modeloDao = new ModeloDaoImpl();
        tecidoDao = new TecidoDaoImpl();
//        itempedidoDao = new ItempedidoDaoImpl();
//        Platform.runLater(() ->{
//
//        });
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public TextField tfValorTotal;

    private double valorTotal;
    private boolean allDataFlag = false;
    private void updateDados(){
        if (selectedPeca != null && selectedModelo != null && selectedTecido != null && !tvAdicionais.getItems().isEmpty()) {
            double value = selectedPeca.getPrecoBase() + selectedTecido.getPreco().doubleValue();
            for (AdicionalEntity a : tvAdicionais.getItems()){
               value += a.getMultiplicador() * selectedPeca.getPrecoBase();
            }
            value += selectedModelo.getMultiplicador() * selectedPeca.getPrecoBase();
            value += selectedTamanho.getV() * selectedPeca.getPrecoBase();
            valorTotal = value;
            tfValorTotal.setText(Double.toString(value));
            allDataFlag = true;
        }
        else if (selectedPeca != null) {
            tfValorTotal.setText(selectedPeca.getPrecoBase().toString());
            allDataFlag = false;
        }

    }

    private ItempedidoEntity item = null;

    public ItempedidoEntity getItempedido() {
        return item;
    }

    public void onConcluirClick(){
        if (allDataFlag){
            item = new ItempedidoEntity();
            item.setAdicionais(tvAdicionais.getItems());
            item.setCor(cbCores.getSelectionModel().getSelectedItem().toString());
            item.setTamanho(selectedTamanho.toString());
            item.setPecaId(selectedPeca);
            item.setTecidoId(selectedTecido);
            item.setModeloId(selectedModelo);
            item.setValorItem(new BigDecimal(valorTotal));
//            itempedidoDao.create(item);
            stage.hide();
            stage.close();
        }
    }


    /**
     * Tamanhos ------------------------------ Cores -----------------------
     */
    @FXML
    public ComboBox<Tamanho> cbTamanhos;
    private void populateCbTamanhos(){
        cbTamanhos.getItems().setAll(Tamanho.values());
        cbTamanhos.getSelectionModel().select(1);
        onCbTamanhosClick();
    }

    Tamanho selectedTamanho;
    public void onCbTamanhosClick(){
        if (!cbTamanhos.getSelectionModel().isEmpty()){
            selectedTamanho = cbTamanhos.getSelectionModel().getSelectedItem();
        }
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
            Parent root = loader.load();
            CriarPecaController criarPecaController = loader.getController();
            stage.setOnHiding(t-> addPeca(criarPecaController.getPeca() ));
            criarPecaController.setStage(stage);
            Scene scene = new Scene(root);
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
    public void addPeca(PecaEntity peca){
        if (peca!=null){
            pecaDao.create(peca);
            cbPecas.getItems().add(peca);
            cbPecas.getSelectionModel().select(peca);
        }
    }

    private PecaEntity selectedPeca;
    public void onCbPecasClick(){
        if (!cbPecas.getSelectionModel().isEmpty()){
            selectedPeca = cbPecas.getSelectionModel().getSelectedItem();
            updateDados();
        }
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
            if (!pecaDao.getPecas().isEmpty()){
                btCriarModelo.setDisable(true);
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/criarmodelo.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.showAndWait();
                btCriarModelo.setDisable(false);
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Crie pelo ao menos uma peca antes de criar Modelos").show();
            }
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private ModeloEntity selectedModelo;

    public void onCbModelosClick(){
        if (!cbModelos.getSelectionModel().isEmpty()){
            selectedModelo = cbModelos.getSelectionModel().getSelectedItem();
            updateDados();
        }
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

    TecidoEntity selectedTecido;

    public void onCbTecidosClick(){
        if (!cbTecidos.getSelectionModel().isEmpty()){
            selectedTecido = cbTecidos.getSelectionModel().getSelectedItem();
            updateDados();
        }
    }

    public void onCriarTecidoClick(){
        try{
            btCriarTecido.setDisable(true);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/criartecido.fxml"));
            Stage stage = new Stage();
            Parent root = loader.load();
            CriarTecidoController tecidoController = loader.getController();
            tecidoController.setStage(stage);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            btCriarTecido.setDisable(false);
            populateTecidos();
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
        updateDados();
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
