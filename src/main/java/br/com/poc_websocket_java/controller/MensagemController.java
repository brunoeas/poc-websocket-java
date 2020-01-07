package br.com.poc_websocket_java.controller;

import java.time.ZonedDateTime;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.poc_websocket_java.converter.MensagemConverter;
import br.com.poc_websocket_java.dto.MensagemDTO;
import br.com.poc_websocket_java.orm.Mensagem;
import br.com.poc_websocket_java.orm.Usuario;
import br.com.poc_websocket_java.socket.WebSocketServer;
import br.com.poc_websocket_java.socket.enumeration.EventsEnum;
import br.com.poc_websocket_java.util.Utils;
import io.quarkus.panache.common.Sort;

/**
 * Controller da Mensagem
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@RequestScoped
public class MensagemController {

    /**
     * Converter da Mensagem
     */
    @Inject
    MensagemConverter mensagemConverter;

    /**
     * Utils
     */
    @Inject
    Utils utils;

    /**
     * Salva uma nova Mensagem e envia para os outros Clients a Mensagem nova
     * 
     * @param dto - DTO da Mensagem
     * @return DTO da Mensagem nova
     * @throws Exception Exceção genérica
     */
    @Transactional
    public MensagemDTO saveMensagem(final MensagemDTO dto) throws Exception {

        final Mensagem orm = mensagemConverter.dtoToOrm(dto);

        this.persistMensagem(orm, dto);

        WebSocketServer.emitToAllClients(EventsEnum.NEW_MESSAGE_RECEIVED, dto);

        return dto;
    }

    /**
     * Retorna as Mensagens que foram enviadas depois que o Usuário passado por
     * parâmetro foi criado
     * 
     * @param idUsuario - ID do Usuário
     * @return Uma lista de Mensagens filtradas
     * @throws Exception Exceção genérica
     */
    @Transactional
    public List<MensagemDTO> findMensagensAfterUserCreation(final Integer idUsuario) throws Exception {

        final Usuario usuario = utils.validateAndReturnEntity(idUsuario, Usuario.class);

        final List<Mensagem> ormList = Mensagem.list("dhEnviado >= ?1", Sort.by("dhEnviado").descending(),
                usuario.getDhCriacao());

        return mensagemConverter.ormListToDtoList(ormList);
    }

    /**
     * Persiste a Mensagem na base de dados
     * 
     * @param orm - ORM da Mensagem
     * @param dto - DTO da Mensagem
     * @throws Exception Exceção genérica
     */
    private void persistMensagem(final Mensagem orm, final MensagemDTO dto) throws Exception {

        final Usuario usuario = utils.validateAndReturnEntity(dto.getUsuario().getIdUsuario(), Usuario.class);
        orm.setUsuario(usuario);

        orm.setIdMensagem(null);
        orm.setDhEnviado(ZonedDateTime.now());
        orm.persistAndFlush();

        dto.setIdMensagem(orm.getIdMensagem());
    }

}