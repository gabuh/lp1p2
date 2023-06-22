package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.model.AdicionalEntity;
import br.ifsp.edu.lp1p2.util.Validator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CriarAdicionalController implements Initializable {
    private Stage stage;
    private AdicionalEntity adicional = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     spAdicionalMultiplicador.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,1,0.5,0.1));
    }

    @FXML
    public Spinner<Double> spAdicionalMultiplicador;
    @FXML
    public TextField tfAdicionalNome;
    @FXML
    public Label lbWarning;



    public void onConcluirClick(){
        if (validateFields()){
            new Alert(Alert.AlertType.INFORMATION,"Adicional Criado").showAndWait();
            stage.hide();
            stage.close();
        }
    }



    private boolean validateFields(){
        if (!tfAdicionalNome.getText().isEmpty() && spAdicionalMultiplicador.getValue() != null){
            String msg = Validator.nameValidator(tfAdicionalNome.getText());
            if (msg != null){ lbWarning.setText(msg); return false; }

            if (spAdicionalMultiplicador.getValue() > 1 || spAdicionalMultiplicador.getValue() < 0){
                lbWarning.setText("O multiplicador deve ser entre 0 e 1 - ex: 0.2");
                return false;
            }
            adicional = new AdicionalEntity();
            adicional.setNome(tfAdicionalNome.getText());
            adicional.setMultiplicador(spAdicionalMultiplicador.getValue());
            return true;
        }
        lbWarning.setText("Preencha os campos");
        return false;
    }




    public AdicionalEntity getAdicional() {
        return adicional;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
