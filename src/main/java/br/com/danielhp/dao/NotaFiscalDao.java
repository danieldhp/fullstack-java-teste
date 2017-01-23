package br.com.danielhp.dao;

import br.com.danielhp.entitybean.Cliente;
import br.com.danielhp.entitybean.NotaFiscal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Daniel on 20/01/2017.
 */
public class NotaFiscalDao {
    private static NotaFiscalDao instance;
    protected EntityManager entityManager;

    public static NotaFiscalDao getInstance() {
        if (instance == null) {
            instance = new NotaFiscalDao();
        }

        return instance;
    }

    private NotaFiscalDao() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("testeContabilizei");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public List<NotaFiscal> findAll() {
        return entityManager.createQuery("select t from NotaFiscal t").getResultList();
    }

    /**
     * Busca os registros de notasFiscais de acordo com os filtros
     *
     * @param mes
     * @param ano
     * @param cliente
     * @return
     */
    public List<NotaFiscal> list(Integer mes, Integer ano, Cliente cliente) {
        String condicao = "";

        if (mes != null) {
            condicao = "MONTH(data) = " + mes;
        }

        if (ano != null) {
            if (!condicao.isEmpty()) {
                condicao += " and ";
            }
            condicao += "YEAR(data) = " + ano;
        }

        if (cliente != null) {
            if (!condicao.isEmpty()) {
                condicao += " and ";
            }
            condicao += "cliente_id = " + cliente.getId();
        }

        String query = "select t from NotaFiscal t ";
        if (!condicao.isEmpty()) {
            query += "where " + condicao;
        }

        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Persiste o objeto no banco de dados
     *
     * @param notaFiscal
     */
    public void persist(NotaFiscal notaFiscal) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(notaFiscal);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * Atualiza o objeto no banco de dados
     *
     * @param notaFiscal
     */
    public void merge(NotaFiscal notaFiscal) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(notaFiscal);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
