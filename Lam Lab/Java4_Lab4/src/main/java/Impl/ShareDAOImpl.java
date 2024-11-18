package Impl;

import Daos.ShareDAO;
import Entity.Share;

import Entity.Video;
import Utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.text.SimpleDateFormat;
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

    //Tìm video được chia sẻ trong năm 2024 và sắp xếp theo thời gian
    public List<Share> findVideoShare2024(){
        String jpql = "SELECT distinct s FROM Share s WHERE year(s.sharedate) = :findyear order by s.sharedate desc";
        TypedQuery<Share> query = em.createQuery(jpql, Share.class);
        query.setParameter("findyear", 2024);
        List<Share> shares = query.getResultList();
        shares.forEach(share -> {
            System.out.println(share.getVideo().getTitle() + " |||  " + share.getSharedate());
        });
        return shares;
    }

    // Tiêu đề video, Số lượt chia sẻ, ngày chia sẻ đầu tiên, ngày chia sẻ
    //cuối cùng)
    public List<Object[]> findtTitleDate(){
        String jpql ="SELECT s.video.title ,COUNT(s.video.id),MIN(s.sharedate),MAX(s.sharedate) FROM Share s  GROUP BY s.video.title, s.video.id  ";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        List<Object[]> titledates = query.getResultList();
        titledates.forEach(titledate ->{
            System.out.println(titledate[0] + " || " + titledate[1] + " || " + titledate[2] + " || " + titledate[3] );
        });
        return titledates;
    }


//    public static void main(String[] args) {
//        ShareDAOImpl s = new ShareDAOImpl();
////        s.findVideoShare2024();
//        s.findtTitleDate();
//    }
}
