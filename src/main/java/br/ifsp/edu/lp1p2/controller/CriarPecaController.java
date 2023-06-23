package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.Main;
import br.ifsp.edu.lp1p2.dao.MedidaDao;
import br.ifsp.edu.lp1p2.dao.impl.MedidaDaoImpl;
import br.ifsp.edu.lp1p2.model.MedidaEntity;
import br.ifsp.edu.lp1p2.model.PecaEntity;
import br.ifsp.edu.lp1p2.util.Validator;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CriarPecaController implements Initializable {
    private final MedidaDao medidaDao;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TableColumn<MedidaEntity, String> tbMedidaTamanho;
    public TableColumn<MedidaEntity, String> tbMedidaNome;
    public TableView<MedidaEntity> tvMedidas;
    public ComboBox<MedidaEntity> cbMedidas;
    public TextField tfPrecoBase;
    public TextField tfNome;

    public CriarPecaController() {
        medidaDao = new MedidaDaoImpl();
    }

    public void onRemoveclick() {
        if (!tvMedidas.getSelectionModel().isEmpty()){
            tvMedidas.getItems().remove(tvMedidas.getSelectionModel().getSelectedItem());
        }
    }

    public void onAdicionarClick() {
        if (!cbMedidas.getSelectionModel().isEmpty()){
            if (!tvMedidas.getItems().contains(cbMedidas.getSelectionModel().getSelectedItem())){
                tvMedidas.getItems().add(cbMedidas.getSelectionModel().getSelectedItem());
            }else {
                new Alert(Alert.AlertType.WARNING,"Ja fora adicionado").show();
            }
        }
    }

    public void onSelectingMedida() {
        if (!cbMedidas.getSelectionModel().isEmpty()){
            if (cbMedidas.getSelectionModel().isSelected(0)){
                try {
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/criarmedida.fxml"));
                    Stage stage = new Stage();
                    Parent root = loader.load();
                    CriarMedidaController criarMedidaController= loader.getController();
                    stage.setOnHiding(t-> addMedida( criarMedidaController.getMedida()));
                    criarMedidaController.setStage(stage);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.showAndWait();
                }catch (IOException e){
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }
            }
        }
    }

    public void addMedida(MedidaEntity medida){
        if (medida!=null)
            cbMedidas.getItems().add(medida);
    }

    private void populateMedidas(){
        var medida = new MedidaEntity();
        medida.setNome("Adicionar Medida");
        medida.setTamanho(0.0);
        if (!cbMedidas.getItems().isEmpty()){
            cbMedidas.getItems().clear();
        }
        cbMedidas.getItems().add(medida);
        for (MedidaEntity m : medidaDao.getMedidas()){
            cbMedidas.getItems().add(m);
        }
    }

    private PecaEntity peca = null;

    public PecaEntity getPeca() {
        return peca;
    }

    public void onConcluirClick(){
        peca = validadeFields();
        if (peca!=null){
            stage.hide();
            stage.close();
        }else
            new Alert(Alert.AlertType.WARNING,"Verificque os campos, e/o Adicione pelo ao menos uma medida").show();
    }

    @FXML
    public Label lbWarning;

    private PecaEntity validadeFields() {
        if (!tfNome.getText().isEmpty() && !tfPrecoBase.getText().isEmpty() && !tvMedidas.getItems().isEmpty()){
            String msg = Validator.nameValidator(tfNome.getText());
            if (msg!=null){
                lbWarning.setText(msg);
                return null;
            }
            msg = Validator.moneyValidator(tfPrecoBase.getText());
            if (msg!=null){
                lbWarning.setText(msg);
                return null;
            }
            PecaEntity pecaEntity = new PecaEntity();
            pecaEntity.setNome(tfNome.getText());
            pecaEntity.setPrecoBase(Double.valueOf(tfPrecoBase.getText()));
            pecaEntity.setMedidas(tvMedidas.getItems());
            return pecaEntity;
        }

        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateMedidas();
        tbMedidaNome.setCellValueFactory(m -> new SimpleStringProperty( m.getValue().getNome() ));
        tbMedidaTamanho.setCellValueFactory(m -> new SimpleStringProperty( m.getValue().getTamanho().toString() +" cm" ));
    }
}
