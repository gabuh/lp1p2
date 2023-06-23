package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.ModeloDao;
import br.ifsp.edu.lp1p2.model.ModeloEntity;

import java.util.ArrayList;

public class ModeloDaoImpl implements ModeloDao {
    @Override
    public ArrayList<ModeloEntity> getModelos() {
        return (ArrayList<ModeloEntity>) JpaFactoryConnection.getEntityManager().createQuery("SELECT m FROM ModeloEntity AS m",ModeloEntity.class).getResultList();
    }
}
