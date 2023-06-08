package br.ifsp.edu.lp1p2.controller;

import br.ifsp.edu.lp1p2.model.ItempedidoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @PersistenceContext
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");


    public void onLoginButtonClick(ActionEvent actionEvent) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ItempedidoEntity itempedido =entityManager.find(ItempedidoEntity.class, 1);
        welcomeText.setText(itempedido.getAdicionalEntityList().get(0).getNome());
    }
}