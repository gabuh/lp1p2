package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.OrcamentoDao;
import br.ifsp.edu.lp1p2.model.ItempedidoEntity;
import br.ifsp.edu.lp1p2.model.OrcamentoEntity;
import br.ifsp.edu.lp1p2.model.UsuarioEntity;
import br.ifsp.edu.lp1p2.util.Usuarioinfo;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class OrcamentoDaoImpl implements OrcamentoDao {


    @Override
    public ArrayList<OrcamentoEntity> getOrcamentos(Long id) {
        return (ArrayList<OrcamentoEntity>) JpaFactoryConnection.getEntityManager().createQuery("SELECT o FROM OrcamentoEntity AS o WHERE o.usuarioId.id = :user",OrcamentoEntity.class).setParameter("user",id).getResultList();
    }

    @Override
    public void create(OrcamentoEntity orcamento) {
        UsuarioEntity user = JpaFactoryConnection.getEntityManager().find(UsuarioEntity.class, Usuarioinfo.id);
        orcamento.setUsuarioId(user);
        for (ItempedidoEntity i : orcamento.getItensPedidos()){
            i.setOrcamentoId(orcamento);
        }
        EntityTransaction transaction = JpaFactoryConnection.getEntityManager().getTransaction();
        try{
            transaction.begin();
            JpaFactoryConnection.getEntityManager().persist(orcamento);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
    }
}
