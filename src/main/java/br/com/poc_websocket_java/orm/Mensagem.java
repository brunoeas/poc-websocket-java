package br.com.poc_websocket_java.orm;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 * ORM da Mensagem
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@Entity
@Table(name = "mensagem")
public class Mensagem extends PanacheEntityBase {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensagem")
    private Integer idMensagem;

    /**
     * Usuário
     */
    @ManyToOne()
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    /**
     * Texto
     */
    @Column(name = "ds_text", nullable = false)
    private String dsText;

    /**
     * Data que foi enviado
     */
    @Column(name = "dh_enviado", nullable = false)
    private ZonedDateTime dhEnviado;

    public Integer getIdMensagem() {
        return this.idMensagem;
    }

    public void setIdMensagem(final Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(final Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDsText() {
        return this.dsText;
    }

    public void setDsText(final String dsText) {
        this.dsText = dsText;
    }

    public ZonedDateTime getDhEnviado() {
        return this.dhEnviado;
    }

    public void setDhEnviado(final ZonedDateTime dhEnviado) {
        this.dhEnviado = dhEnviado;
    }

}