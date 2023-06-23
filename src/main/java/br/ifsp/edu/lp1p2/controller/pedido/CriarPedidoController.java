package br.ifsp.edu.lp1p2.controller.pedido;

import br.ifsp.edu.lp1p2.model.PedidoEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CriarPedidoController implements Initializable {

    Stage stage;

    @FXML
    public DatePicker dpDataEntrega;

    @FXML
    public ComboBox<TipoPagamento> cbTipoPagamento;
    private void populateTipoPagamento(){
        if (!cbTipoPagamento.getItems().isEmpty()) {
            cbTipoPagamento.getItems().clear();
        }
        cbTipoPagamento.getItems().addAll(TipoPagamento.values());
        cbTipoPagamento.getSelectionModel().select(0);
    }


    @FXML
    public ComboBox<Situacao> cbSituacao;
    private void populateSituacao(){
        if (!cbSituacao.getItems().isEmpty()) {
            cbSituacao.getItems().clear();
        }
        cbSituacao.getItems().addAll(Situacao.values());
        cbSituacao.getSelectionModel().select(0);
    }

    @FXML
    public CheckBox checkAgora;

    @FXML
    public CheckBox checkPosteriormente;

    @FXML
    public Button btConcluir;

    private PedidoEntity pedido = null;

    public void onConcluirClick(){
      pedido = validateFields();
      if (pedido!=null){
          stage.hide();
          stage.close();
      }else {
          btConcluir.setDisable(true);
          new Alert(Alert.AlertType.WARNING, "Verifique os campos").showAndWait();
          btConcluir.setDisable(false);
      }
    }

    private PedidoEntity validateFields(){
        if (checkAgora.isSelected() || checkPosteriormente.isSelected() && !cbSituacao.getSelectionModel().isEmpty() && !cbTipoPagamento.getSelectionModel().isEmpty() && dpDataEntrega.getValue() != null){
            PedidoEntity pedidoEntity =  new PedidoEntity();
            pedidoEntity.setDataEntrega(Timestamp.valueOf(dpDataEntrega.getValue().atStartOfDay()));
            if (checkAgora.isSelected()) {
                pedidoEntity.setPago((byte) 1);
                pedidoEntity.setDataPagamento(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
            } else {
                pedidoEntity.setPago((byte) 0);
            }
            pedidoEntity.setSituacao(cbSituacao.getSelectionModel().getSelectedItem().name());
            pedidoEntity.setTipoPagamento(cbTipoPagamento.getSelectionModel().getSelectedItem().name());
            return pedidoEntity;
        }
        return null;
    }


    public PedidoEntity getPedido() {
        return this.pedido;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }




    public void onCheckAgoraClick(){
        if (checkPosteriormente.isSelected()){
            checkPosteriormente.setSelected(false);
        }
    }

    public void onCheckPosteriormenteClick(){
        if (checkAgora.isSelected()){
            checkAgora.setSelected(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateSituacao();
        populateTipoPagamento();
    }
}
