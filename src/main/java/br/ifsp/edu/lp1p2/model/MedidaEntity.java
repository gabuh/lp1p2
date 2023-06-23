package br.ifsp.edu.lp1p2.model;

import jakarta.persistence.*;


import java.util.Objects;

@Entity
@Table(name = "medida", schema = "lp1p2")
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

    @ManyToOne
    @JoinColumn(name = "peca_id")
    private PecaEntity pecaId;

    public PecaEntity getPecaId() {
        return pecaId;
    }

    public void setPecaId(PecaEntity pecaId) {
        this.pecaId = pecaId;
    }

    public ClienteEntity getClienteId() {
        return clienteId;
    }

    public void setClienteId(ClienteEntity clienteId) {
        this.clienteId = clienteId;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteId;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedidaEntity that = (MedidaEntity) o;
        return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(tamanho, that.tamanho) && Objects.equals(pecaId, that.pecaId) && Objects.equals(clienteId, that.clienteId);
    }

    @Override
    public String toString() {
        return nome +" : " + tamanho;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, tamanho, pecaId, clienteId);
    }
}
