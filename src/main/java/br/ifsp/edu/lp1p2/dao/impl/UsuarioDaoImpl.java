package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.UsuarioDao;
import br.ifsp.edu.lp1p2.model.UsuarioEntity;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;


public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public UsuarioEntity getUsuarioByEmailAndPassword(String email,String password) {
        Query query = JpaFactoryConnection.getEntityManager().createNamedQuery("selectByEmail", UsuarioEntity.class);
        List<UsuarioEntity> result = query.setParameter(1,email).getResultList();
        if(!result.isEmpty()){
            result.get(0).setSenhaUsuario("-");
            return result.get(0);
        }
     return null;
    }

    @Override
    public boolean checkUserByEmail(String email) {
        Query query = JpaFactoryConnection.getEntityManager().createNamedQuery("selectByEmail", UsuarioEntity.class);
        List<UsuarioEntity> result = query.setParameter(1,email).getResultList();
        return !result.isEmpty();
    }


    @Override
    public void create(UsuarioEntity usuario) {
//        var user = getUsuarioByEmail(usuario.getEmailUsuario());
//        if (user!=null)
//            if (usuario.getEmailUsuario().equals(user.getEmailUsuario())){ return; }
        EntityTransaction transaction = JpaFactoryConnection.getEntityManager().getTransaction();
        try{
            transaction.begin();
            JpaFactoryConnection.getEntityManager().persist(usuario);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }

    }

    @Override
    public boolean checkUserByEmailAndPassword(String email, String password) {
        Query query = JpaFactoryConnection.getEntityManager().createQuery("SELECT u FROM UsuarioEntity AS u WHERE u.emailUsuario LIKE ?1 AND u.senhaUsuario LIKE ?2", UsuarioEntity.class);
        query.setParameter(1,email);
        query.setParameter(2,password);
        List<UsuarioEntity> result = query.getResultList();
        return !result.isEmpty();
    }
}
