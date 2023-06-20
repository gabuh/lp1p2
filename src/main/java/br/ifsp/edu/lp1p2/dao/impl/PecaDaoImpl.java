package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.PecaDao;
import br.ifsp.edu.lp1p2.model.PecaEntity;
import jakarta.persistence.Query;

import java.util.ArrayList;

public class PecaDaoImpl implements PecaDao {
    @Override
    public ArrayList<PecaEntity> getPecas() {
        Query query = JpaFactoryConnection.getEntityManager().createQuery("SELECT p FROM PecaEntity AS p");
        return (ArrayList<PecaEntity>) query.getResultList();
    }
}
