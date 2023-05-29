package br.ifsp.edu.lp1p2.lp1p2.model;

public class ItemPedido {
    private Long id;

    private Peca peca;

    private Enum tamanho;

    private Modelo modelo;

    private Tecido tecido;

    private Enum cor;

    private List<Adicional> adicionais;

    private Double valorItem;
}