package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.AdicionalDao;
import br.ifsp.edu.lp1p2.model.AdicionalEntity;
import jakarta.persistence.Query;

import java.util.ArrayList;

public class AdicionalDaoImpl implements AdicionalDao {


    @Override
    public ArrayList<AdicionalEntity> getAdicionais() {
        Query query = JpaFactoryConnection.getEntityManager().createQuery("SELECT a FROM AdicionalEntity AS a");
        return (ArrayList<AdicionalEntity>) query.getResultList();
    }
}
