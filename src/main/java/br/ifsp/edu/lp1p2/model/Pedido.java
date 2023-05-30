package br.ifsp.edu.lp1p2.model;

import java.util.Date;

public class Pedido {
    private Date dataEntrega;

    private Boolean pago;

    private Date dataPagamento;

    private TipoPagamento tipoPagamento;

    private Enum situacao;
}