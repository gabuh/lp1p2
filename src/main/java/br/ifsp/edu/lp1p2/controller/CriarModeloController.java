package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.dao.ModeloDao;
import br.ifsp.edu.lp1p2.dao.PecaDao;
import br.ifsp.edu.lp1p2.dao.impl.ModeloDaoImpl;
import br.ifsp.edu.lp1p2.dao.impl.PecaDaoImpl;
import br.ifsp.edu.lp1p2.model.ModeloEntity;
import br.ifsp.edu.lp1p2.model.PecaEntity;
import br.ifsp.edu.lp1p2.util.Validator;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CriarModeloController implements Initializable {

    private final PecaDao pecaDao;


    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public ComboBox<PecaEntity> cbPecas;

    @FXML
    public TableColumn<PecaEntity, String> tbPecaNome;

    @FXML
    public TableColumn<PecaEntity, String> tbPrecoBase;

    private void populateCbPecas(){
        if (!cbPecas.getItems().isEmpty()){
            cbPecas.getItems().clear();
        }
        for (PecaEntity p:pecaDao.getPecas()){
            cbPecas.getItems().add(p);
        }
    }

    @FXML
    public Spinner<Double> spMultiplicador;

    @FXML
    public TextField tfNome;

    @FXML
    public Label lbWarning;

    @FXML
    public TableView<PecaEntity> tvPecas;

    @FXML
    void onAdicionarClick() {
        if (!cbPecas.getSelectionModel().isEmpty()){
            if (!tvPecas.getItems().contains(cbPecas.getSelectionModel().getSelectedItem())){
                tvPecas.getItems().add(cbPecas.getSelectionModel().getSelectedItem());
            }else {
                new Alert(Alert.AlertType.WARNING,"Ja fora Adicionado").show();
            }
        }
    }

    @FXML
    void onRemoveClick() {
        if (!tvPecas.getSelectionModel().isEmpty()){
            tvPecas.getItems().remove(tvPecas.getSelectionModel().getSelectedItem());
        }
    }


    ModeloEntity modelo;
    public void onConcluirClick(){
        modelo = validateFields();
        if (modelo != null){
            stage.hide();
            stage.close();
        }
    }


    private ModeloEntity validateFields(){
        if (!tfNome.getText().isEmpty() && !tvPecas.getItems().isEmpty()){
            String msg = Validator.nameValidator(tfNome.getText());
            if (msg!=null){
                lbWarning.setText(msg);
                return null;
            }
            ModeloEntity modeloEntity = new ModeloEntity();
            modeloEntity.setNome(tfNome.getText());
            modeloEntity.setMultiplicador(spMultiplicador.getValue());
            modeloEntity.setPecas(cbPecas.getItems());
            return modeloEntity;
        }
        return null;
    }

    public CriarModeloController() {
        pecaDao = new PecaDaoImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbPecaNome.setCellValueFactory( p -> new SimpleStringProperty( p.getValue().getNome()));
        tbPrecoBase.setCellValueFactory( p-> new SimpleStringProperty( p.getValue().getPrecoBase().toString()));
        spMultiplicador.setValueFactory( new SpinnerValueFactory.DoubleSpinnerValueFactory(0,1,0.5,0.1));
        populateCbPecas();
    }


    public ModeloEntity getModelo() {
        return this.modelo;
    }
}
