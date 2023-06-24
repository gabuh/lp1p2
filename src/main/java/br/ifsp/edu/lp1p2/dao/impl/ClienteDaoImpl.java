package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.ClienteDao;
import br.ifsp.edu.lp1p2.model.ClienteEntity;
import br.ifsp.edu.lp1p2.model.MedidaEntity;
import br.ifsp.edu.lp1p2.model.PecaEntity;
import br.ifsp.edu.lp1p2.model.UsuarioEntity;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements ClienteDao {


    @Override
    public void create(ClienteEntity cliente) {
        EntityTransaction transaction = JpaFactoryConnection.getEntityManager().getTransaction();
        for (MedidaEntity m : cliente.getMedidas()){
            m.setClienteId(cliente);
        }
        try{
            transaction.begin();
            JpaFactoryConnection.getEntityManager().persist(cliente);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
    }

    @Override
    public ClienteEntity read(Long id) {
        return JpaFactoryConnection.getEntityManager().find(ClienteEntity.class, id);
    }

    @Override
    public ArrayList<ClienteEntity> getClients() {
        return (ArrayList<ClienteEntity>) JpaFactoryConnection.getEntityManager().createQuery("SELECT c FROM ClienteEntity AS c",ClienteEntity.class).getResultList();
    }


    public boolean verifyEmail(String email){
        List<ClienteEntity> result = JpaFactoryConnection.getEntityManager().createQuery("SELECT c FROM ClienteEntity AS c WHERE c.email LIKE :email", ClienteEntity.class).setParameter("email",email).getResultList();
        return !result.isEmpty();
    }

    @Override
    public void update(ClienteEntity cliente) {
        EntityTransaction transaction = JpaFactoryConnection.getEntityManager().getTransaction();
        try{
            transaction.begin();
            JpaFactoryConnection.getEntityManager().persist(cliente);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(long id) {

    }


}
