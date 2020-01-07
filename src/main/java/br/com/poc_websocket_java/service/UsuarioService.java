package br.com.poc_websocket_java.service;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.poc_websocket_java.controller.UsuarioController;
import br.com.poc_websocket_java.dto.UsuarioDTO;

/**
 * Service do Usuário
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioService {

    /**
     * Controller do Usuário
     */
    @Inject
    UsuarioController usuarioController;

    /**
     * Faz o login com um novo Usuário
     * 
     * @param usuario - DTO do Usuário
     * @return Um Response com o novo Usuário
     */
    @Path("login")
    @POST
    public Response logIn(final UsuarioDTO usuario) {
        try {
            return Response.ok(usuarioController.saveUsuario(usuario)).build();

        } catch (final Exception e) {
            return Response.status(400).entity(e).build();
        }
    }

    /**
     * Faz logoff do Usuário e exclui ele
     * 
     * @param id - ID do Usuário
     * @return Um Response
     */
    @Path("logoff/{id}")
    @DELETE
    public Response logOff(@PathParam("id") final Integer id) {
        try {
            usuarioController.deleteUsuarioById(id);
            return Response.ok().build();

        } catch (final Exception e) {
            return Response.status(400).entity(e).build();
        }
    }

    /**
     * Retorna um Usuário pelo ID
     * 
     * @param id - ID do Usuário
     * @return Um Response com o Usuário
     */
    @Path("usuario/{id}")
    @GET
    public Response findById(@PathParam("id") final Integer id) {
        try {
            return Response.ok(usuarioController.findById(id)).build();

        } catch (final Exception e) {
            return Response.status(400).entity(e).build();
        }
    }

}