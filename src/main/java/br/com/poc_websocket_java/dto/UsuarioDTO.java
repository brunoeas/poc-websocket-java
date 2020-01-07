package br.com.poc_websocket_java.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * DTO do Usuário
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
public class UsuarioDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer idUsuario;

    /**
     * Nome
     */
    private String nmUsuario;

    /**
     * Data de Criação
     */
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