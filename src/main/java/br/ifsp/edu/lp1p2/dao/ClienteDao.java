package br.ifsp.edu.lp1p2.dao;

import br.ifsp.edu.lp1p2.model.ClienteEntity;

import java.util.ArrayList;

public interface ClienteDao {
    void create(ClienteEntity cliente);
    ClienteEntity read(Long id);

    ArrayList<ClienteEntity> getClients();
    void update(ClienteEntity cliente);
    void delete(long id);
}
