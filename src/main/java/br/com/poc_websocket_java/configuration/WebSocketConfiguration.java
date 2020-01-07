package br.com.poc_websocket_java.configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.reflections.Reflections;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;

import br.com.poc_websocket_java.socket.annotation.AckRequestParam;
import br.com.poc_websocket_java.socket.annotation.Client;
import br.com.poc_websocket_java.socket.annotation.Data;
import br.com.poc_websocket_java.socket.annotation.SocketEvent;
import br.com.poc_websocket_java.socket.annotation.SocketService;

/**
 * Server WebSocket do Chat
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@ApplicationScoped
public class WebSocketConfiguration {

    /**
     * Logger
     */
    private final static Logger LOGGER = Logger.getLogger(WebSocketConfiguration.class.getName());

    /**
     * PORT que está no application.properties
     */
    @ConfigProperty(name = "websocket.port")
    private Integer port;

    /**
     * Socket Server
     */
    public static SocketIOServer Server;

    /**
     * Inicializa o Server WebSocket
     */
    void initWebSocketServer() {

        Server = this.createServer();

        try {
            Server.start();

            this.registerEvents();

        } catch (final Exception e) {
            LOGGER.log(Level.ERROR, "Erro ao iniciar WebSocket Server, o server será finalizado", e);
            this.stopSocketServer();
        }
    }

    /**
     * Para o Server WebSocket
     */
    @PreDestroy
    void stopSocketServer() {
        if (Server != null) {
            Server.stop();
            Server = null;
        }
    }

    /**
     * Registra os eventos de todos os métodos anotados
     * com @br.com.poc_websocket_java.socket.annotation.SocketEvent
     */
    private void registerEvents() {

        final Reflections ref = new Reflections("br.com.poc_websocket_java");

        for (final Class<?> classe : ref.getTypesAnnotatedWith(SocketService.class)) {
            for (final Method metodo : classe.getMethods()) {
                if (!metodo.isAnnotationPresent(SocketEvent.class)) {
                    continue;
                }
                metodo.setAccessible(true);

                final SocketEvent socketEvent = metodo.getAnnotation(SocketEvent.class);

                final String eventName = socketEvent.event();

                Server.addEventListener(eventName, socketEvent.typeData(),
                        (client, data, ackRequest) -> this.invokeEventMethod(client, data, ackRequest, metodo, classe));
            }
        }
    }

    /**
     * Executa o método que representa o evento passando somente os parâmetros
     * específicos anotados com @Data, @Client ou @AckRequestParam do pacote
     * br.com.poc_websocket_java.socket.annotation
     * 
     * @param client - Socket Client
     * @param data - Socket Data
     * @param ackRequest - Socket AckRequest
     * @param metodo - Método que representa o evento
     * @param classe - Classe do método que representa o evento
     * @throws Exception - Exception genérica
     */
    private void invokeEventMethod(final SocketIOClient client, final Object data, final AckRequest ackRequest,
            final Method metodo, final Class<?> classe) throws Exception {

        final List<Object> parametersToEventMethod = new ArrayList<>();

        for (final Parameter parametro : metodo.getParameters()) {

            if (parametro.isAnnotationPresent(Data.class)) {
                parametersToEventMethod.add(data);
                continue;
            }

            if (parametro.isAnnotationPresent(Client.class) && parametro.getType().equals(SocketIOClient.class)) {
                parametersToEventMethod.add(client);
                continue;
            }

            if (parametro.isAnnotationPresent(AckRequestParam.class) && parametro.getType().equals(AckRequest.class)) {
                parametersToEventMethod.add(ackRequest);
                continue;
            }

            parametersToEventMethod.add(null);
        }

        metodo.invoke(this.injectDependenciesAndReturnNewInstance(classe), parametersToEventMethod.toArray());
    }

    /**
     * Injeta as dependências da classe e de todas suas dependências e retorna
     * uma nova instância da classe
     * 
     * @param classe - Classe que vai ser manipulada
     * @return Uma nova instância da classe passada por parâmetro
     * @throws Exception - Exception genérica
     */
    private Object injectDependenciesAndReturnNewInstance(final Class<?> classe) throws Exception {

        final Object classToReturn = classe.newInstance();

        for (final Field field : classe.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Inject.class)) {
                continue;
            }
            field.setAccessible(true);

            field.set(classToReturn, this.injectDependenciesAndReturnNewInstance(field.getType()));
        }

        return classToReturn;
    }

    /**
     * Cria e retorna o Servidor WebSocket
     * 
     * @return O Servidor WebSocket
     */
    private SocketIOServer createServer() {

        final Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(this.getPort());

        final SocketIOServer server = new SocketIOServer(config);

        return server;
    }

    /**
     * Retorna a porta em que o back-end está rodando
     * 
     * @return A porta que está especificada no application.properties ou 2001
     * caso não tenha especificado
     */
    private Integer getPort() {
        return this.port != null ? this.port : 2001;
    }

}