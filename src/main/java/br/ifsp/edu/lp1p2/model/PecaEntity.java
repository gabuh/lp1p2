package br.ifsp.edu.lp1p2.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "peca", schema = "lp1p2")
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


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "modelo_id")
    private ModeloEntity modeloId;

    public ModeloEntity getModeloId() {
        return modeloId;
    }

    public void setModeloId(ModeloEntity modeloId) {
        this.modeloId = modeloId;
    }

    @OneToMany(mappedBy = "pecaId", cascade = CascadeType.ALL)
    private List<MedidaEntity> medidas;

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

    public List<MedidaEntity> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<MedidaEntity> medidas) {
        this.medidas = medidas;
    }

    @Override
    public String toString() {
        return  nome +" : R$"+precoBase;
    }
}


