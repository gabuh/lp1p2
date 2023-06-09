package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.ClienteDao;
import br.ifsp.edu.lp1p2.model.ClienteEntity;
import jakarta.persistence.Query;

import java.util.List;

public class ClienteDaoImpl implements ClienteDao {


    @Override
    public void create(ClienteEntity cliente) {
    }

    @Override
    public ClienteEntity read(Long id) {
        return JpaFactoryConnection.getEntityManager().find(ClienteEntity.class, id);
    }

    @Override
    public void update(ClienteEntity cliente) {

    }

    @Override
    public void delete(long id) {

    }


}