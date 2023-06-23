package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.PecaDao;
import br.ifsp.edu.lp1p2.model.AdicionalEntity;
import br.ifsp.edu.lp1p2.model.MedidaEntity;
import br.ifsp.edu.lp1p2.model.PecaEntity;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class PecaDaoImpl implements PecaDao {
    @Override
    public ArrayList<PecaEntity> getPecas() {
        return (ArrayList<PecaEntity>) JpaFactoryConnection.getEntityManager().createQuery("SELECT p FROM PecaEntity AS p",PecaEntity.class).getResultList();
    }

    @Override
    public void create(PecaEntity peca) {
        for (MedidaEntity m : peca.getMedidas()){
            m.setPecaId(peca);
        }
        EntityTransaction transaction = JpaFactoryConnection.getEntityManager().getTransaction();
        try{
            transaction.begin();
            JpaFactoryConnection.getEntityManager().persist(peca);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
    }
}