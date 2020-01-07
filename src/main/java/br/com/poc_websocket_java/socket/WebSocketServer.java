package br.com.poc_websocket_java.socket;

import java.util.UUID;

import com.corundumstudio.socketio.SocketIOServer;

import br.com.poc_websocket_java.configuration.WebSocketConfiguration;
import br.com.poc_websocket_java.socket.enumeration.EventsEnum;

/**
 * Wrapper para funções do Socket
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
public abstract class WebSocketServer {

    /**
     * Emite um evento para todos os Clients conectados
     * 
     * @param event - Evento
     * @param data - Dados que vão ser enviados
     */
    public static void emitToAllClients(final EventsEnum event, final Object data) {
        if (WebSocketConfiguration.Server == null) {
            return;
        }

        final SocketIOServer server = WebSocketConfiguration.Server;

        server.getAllClients().forEach(client -> client.sendEvent(event.getEvent(), data));
    }

    /**
     * Emite um evento para um Client específico
     * 
     * @param uuidClient - UUID do Client
     * @param event - Evento
     * @param data - Dados que vão ser enviados
     */
    public static void emitToOneClient(final String uuidClient, final EventsEnum event, final Object data) {
        if (WebSocketConfiguration.Server == null) {
            return;
        }

        final SocketIOServer server = WebSocketConfiguration.Server;

        server.getClient(UUID.fromString(uuidClient)).sendEvent(event.getEvent(), data);
    }

    /**
     * @see emitToOneClient(String, EventsEnum, Object)
     * @param uuidClient - UUID do Client
     * @param event - Evento
     * @param data - Dados que vão ser enviados
     */
    public static void emitToOneClient(final UUID uuidClient, final EventsEnum event, final Object data) {
        if (WebSocketConfiguration.Server == null) {
            return;
        }

        final SocketIOServer server = WebSocketConfiguration.Server;

        server.getClient(uuidClient).sendEvent(event.getEvent(), data);
    }

}