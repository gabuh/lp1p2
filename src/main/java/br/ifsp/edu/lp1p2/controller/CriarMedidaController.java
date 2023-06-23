package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.model.MedidaEntity;
import br.ifsp.edu.lp1p2.util.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CriarMedidaController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public TextField tfNome;
    @FXML
    public TextField tfTamanho;
    @FXML
    public Label lbWarning;

    MedidaEntity medida;

    public void onConcluirClick() {
        medida = validadeFields();
        if (medida!=null){
            stage.hide();
            stage.close();
        }
    }

    private MedidaEntity validadeFields(){
        if (!tfNome.getText().isEmpty() && !tfTamanho.getText().isEmpty()){
            String msg = Validator.moneyValidator(tfTamanho.getText());
            if (msg!=null){
                lbWarning.setText(msg);
                return null;
            }
            msg = Validator.nameValidator(tfNome.getText());
            if (msg!=null){
                lbWarning.setText(msg);
                return null;
            }
            MedidaEntity medida= new MedidaEntity();
            medida.setTamanho(Double.valueOf(tfTamanho.getText()));
            medida.setNome(tfNome.getText());
            return medida;
        }
        return null;
    }


    public MedidaEntity getMedida() {
        return this.medida;
    }
}
