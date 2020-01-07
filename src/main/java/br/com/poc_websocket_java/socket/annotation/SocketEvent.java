package br.com.poc_websocket_java.socket.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marcador para métodos que representam receptores de eventos do Socket
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SocketEvent {

    /**
     * Nome do evento que vai ser chamado
     * 
     * @return O nome do evento
     */
    String event();

    /**
     * Tipo do dado que vai ser recebido
     * 
     * @return Classe do tipo de dado que vai ser recebido
     * @default Object.class
     */
    Class<?> typeData() default Object.class;

}