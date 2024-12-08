package DAO;

import java.util.ArrayList;
import java.util.List;
import entity.video; // Ensure you have the Video entity imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class videoDaolmpl implements videoDao {
    private EntityManagerFactory factory;

    public videoDaolmpl() {
        factory = Persistence.createEntityManagerFactory("webvideo");
    }
    @Override
    public List<video> searchVideos(String searchTerm) {
        EntityManager em = factory.createEntityManager();
        List<video> videos = null;
        try {
            String jpql = "SELECT v FROM video v WHERE v.title LIKE :searchTerm AND v.active = true";
            TypedQuery<video> query = em.createQuery(jpql, video.class);
            query.setParameter("searchTerm", "%" + searchTerm + "%");
            videos = query.getResultList();
        } finally {
            em.close();
        }
        return videos;
    }
    @Override
    public void incrementViews(String videoId) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            video videoToUpdate = em.find(video.class, videoId);
            if (videoToUpdate != null) {
                videoToUpdate.incrementViews();
                em.merge(videoToUpdate);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public List<video> findTop10Videos() {
        EntityManager em = factory.createEntityManager();
        List<video> topVideos = new ArrayList<>();
        try {
            String query = "SELECT v FROM video v ORDER BY v.views DESC";
            topVideos = em.createQuery(query, video.class)
                          .setMaxResults(10)
                          .getResultList();
        } finally {
            em.close();
        }
        return topVideos;
    }
    @Override
    public List<video> findAll() {
        EntityManager em = factory.createEntityManager();
        List<video> videos = null;
        try {
            String jpql = "SELECT v FROM video v";
            TypedQuery<video> query = em.createQuery(jpql, video.class);
            videos = query.getResultList();
        } finally {
            em.close();
        }
        return videos;
    }
    @Override
    public List<video> findAlltrue() {
        EntityManager em = factory.createEntityManager();
        List<video> videos = null;
        try {
            String jpql = "SELECT v FROM video v WHERE v.active = true";
            TypedQuery<video> query = em.createQuery(jpql, video.class);
            videos = query.getResultList();
        } finally {
            em.close();
        }
        return videos;
    }

    @Override
    public video findById(Integer videoId) {
        EntityManager em = factory.createEntityManager();
        video foundVideo = null;
        try {
            foundVideo = em.find(video.class, videoId);
        } finally {
            em.close();
        }
        return foundVideo;
    }

    @Override
    public void create(video newVideo) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newVideo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(video updatedVideo) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(updatedVideo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteById(Integer videoId) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            video videoToRemove = em.find(video.class, videoId);
            if (videoToRemove != null) {
                em.remove(videoToRemove);
                em.getTransaction().commit();
                System.out.println("Deleted successfully");
            } else {
                System.out.println("Video not found");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public int countVideos(String search, Boolean active) {
        EntityManager em = factory.createEntityManager();
        Long count = 0L;
        try {
            String jpql = "SELECT COUNT(v) FROM video v WHERE (:search IS NULL OR v.title LIKE :search) AND (:active IS NULL OR v.active = :active)";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            if (search != null && !search.isEmpty()) {
                query.setParameter("search", "%" + search + "%");
            } else {
                query.setParameter("search", null);
            }
            query.setParameter("active", active);
            count = query.getSingleResult();
        } finally {
            em.close();
        }
        return count.intValue();
    }

    @Override
    public List<video> findByPage(int pageNumber, int pageSize) {
        EntityManager em = factory.createEntityManager();
        List<video> videos = null;
        try {
            String jpql = "SELECT v FROM video v";
            TypedQuery<video> query = em.createQuery(jpql, video.class);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            videos = query.getResultList();
        } finally {
            em.close();
        }
        return videos;
    }
}