package Impl;

import Daos.FavoriteDAO;
import Entity.Favorite;
import Entity.Share;
import Entity.Video;
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

    //Truy vấn 10 video được yêu thích nhiều nhất
    public List<Object[]> findvideoLikeMost (){
        String jpql = "SELECT  o.video.id,o.video.title, count(o) FROM Favorite o GROUP BY o.video.id,o.video.title ORDER BY count(o) DESC";

        TypedQuery<Object[]> query = em.createQuery(jpql,Object[].class);
        query.setMaxResults(5);
        List<Object[]> soluongs = query.getResultList();
        soluongs.forEach(soluong -> {
            System.out.println("Video ID: " + soluong[0] + " Title:" + soluong[1] +
                    " | Số lượt thích: " + soluong[2] );
        });
        return soluongs;
    }

    public List<Object[]> FindTitleKeyCountSLLink(String key){
        String jpql = "SELECT  distinct o.video.title,count(o), o.video.active  FROM Favorite o WHERE o.video.title LIKE :pushKey GROUP BY o.video.title, o.video.active";
        TypedQuery<Object[]> query = em.createQuery(jpql,Object[].class);
        query.setParameter("pushKey","%" + key + "%");
        List<Object[]> items = query.getResultList();
        items.forEach(item -> {
            System.out.println("Title: " + item[0] + " Số lượt thích:" + item[1] +
                    " | Còn hiệu lực: " + item[2] );
        });
        return items;
    }

//        public static void main(String[] args) {
//        FavoriteDAOImpl f  = new FavoriteDAOImpl();
//
//        f.FindTitleKeyCountSLLink("Maroon");
//    }


}
