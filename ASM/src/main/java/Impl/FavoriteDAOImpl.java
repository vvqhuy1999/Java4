package Impl;

import Daos.FavoriteDAO;
import Entity.Favorite;
import Entity.Share;
import Utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class FavoriteDAOImpl implements FavoriteDAO {
    EntityManager em = XJPA.getEntityManager();
    @Override
    public List<Favorite> findAll() {
        String jpql = "SELECT f FROM Favorite f";
        TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
        List<Favorite> list = query.getResultList();
        return list;
    }

    @Override
    public Favorite findByID(Long id) {
        return em.find(Favorite.class, id);
    }

    @Override
    public void create(Favorite favorite) {
        em.getTransaction().begin();
        try{
            em.persist(favorite);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Favorite favorite) {
        em.getTransaction().begin();
        try {
            em.merge(favorite);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        Favorite favorite = em.find(Favorite.class, id);
        em.getTransaction().begin();
        try{
            em.remove(favorite);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }


}
