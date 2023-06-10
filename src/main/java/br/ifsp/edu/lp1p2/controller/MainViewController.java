package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.dao.impl.TecidoDaoImpl;
import br.ifsp.edu.lp1p2.model.TecidoEntity;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



public class MainViewController {

    @FXML
    TableView<TecidoEntity> tvTecido;
    @FXML
    TableColumn<TecidoEntity, String> tbTecidoNome;
    @FXML
    TableColumn<TecidoEntity, String> tbTecidoPreco;

    public void populateTvTecido(){
        if (!tvTecido.getItems().isEmpty()) tvTecido.getItems().clear();
        tbTecidoNome.setCellValueFactory( t -> new SimpleStringProperty(t.getValue().getNome()) );
        tbTecidoPreco.setCellValueFactory(t -> new SimpleStringProperty( "R$ "+t.getValue().getPreco().toString()) );
        for (TecidoEntity tecido: new TecidoDaoImpl().getTecidos()) {
            tvTecido.getItems().add(tecido);
        }
    }

    public MainViewController() {
        Platform.runLater(this::populateTvTecido);
    }
}
