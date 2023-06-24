package br.ifsp.edu.lp1p2.dao;

import br.ifsp.edu.lp1p2.model.ItempedidoEntity;

import java.util.ArrayList;

public interface ItempedidoDao {

    void create(ItempedidoEntity itempedido);

    ArrayList<ItempedidoEntity> getItemPedidos();


}
