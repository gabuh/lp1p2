package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.MedidaDao;
import br.ifsp.edu.lp1p2.model.MedidaEntity;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class MedidaDaoImpl implements MedidaDao {
    @Override
    public ArrayList<MedidaEntity> getMedidas() {
        return (ArrayList<MedidaEntity>) JpaFactoryConnection.getEntityManager().createQuery("SELECT m FROM MedidaEntity AS m ", MedidaEntity.class).getResultList();
    }

    @Override
    public void create(MedidaEntity medida) {
        EntityTransaction transaction = JpaFactoryConnection.getEntityManager().getTransaction();
        try{
            transaction.begin();
            JpaFactoryConnection.getEntityManager().persist(medida);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
    }
}
