package br.ifsp.edu.lp1p2.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "medida", schema = "lp1p2", catalog = "")
public class MedidaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @Basic
    @Column(name = "tamanho")
    private Double tamanho;
    @Basic
    @Column(name = "peca_id")
    private Long pecaId;
    @Basic
    @Column(name = "cliente_id")
    private Long clienteId;

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

    public Double getTamanho() {
        return tamanho;
    }

    public void setTamanho(Double tamanho) {
        this.tamanho = tamanho;
    }

    public Long getPecaId() {
        return pecaId;
    }

    public void setPecaId(Long pecaId) {
        this.pecaId = pecaId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedidaEntity that = (MedidaEntity) o;
        return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(tamanho, that.tamanho) && Objects.equals(pecaId, that.pecaId) && Objects.equals(clienteId, that.clienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, tamanho, pecaId, clienteId);
    }
}
