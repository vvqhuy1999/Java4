package Impl;

import Daos.LogDAO;
import Entity.Log;
import Utils.XJPA;
import jakarta.persistence.EntityManager;

public class LogDAOImpl implements LogDAO {
    EntityManager em = XJPA.getEntityManager();
    @Override
    public void create(Log log) {
        em.getTransaction().begin();
        try{
            em.persist(log);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

    public void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
