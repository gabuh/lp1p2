package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.ItempedidoDao;
import br.ifsp.edu.lp1p2.model.AdicionalEntity;
import br.ifsp.edu.lp1p2.model.ItempedidoEntity;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class ItempedidoDaoImpl implements ItempedidoDao {
    @Override
    public void create(ItempedidoEntity itempedido) {
        EntityTransaction transaction = JpaFactoryConnection.getEntityManager().getTransaction();
        try{
            transaction.begin();
            JpaFactoryConnection.getEntityManager().persist(itempedido);
            for (AdicionalEntity a: itempedido.getAdicionais()) {
                a.setItemPedidoId(itempedido);
                JpaFactoryConnection.getEntityManager().persist(a);
            }
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
    }

    @Override
    public ArrayList<ItempedidoEntity> getItemPedidos() {
        return (ArrayList<ItempedidoEntity>) JpaFactoryConnection.getEntityManager().createQuery("SELECT i FROM ItempedidoEntity as i",ItempedidoEntity.class).getResultList();
    }
}
