package br.ifsp.edu.lp1p2.dao;

import br.ifsp.edu.lp1p2.model.UsuarioEntity;

public interface UsuarioDao {
    public UsuarioEntity getUsuarioByEmailAndPassword(String email, String password);

    public boolean checkUserByEmailAndPassword(String email, String password);

    public boolean checkUserByEmail(String email);
    public void create(UsuarioEntity usuario);

}
