package Impl;

import Daos.VideoDAO;

import Entity.User;
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
        em.getTransaction().begin();
        try {
            em.merge(entity);
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

    //Tìm các video có title chứa từ khóa <<keyword>>
    public List<Video> findkeyTitle (String title){
        String jpql = "SELECT v FROM Video v WHERE v.title LIKE :findTitle";
        TypedQuery<Video> query = em.createQuery(jpql,Video.class);
        // Thiết lập giá trị cho các tham số
        query.setParameter("findTitle", "%" + title + "%");
        List<Video> videos = query.getResultList();
        videos.forEach(video -> {
            System.out.println(video.getTitle());
        });
        return videos;
    }

    //Tìm các video không được ai thích
    public List<Video> findVideoNotLike(){
        String jpql = "SELECT count(v) FROM Video v WHERE v.favorites IS EMPTY";
        TypedQuery<Video> query = em.createQuery(jpql,Video.class);
        List<Video> videos = query.getResultList();
        videos.forEach(video -> {
            System.out.println(video.getId() + " "+ video.getTitle());
        });
        return videos;
    }



//    public static void main(String[] args) {
//        VideoDAOImpl v  = new VideoDAOImpl();
////        v.findkeyTitle("Maroon ");
//        v.findVideoNotLike();
//    }

}
