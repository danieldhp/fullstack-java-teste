package br.com.danielhp.dao;

import br.com.danielhp.entitybean.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Daniel on 20/01/2017.
 */
public class ClienteDao {
    private static ClienteDao instance;
    protected EntityManager entityManager;

    public static ClienteDao getInstance() {
        if (instance == null) {
            instance = new ClienteDao();
        }

        return instance;
    }

    private ClienteDao() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("testeContabilizei");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    /**
     * Busca os registros de clientes de acordo com os filtros
     *
     * @param pesquisa
     * @return
     */
    public List<Cliente> list(String pesquisa) {
        String query = "select t from Cliente t ";
        if (pesquisa != null) {
            query += "where razao_social like '%" + pesquisa + "%' or cnpj like '%" + pesquisa + "%'";
        }
        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Persiste o objeto no banco de dados
     *
     * @param cliente
     */
    public void persist(Cliente cliente) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * Atualiza o objeto no banco de dados
     *
     * @param cliente
     */
    public void merge(Cliente cliente) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
