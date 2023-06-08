package br.ifsp.edu.lp1p2.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "adicional", schema = "lp1p2")
public class AdicionalEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @Basic
    @Column(name = "multiplicador")
    private Double multiplicador;

    @ManyToOne
    @JoinColumn(name = "itemPedido_id")
    private ItempedidoEntity itemPedidoId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(Double multiplicador) {
        this.multiplicador = multiplicador;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdicionalEntity that = (AdicionalEntity) o;
        return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(multiplicador, that.multiplicador) && Objects.equals(itemPedidoId, that.itemPedidoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, multiplicador, itemPedidoId);
    }
}
