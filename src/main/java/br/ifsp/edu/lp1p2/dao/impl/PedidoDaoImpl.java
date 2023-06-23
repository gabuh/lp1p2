package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.PedidoDao;
import br.ifsp.edu.lp1p2.model.PedidoEntity;
import jakarta.persistence.EntityTransaction;

public class PedidoDaoImpl implements PedidoDao {
    @Override
    public void create(PedidoEntity pedido) {
        EntityTransaction transaction = JpaFactoryConnection.getEntityManager().getTransaction();
        try{
            transaction.begin();
            JpaFactoryConnection.getEntityManager().persist(pedido);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
    }
}
