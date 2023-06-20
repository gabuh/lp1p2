package br.ifsp.edu.lp1p2.dao;

import br.ifsp.edu.lp1p2.model.OrcamentoEntity;

import java.util.ArrayList;

public interface OrcamentoDao {

   ArrayList<OrcamentoEntity> getOrcamentos(Long id);

}
