package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.TecidoDao;
import br.ifsp.edu.lp1p2.model.TecidoEntity;
import jakarta.persistence.Query;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TecidoDaoImpl implements TecidoDao {


    @Override
    public void create(TecidoEntity tecido) {

    }

    @Override
    public ObservableList<TecidoEntity> getTecidosObs() {
        Query query = JpaFactoryConnection.getEntityManager().createQuery("SELECT t FROM TecidoEntity AS t");
        return (ObservableList<TecidoEntity>) query.getResultList();
    }

    @Override
    public ArrayList<TecidoEntity> getTecidos() {
        return (ArrayList<TecidoEntity>) JpaFactoryConnection.getEntityManager().createQuery("SELECT t FROM TecidoEntity AS t").getResultList();
    }

    @Override
    public void update(TecidoEntity tecido) {

    }
}
