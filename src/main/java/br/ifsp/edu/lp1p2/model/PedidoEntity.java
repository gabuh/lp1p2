package br.ifsp.edu.lp1p2.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "pedido", schema = "lp1p2", catalog = "")
public class PedidoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "dataEntrega")
    private Timestamp dataEntrega;
    @Basic
    @Column(name = "pago")
    private Byte pago;
    @Basic
    @Column(name = "dataPagamento")
    private Timestamp dataPagamento;
    @Basic
    @Column(name = "tipoPagamento")
    private String tipoPagamento;
    @Basic
    @Column(name = "situacao")
    private String situacao;
    @Basic
    @Column(name = "orcamento_id")
    private Long orcamentoId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Timestamp dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Byte getPago() {
        return pago;
    }

    public void setPago(Byte pago) {
        this.pago = pago;
    }

    public Timestamp getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Timestamp dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
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
        PedidoEntity that = (PedidoEntity) o;
        return id == that.id && Objects.equals(dataEntrega, that.dataEntrega) && Objects.equals(pago, that.pago) && Objects.equals(dataPagamento, that.dataPagamento) && Objects.equals(tipoPagamento, that.tipoPagamento) && Objects.equals(situacao, that.situacao) && Objects.equals(orcamentoId, that.orcamentoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataEntrega, pago, dataPagamento, tipoPagamento, situacao, orcamentoId);
    }
}
