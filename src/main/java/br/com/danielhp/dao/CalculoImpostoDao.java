package br.com.danielhp.dao;

import br.com.danielhp.entitybean.CalculoImposto;
import br.com.danielhp.entitybean.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Daniel on 22/01/2017.
 */
public class CalculoImpostoDao {
    private static CalculoImpostoDao instance;
    protected EntityManager entityManager;

    public static CalculoImpostoDao getInstance() {
        if (instance == null) {
            instance = new CalculoImpostoDao();
        }

        return instance;
    }

    private CalculoImpostoDao() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("testeContabilizei");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public List<CalculoImposto> list(Integer mes, Integer ano, Cliente cliente) {
        String condicao = "";

        if (mes != null) {
            if (mes < 10) {
                condicao = "mes_ano_referente like '0" + mes + "/%' ";
            } else {
                condicao = "mes_ano_referente like '" + mes + "/%' ";
            }
        }

        if (ano != null) {
            if (!condicao.isEmpty()) {
                condicao += "and ";
            }
            condicao += "mes_ano_referente like '%/" + ano + "' ";
        }

        if (cliente != null) {
            if (!condicao.isEmpty()) {
                condicao += "and ";
            }
            condicao += "cliente_id = " + cliente.getId();
        }

        String query = "select t from CalculoImposto t ";
        if (!condicao.isEmpty()) {
            query += "where " + condicao;
        }

        return entityManager.createQuery(query).getResultList();
    }

    public void persist(CalculoImposto calculoImposto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(calculoImposto);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(CalculoImposto calculoImposto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(calculoImposto);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
