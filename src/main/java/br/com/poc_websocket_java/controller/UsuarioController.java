package br.com.poc_websocket_java.controller;

import java.time.ZonedDateTime;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.poc_websocket_java.converter.UsuarioConverter;
import br.com.poc_websocket_java.dto.UsuarioDTO;
import br.com.poc_websocket_java.orm.Mensagem;
import br.com.poc_websocket_java.orm.Usuario;
import br.com.poc_websocket_java.socket.WebSocketServer;
import br.com.poc_websocket_java.socket.enumeration.EventsEnum;
import br.com.poc_websocket_java.util.Utils;

/**
 * Controller do Usuário
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@RequestScoped
public class UsuarioController {

    /**
     * Converter do Usuário
     */
    @Inject
    UsuarioConverter usuarioConverter;

    /**
     * Utils
     */
    @Inject
    Utils utils;

    /**
     * Salva um novo Usuário e emite ele para os Clients
     * 
     * @param dto - DTO do Usuário
     * @return DTO do Usuário novo
     * @throws Exception Exceção genérica
     */
    @Transactional
    public UsuarioDTO saveUsuario(final UsuarioDTO dto) throws Exception {

        final Usuario orm = usuarioConverter.dtoToOrm(dto);

        this.validaUsuarioDuplicado(orm.getNmUsuario());

        this.persistUsuario(orm, dto);

        WebSocketServer.emitToAllClients(EventsEnum.NEW_USER, dto);

        return dto;
    }

    /**
     * Deleta um Usuário pelo ID e emite aos Clients
     * 
     * @param id - ID do Usuário
     * @throws Exception Exceção genérica
     */
    @Transactional
    public void deleteUsuarioById(final Integer id) throws Exception {

        final Usuario orm = utils.validateAndReturnEntity(id, Usuario.class);
        Mensagem.delete("usuario", orm);
        orm.flush();
        orm.delete();

        final UsuarioDTO dto = usuarioConverter.ormToDto(orm);
        WebSocketServer.emitToAllClients(EventsEnum.USER_LOGGED_OFF, dto);
    }

    /**
     * Retorna um Usuário pelo ID
     * 
     * @param id - ID do Usuário
     * @return O DTO do Usuário
     * @throws Exception Exceção genérica
     */
    @Transactional
    public UsuarioDTO findById(final Integer id) throws Exception {

        final Usuario orm = utils.validateAndReturnEntity(id, Usuario.class);

        final UsuarioDTO dto = usuarioConverter.ormToDto(orm);
        return dto;
    }

    /**
     * Persiste o Usuário na base de dados
     * 
     * @param orm - ORM do Usuário
     * @param dto - DTO do Usuário
     * @throws Exception Exceção genérica
     */
    private void persistUsuario(final Usuario orm, final UsuarioDTO dto) throws Exception {

        orm.setIdUsuario(null);
        orm.setDhCriacao(ZonedDateTime.now());
        orm.persistAndFlush();

        dto.setIdUsuario(orm.getIdUsuario());
    }

    /**
     * Valida se ja existe um Usuário na base de dados com o mesmo nome
     * 
     * @param nome - Nome para validar
     * @throws Exception Caso o nome ja exista na base de dados
     */
    private void validaUsuarioDuplicado(final String nome) throws Exception {

        final Usuario usuario = Usuario.find("nmUsuario", nome).firstResult();

        if (usuario != null) {
            throw new Exception("USUARIO_DUPLICADO");
        }
    }

}