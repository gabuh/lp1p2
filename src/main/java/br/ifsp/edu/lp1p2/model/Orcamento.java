package br.ifsp.edu.lp1p2.model;

import java.util.Date;
import java.util.List;

public class Orcamento {
    private Long id;

    private Usuario usuario;

    private Cliente cliente;

    private Date dataCriacao;

    private List<ItemPedido> itemPedidos;

    private Double valorTotal;

    private String observações;
}
