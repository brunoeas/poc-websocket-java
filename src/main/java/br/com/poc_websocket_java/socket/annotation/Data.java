package br.com.poc_websocket_java.socket.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marcador para o parâmetro "Data" do evento do Socket
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Data {
}