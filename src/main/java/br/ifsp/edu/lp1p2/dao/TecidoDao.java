package br.ifsp.edu.lp1p2.dao;

import br.ifsp.edu.lp1p2.model.TecidoEntity;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface TecidoDao {
    public void create (TecidoEntity tecido);

    public ObservableList<TecidoEntity> getTecidosObs ();

    public ArrayList<TecidoEntity> getTecidos ();

    public void update (TecidoEntity tecido);

}
