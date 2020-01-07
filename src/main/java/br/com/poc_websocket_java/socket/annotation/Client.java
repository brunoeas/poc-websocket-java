package br.com.poc_websocket_java.socket.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marcador para o parâmetro "Client" do evento do Socket, o tipo do parâmetro
 * deve ser com.corundumstudio.socketio.SocketIOClient
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Client {
}