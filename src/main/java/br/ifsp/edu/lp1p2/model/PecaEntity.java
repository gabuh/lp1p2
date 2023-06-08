package br.ifsp.edu.lp1p2.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "peca", schema = "lp1p2", catalog = "")
public class PecaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @Basic
    @Column(name = "precoBase")
    private Double precoBase;
    @Basic
    @Column(name = "modelo_id")
    private Long modeloId;

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

    public Double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(Double precoBase) {
        this.precoBase = precoBase;
    }

    public Long getModeloId() {
        return modeloId;
    }

    public void setModeloId(Long modeloId) {
        this.modeloId = modeloId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PecaEntity that = (PecaEntity) o;
        return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(precoBase, that.precoBase) && Objects.equals(modeloId, that.modeloId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, precoBase, modeloId);
    }
}
