package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.dao.TecidoDao;
import br.ifsp.edu.lp1p2.dao.impl.TecidoDaoImpl;
import br.ifsp.edu.lp1p2.model.TecidoEntity;
import br.ifsp.edu.lp1p2.util.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class CriarTecidoController {
    private final TecidoDao tecidoDao;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public TextField tfNome;
    @FXML
    public TextField tfPrecoBase;

    @FXML
    public Label lbWarning;

    public CriarTecidoController() {
        tecidoDao = new TecidoDaoImpl();
    }

    public void onConcluirClick() {
        TecidoEntity tecido = validadeFields();
        if (tecido!=null){
            tecidoDao.create(tecido);
            stage.close();
        }
    }

    private TecidoEntity validadeFields(){
        if (!tfNome.getText().isEmpty() && !tfPrecoBase.getText().isEmpty()){
                var msg = Validator.moneyValidator(tfPrecoBase.getText());
                if (msg!=null){
                    lbWarning.setText(msg);
                    return null;
                }
                msg = Validator.nameValidator(tfNome.getText());
                if (msg!=null){
                    lbWarning.setText(msg);
                    return null;
                }
                TecidoEntity tecido = new TecidoEntity();
                tecido.setPreco(new BigDecimal(tfPrecoBase.getText()));
                tecido.setNome(tfNome.getText());
                return tecido;
        }
        return null;
    }


}

