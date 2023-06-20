package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.OrcamentoDao;
import br.ifsp.edu.lp1p2.model.OrcamentoEntity;
import jakarta.persistence.Query;

import java.util.ArrayList;

public class OrcamentoDaoImpl implements OrcamentoDao {


    @Override
    public ArrayList<OrcamentoEntity> getOrcamentos(Long id) {
        Query query = JpaFactoryConnection.getEntityManager().createQuery("SELECT o FROM OrcamentoEntity AS o WHERE o.usuarioId = ?1");
        query.setParameter(1,id);
        return (ArrayList<OrcamentoEntity>) query.getResultList() ;
    }
}
