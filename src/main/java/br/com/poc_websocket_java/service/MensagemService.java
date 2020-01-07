package br.com.poc_websocket_java.service;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.poc_websocket_java.controller.MensagemController;
import br.com.poc_websocket_java.dto.MensagemDTO;

/**
 * Service da Mensagem
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MensagemService {

    /**
     * Controller da Mensagem
     */
    @Inject
    MensagemController mensagemController;

    /**
     * Salva uma nova Mensagem
     * 
     * @param mensagem - DTO da Mensagem
     * @return Um Response com a Mensagem nova
     */
    @POST
    public Response saveMensagem(final MensagemDTO mensagem) {
        try {
            return Response.ok(mensagemController.saveMensagem(mensagem)).build();

        } catch (final Exception e) {
            return Response.status(400).entity(e).build();
        }
    }

    /**
     * Retorna todas as Mensagens que foram enviadas após a criação do Usuário
     * especificado
     * 
     * @param idUsuario - ID do Usuário
     * @return Um Response com a lista de Mensagens
     */
    @GET
    @Path("/after-user-creation/{idUsuario}")
    public Response findMensagensAfterUserCreation(@PathParam("idUsuario") final Integer idUsuario) {
        try {
            return Response.ok(mensagemController.findMensagensAfterUserCreation(idUsuario)).build();

        } catch (final Exception e) {
            return Response.status(400).entity(e).build();
        }
    }

}