package br.com.poc_websocket_java.converter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.poc_websocket_java.converter.abstractconverter.AbstractConverter;
import br.com.poc_websocket_java.dto.MensagemDTO;
import br.com.poc_websocket_java.orm.Mensagem;

/**
 * Converter da Mensagem
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@RequestScoped
public class MensagemConverter extends AbstractConverter<Mensagem, MensagemDTO> {

    /**
     * Converter do Usuário
     */
    @Inject
    UsuarioConverter usuarioConverter;

    /**
     * Converte um DTO para ORM
     */
    @Override
    public Mensagem dtoToOrm(final MensagemDTO dto, final Mensagem orm) {

        orm.setIdMensagem(dto.getIdMensagem());
        orm.setDsText(dto.getDsText());
        orm.setDhEnviado(dto.getDhEnviado());

        return orm;
    }

    /**
     * Converte um ORM para DTO
     */
    @Override
    public MensagemDTO ormToDto(final Mensagem orm, final MensagemDTO dto) {

        dto.setIdMensagem(orm.getIdMensagem());
        dto.setDsText(orm.getDsText());
        dto.setDhEnviado(orm.getDhEnviado());

        if (orm.getUsuario() != null) {
            dto.setUsuario(usuarioConverter.ormToDto(orm.getUsuario()));
        }

        return dto;
    }

    /**
     * Retorna nova instância do ORM
     */
    @Override
    public Mensagem getOrmNewInstance() {
        return new Mensagem();
    }

    /**
     * Retorna nova instância do DTO
     */
    @Override
    public MensagemDTO getDtoNewInstance() {
        return new MensagemDTO();
    }

}