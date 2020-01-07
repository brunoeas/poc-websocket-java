package br.com.poc_websocket_java.orm;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 * ORM do Usuário
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@Entity
@Table(name = "usuario")
public class Usuario extends PanacheEntityBase {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    /**
     * Nome
     */
    @Column(name = "nm_usuario", nullable = false, length = 100)
    private String nmUsuario;

    /**
     * Data de Criação
     */
    @Column(name = "dh_criacao", nullable = false)
    private ZonedDateTime dhCriacao;

    public Integer getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNmUsuario() {
        return this.nmUsuario;
    }

    public void setNmUsuario(final String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public ZonedDateTime getDhCriacao() {
        return this.dhCriacao;
    }

    public void setDhCriacao(final ZonedDateTime dhCriacao) {
        this.dhCriacao = dhCriacao;
    }

}