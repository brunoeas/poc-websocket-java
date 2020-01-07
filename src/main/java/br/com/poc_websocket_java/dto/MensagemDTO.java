package br.com.poc_websocket_java.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * DTO da Mensagem
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
public class MensagemDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer idMensagem;

    /**
     * Usuário
     */
    private UsuarioDTO usuario;

    /**
     * Texto
     */
    private String dsText;

    /**
     * Data que foi enviado
     */
    private ZonedDateTime dhEnviado;

    public Integer getIdMensagem() {
        return this.idMensagem;
    }

    public void setIdMensagem(final Integer idMensagem) {
        this.idMensagem = idMensagem;
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

    public UsuarioDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(final UsuarioDTO usuario) {
        this.usuario = usuario;
    }

}