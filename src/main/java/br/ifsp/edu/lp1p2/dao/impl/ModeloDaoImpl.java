package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.ModeloDao;
import br.ifsp.edu.lp1p2.model.ModeloEntity;
import br.ifsp.edu.lp1p2.model.PecaEntity;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class ModeloDaoImpl implements ModeloDao {
    @Override
    public ArrayList<ModeloEntity> getModelos() {
        return (ArrayList<ModeloEntity>) JpaFactoryConnection.getEntityManager().createQuery("SELECT m FROM ModeloEntity AS m",ModeloEntity.class).getResultList();
    }

    @Override
    public void create(ModeloEntity modelo) {
        EntityTransaction transaction = JpaFactoryConnection.getEntityManager().getTransaction();
        for (PecaEntity p : modelo.getPecas()){
            p.setModeloId(modelo);
        }
        try{
            transaction.begin();
            JpaFactoryConnection.getEntityManager().persist(modelo);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
    }



}