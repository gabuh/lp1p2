package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.ModeloDao;
import br.ifsp.edu.lp1p2.model.ModeloEntity;
import jakarta.persistence.Query;

import java.util.ArrayList;

public class ModeloDaoImpl implements ModeloDao {
    @Override
    public ArrayList<ModeloEntity> getModelos() {
        Query query = JpaFactoryConnection.getEntityManager().createQuery("SELECT m FROM ModeloEntity AS m");
        return (ArrayList<ModeloEntity>) query.getResultList();
    }
}
