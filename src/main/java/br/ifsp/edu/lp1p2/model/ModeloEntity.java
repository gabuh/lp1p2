package br.ifsp.edu.lp1p2.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "modelo")
public class ModeloEntity {
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

    @OneToMany(mappedBy = "modeloId", cascade = CascadeType.ALL)
    private List<PecaEntity> pecas;

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
        ModeloEntity that = (ModeloEntity) o;
        return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(multiplicador, that.multiplicador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, multiplicador);
    }

    public List<PecaEntity> getPecas() {
        return pecas;
    }

    public void setPecas(List<PecaEntity> pecas) {
        this.pecas = pecas;
    }

    @Override
    public String toString() {
        return nome +" : "+ multiplicador;
    }
}
