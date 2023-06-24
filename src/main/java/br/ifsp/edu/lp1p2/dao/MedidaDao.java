package br.ifsp.edu.lp1p2.dao;

import br.ifsp.edu.lp1p2.model.MedidaEntity;

import java.util.ArrayList;

public interface MedidaDao {
    ArrayList<MedidaEntity>getMedidas();

    void create(MedidaEntity medida);
}
