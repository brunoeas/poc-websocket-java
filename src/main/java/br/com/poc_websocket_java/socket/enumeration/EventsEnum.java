package br.com.poc_websocket_java.socket.enumeration;

/**
 * Mapa de eventos que são recebidos e enviados pelo back-end
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
public enum EventsEnum {

    /**
     * 0 -Novo Usuário
     */
    NEW_USER("newUser"),

    /**
     * 1 - Usuário deslogou
     */
    USER_LOGGED_OFF("userLoggedOff"),

    /**
     * 2 - Nova Mensagem recebida
     */
    NEW_MESSAGE_RECEIVED("newMessageReceived");

    /**
     * Evento
     */
    private String event;

    /**
     * Construtor default
     * 
     * @param event - Evento
     */
    EventsEnum(final String event) {
        this.event = event;
    }

    public String getEvent() {
        return this.event;
    }

}