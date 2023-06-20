package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemOrcamentoController {

    public void onCriarItemClick() {
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/criaritem.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
