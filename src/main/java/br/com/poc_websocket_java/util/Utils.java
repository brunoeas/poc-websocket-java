package br.com.poc_websocket_java.util;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 * Classe com métodos auxiliares comuns
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@ApplicationScoped
public class Utils {

    /**
     * Entity Manager
     */
    @Inject
    EntityManager entityManager;

    /**
     * Valida e retorna um ORM
     * 
     * @param <T> - Entitade que está sendo manipulada
     * @param id - ID da entidade
     * @param entity - Classe da entidade
     * @return A instância da entidade pelo ID
     * @throws Exception - Lança uma Exception caso o ID não exista na base de
     * dados
     */
    public <T extends PanacheEntityBase> T validateAndReturnEntity(final Object id, final Class<T> entity)
            throws Exception {

        final T response = entityManager.find(entity, id);

        if (response == null) {
            throw new Exception("ID_INEXISTENTE");
        }

        return response;
    }

}