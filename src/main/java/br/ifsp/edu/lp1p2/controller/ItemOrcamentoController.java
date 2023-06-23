package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.Main;
import br.ifsp.edu.lp1p2.controller.item.CriarItemController;
import br.ifsp.edu.lp1p2.dao.ItempedidoDao;
import br.ifsp.edu.lp1p2.dao.impl.ItempedidoDaoImpl;
import br.ifsp.edu.lp1p2.model.ItempedidoEntity;
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

public class ItemOrcamentoController implements Initializable {

    private final ItempedidoDao itempedidoDao;

    private Stage stage;

    protected void setStage(Stage stage) {
        this.stage = stage;
    }

    public ItemOrcamentoController(){
        itempedidoDao = new ItempedidoDaoImpl();
    }

    @FXML
    public TableView<ItempedidoEntity> tvItemsAvailable;
    @FXML
    public TableColumn<ItempedidoEntity, String> tbItemAvailableModelo;
    @FXML
    public TableColumn<ItempedidoEntity, String> tbItemAvailableTamanho;
    @FXML
    public TableColumn<ItempedidoEntity, String> tbItemAvailableValor;
    @FXML
    public TableColumn<ItempedidoEntity, String> tbItemAvailablePeca;
    @FXML
    public TableColumn<ItempedidoEntity, String> tbItemAvailableCor;

    private void populateItemsAvailable(){
        if(tvItemsAvailable.getItems().isEmpty()){
            tvItemsAvailable.getItems().addAll(itempedidoDao.getItemPedidos());
        }else{
            tvItemsAvailable.getItems().clear();
            tvItemsAvailable.getItems().addAll(itempedidoDao.getItemPedidos());
        }
    }

    public void onAddItemAvailableClick() {
        if (tvItemsAvailable.getSelectionModel().getSelectedItem() != null){
            if(this.tvItemsPedido.getItems().contains(tvItemsAvailable.getSelectionModel().getSelectedItem()))
                new Alert(Alert.AlertType.WARNING,"Já foi Adicionado!").show();
            else{
                this.tvItemsPedido.getItems().add(tvItemsAvailable.getSelectionModel().getSelectedItem());
            }
        }
    }

    @FXML
    public Button btCriarItem;
    public void onCriarItemClick() {
        try{
            btCriarItem.setDisable(true);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/criaritem.fxml"));
            Stage stage = new Stage();
            Parent root = loader.load();
            CriarItemController criarItemController = loader.getController();
            criarItemController.setStage(stage);
            stage.setOnCloseRequest(t -> addItem(criarItemController.getItempedido()));
            stage.setOnHiding(t -> addItem(criarItemController.getItempedido()));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            btCriarItem.setDisable(false);
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    @FXML
    public TableView<ItempedidoEntity> tvItemsPedido;
    @FXML
    public TableColumn<ItempedidoEntity, String> tbItemModelo;
    @FXML
    public TableColumn<ItempedidoEntity, String> tbItemTamanho;
    @FXML
    public TableColumn<ItempedidoEntity, String> tbItemValor;
    @FXML
    public TableColumn<ItempedidoEntity, String> tbItemPeca;
    @FXML
    public TableColumn<ItempedidoEntity, String> tbItemCor;

    public void onRemoveItemClick() {
        if (tvItemsPedido.getSelectionModel().getSelectedItem() != null){
            this.tvItemsPedido.getItems().remove(this.tvItemsPedido.getSelectionModel().getSelectedItem());
        }
    }
    public void addItem(ItempedidoEntity item){
        if (item != null)
            tvItemsPedido.getItems().add(item);
    }

    public ObservableList<ItempedidoEntity> getItems(){
        return this.tvItemsPedido.getItems();
    }

    public void setTvItems(ObservableList<ItempedidoEntity> items){
        this.tvItemsPedido.getItems().addAll(items);
    }

    public void onConcluirClick() {
        try {
            this.stage.hide();
            this.stage.close();
        }catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbItemCor.setCellValueFactory(i -> new SimpleStringProperty( i.getValue().getCor() ));
        tbItemPeca.setCellValueFactory(i -> new SimpleStringProperty( i.getValue().getPecaId().getNome() ));
        tbItemModelo.setCellValueFactory(i -> new SimpleStringProperty( i.getValue().getModeloId().getNome() ));
        tbItemValor.setCellValueFactory( i-> new SimpleStringProperty( "R$ " + i.getValue().getValorItem().toString() ) );
        tbItemTamanho.setCellValueFactory( i-> new SimpleStringProperty( i.getValue().getTamanho() ) );

        tbItemAvailablePeca.setCellValueFactory(i -> new SimpleStringProperty( i.getValue().getPecaId().getNome() ));
        tbItemAvailableCor.setCellValueFactory(i -> new SimpleStringProperty( i.getValue().getCor() ));
        tbItemAvailableModelo.setCellValueFactory(i -> new SimpleStringProperty( i.getValue().getModeloId().getNome() ));
        tbItemAvailableValor.setCellValueFactory( i-> new SimpleStringProperty( "R$" + i.getValue().getValorItem().toString() ) );
        tbItemAvailableTamanho.setCellValueFactory( i-> new SimpleStringProperty( i.getValue().getTamanho() ) );
        populateItemsAvailable();
    }






}
