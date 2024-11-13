package Impl;

import Daos.VideoDAO;

import Entity.Video;
import Utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VideoDAOImpl implements VideoDAO {
    EntityManager em = XJPA.getEntityManager();
    @Override
    public List<Video> findAll() {
        String jpql = "SELECT o FROM Video o";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        List<Video> list = query.getResultList();
        return list;
    }

    @Override
    public Video findByID(String id) {
        return em.find(Video.class, id);
    }

    @Override
    public void create(Video video) {
        em.getTransaction().begin();
        try{
            em.persist(video);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Video entity) {
        Video video = em.find(Video.class, entity.getId());
        video.setTitle(entity.getTitle());
        video.setPoster(entity.getPoster());
        video.setViews(entity.getViews());
        video.setDescription(entity.getDescription());
        video.setActive(entity.getActive());
        em.getTransaction().begin();
        try {
            em.merge(video);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        Video video = em.find(Video.class, id);
        em.getTransaction().begin();
        try{
            em.remove(video);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }
}
