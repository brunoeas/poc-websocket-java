package br.com.poc_websocket_java.configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

/**
 * Classe de configuração de inicialização
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@ApplicationScoped
public class LifecycleConfiguration {

    /**
     * Configuração do WebSocket Server
     */
    @Inject
    WebSocketConfiguration webSocketConfiguration;

    /**
     * Executa ao iniciar o back-end
     * 
     * @param ev
     */
    void init(@Observes final StartupEvent ev) {
        webSocketConfiguration.initWebSocketServer();
    }

    /**
     * Executa ao parar o back-end
     * 
     * @param ev
     */
    void stop(@Observes final ShutdownEvent ev) {
        webSocketConfiguration.stopSocketServer();
    }

}