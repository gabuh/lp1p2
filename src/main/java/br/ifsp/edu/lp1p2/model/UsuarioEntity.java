package br.ifsp.edu.lp1p2.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "lp1p2", catalog = "")
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "nomeUsuario")
    private String nomeUsuario;
    @Basic
    @Column(name = "senhaUsuario")
    private String senhaUsuario;
    @Basic
    @Column(name = "emailUsuario")
    private String emailUsuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return id == that.id && Objects.equals(nomeUsuario, that.nomeUsuario) && Objects.equals(senhaUsuario, that.senhaUsuario) && Objects.equals(emailUsuario, that.emailUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeUsuario, senhaUsuario, emailUsuario);
    }
}
