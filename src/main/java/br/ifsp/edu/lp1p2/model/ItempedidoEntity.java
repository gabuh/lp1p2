package br.ifsp.edu.lp1p2.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "itempedido", schema = "lp1p2")
public class ItempedidoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "tamanho")
    private String tamanho;
    @Basic
    @Column(name = "cor")
    private String cor;
    @Basic
    @Column(name = "valorItem")
    private BigDecimal valorItem;
    @Basic
    @Column(name = "peca_id")
    private Long pecaId;
    @Basic
    @Column(name = "modelo_id")
    private Long modeloId;
    @Basic
    @Column(name = "tecido_id")
    private Long tecidoId;
    @Basic
    @Column(name = "orcamento_id")
    private Long orcamentoId;

    @OneToMany(mappedBy = "itemPedidoId", cascade = CascadeType.ALL)
    private List<AdicionalEntity> adicionais;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public BigDecimal getValorItem() {
        return valorItem;
    }

    public void setValorItem(BigDecimal valorItem) {
        this.valorItem = valorItem;
    }

    public Long getPecaId() {
        return pecaId;
    }

    public void setPecaId(Long pecaId) {
        this.pecaId = pecaId;
    }

    public Long getModeloId() {
        return modeloId;
    }

    public void setModeloId(Long modeloId) {
        this.modeloId = modeloId;
    }

    public Long getTecidoId() {
        return tecidoId;
    }

    public void setTecidoId(Long tecidoId) {
        this.tecidoId = tecidoId;
    }

    public Long getOrcamentoId() {
        return orcamentoId;
    }

    public void setOrcamentoId(Long orcamentoId) {
        this.orcamentoId = orcamentoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItempedidoEntity that = (ItempedidoEntity) o;
        return id == that.id && Objects.equals(tamanho, that.tamanho) && Objects.equals(cor, that.cor) && Objects.equals(valorItem, that.valorItem) && Objects.equals(pecaId, that.pecaId) && Objects.equals(modeloId, that.modeloId) && Objects.equals(tecidoId, that.tecidoId) && Objects.equals(orcamentoId, that.orcamentoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tamanho, cor, valorItem, pecaId, modeloId, tecidoId, orcamentoId);
    }

    public List<AdicionalEntity> getAdicionalEntityList() {
        return adicionais;
    }

    public void setAdicionalEntityList(List<AdicionalEntity> adicionalEntityList) {
        this.adicionais = adicionalEntityList;
    }
}
