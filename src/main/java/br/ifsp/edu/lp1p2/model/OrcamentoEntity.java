package br.ifsp.edu.lp1p2.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orcamento", schema = "lp1p2")
public class OrcamentoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "dataCriacao")
    private Timestamp dataCriacao;
    @Basic
    @Column(name = "valorTotal")
    private BigDecimal valorTotal;
    @Basic
    @Column(name = "observacoes")
    private String observacoes;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuarioId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteId;

    @OneToMany(mappedBy = "orcamentoId", cascade = CascadeType.ALL)
    private List<ItempedidoEntity> itensPedidos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public UsuarioEntity getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UsuarioEntity usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ClienteEntity getClienteId() {
        return clienteId;
    }

    public void setClienteId(ClienteEntity clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrcamentoEntity that = (OrcamentoEntity) o;
        return id == that.id && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(valorTotal, that.valorTotal) && Objects.equals(observacoes, that.observacoes) && Objects.equals(usuarioId, that.usuarioId) && Objects.equals(clienteId, that.clienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCriacao, valorTotal, observacoes, usuarioId, clienteId);
    }

    public List<ItempedidoEntity> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItempedidoEntity> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }
}
