package br.ifsp.edu.lp1p2.dao.impl;

import br.ifsp.edu.lp1p2.config.JpaFactoryConnection;
import br.ifsp.edu.lp1p2.dao.AdicionalDao;
import br.ifsp.edu.lp1p2.model.AdicionalEntity;

import java.util.ArrayList;

public class AdicionalDaoImpl implements AdicionalDao {


    @Override
    public ArrayList<AdicionalEntity> getAdicionais() {
        ArrayList<AdicionalEntity> adicionais = (ArrayList<AdicionalEntity>) JpaFactoryConnection.getEntityManager().createQuery("SELECT distinct (a) FROM AdicionalEntity AS a",AdicionalEntity.class).getResultList();
        ArrayList<AdicionalEntity> result = new ArrayList<>();
        for (AdicionalEntity a: adicionais) {
            var ad = new AdicionalEntity();
            ad.setMultiplicador(a.getMultiplicador());
            ad.setNome(a.getNome());
            result.add(ad);
        }
        return result;
    }
}
