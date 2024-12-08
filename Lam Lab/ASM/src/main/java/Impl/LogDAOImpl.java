package Impl;

import Daos.*;
import Entity.*;
import Utils.XJPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class LogDAOImpl implements LogDAO {
    private EntityManagerFactory emf;

    public LogDAOImpl() {
        emf = Persistence.createEntityManagerFactory("ASM"); // Thay bằng persistence unit name của bạn
    }
@Override
    public void create(Log log) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            System.out.println("Persisting log: " + log);
            em.persist(log);
            em.getTransaction().commit();
            System.out.println("Log persisted successfully!");
        } catch (Exception e) {
        	 System.err.println("Error during log creation: " + e.getMessage());
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
