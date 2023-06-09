package br.ifsp.edu.lp1p2.dao;

import br.ifsp.edu.lp1p2.model.ClienteEntity;

public interface ClienteDao {
    void create(ClienteEntity cliente);
    ClienteEntity read(Long id);
    void update(ClienteEntity cliente);
    void delete(long id);
}
