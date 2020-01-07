package br.com.poc_websocket_java.converter;

import javax.enterprise.context.RequestScoped;

import br.com.poc_websocket_java.converter.abstractconverter.AbstractConverter;
import br.com.poc_websocket_java.dto.UsuarioDTO;
import br.com.poc_websocket_java.orm.Usuario;

/**
 * Converter do Usuário
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@RequestScoped
public class UsuarioConverter extends AbstractConverter<Usuario, UsuarioDTO> {

    /**
     * Converte um DTO para ORM
     */
    @Override
    public Usuario dtoToOrm(final UsuarioDTO dto, final Usuario orm) {

        orm.setIdUsuario(dto.getIdUsuario());
        orm.setNmUsuario(dto.getNmUsuario());
        orm.setDhCriacao(dto.getDhCriacao());

        return orm;
    }

    /**
     * Converte um ORM para DTO
     */
    @Override
    public UsuarioDTO ormToDto(final Usuario orm, final UsuarioDTO dto) {

        dto.setIdUsuario(orm.getIdUsuario());
        dto.setNmUsuario(orm.getNmUsuario());
        dto.setDhCriacao(orm.getDhCriacao());

        return dto;
    }

    /**
     * Retorna nova instância do ORM
     */
    @Override
    public Usuario getOrmNewInstance() {
        return new Usuario();
    }

    /**
     * Retorna nova instância do DTO
     */
    @Override
    public UsuarioDTO getDtoNewInstance() {
        return new UsuarioDTO();
    }

}