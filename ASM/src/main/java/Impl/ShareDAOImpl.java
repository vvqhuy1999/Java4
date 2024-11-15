package Impl;

import Daos.ShareDAO;
import Entity.Share;

import Entity.Video;
import Utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ShareDAOImpl implements ShareDAO {
    EntityManager em = XJPA.getEntityManager();
    @Override
    public List<Share> findAll() {
        String jpql = "SELECT s FROM Share s";
        TypedQuery<Share> query = em.createQuery(jpql, Share.class);
        List<Share> list = query.getResultList();
        return list;
    }

    @Override
    public Share findByID(Long id) {
        return em.find(Share.class, id);
    }

    @Override
    public void create(Share share) {
        em.getTransaction().begin();
        try{
            em.persist(share);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Share entity) {
        em.getTransaction().begin();
        try {
            em.merge(entity);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        Share share = em.find(Share.class, id);
        em.getTransaction().begin();
        try{
            em.remove(share);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }
}
