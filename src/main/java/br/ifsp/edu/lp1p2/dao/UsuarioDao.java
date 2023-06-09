package br.ifsp.edu.lp1p2.dao;

import br.ifsp.edu.lp1p2.model.UsuarioEntity;

public interface UsuarioDao {
    public UsuarioEntity getUsuarioByEmail(String email);

    public void create(UsuarioEntity usuario);

}
