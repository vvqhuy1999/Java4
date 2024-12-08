package DAO;

import java.util.ArrayList;
import java.util.List;

import entity.Favorite;
import entity.video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class FavoriteDaolmpl implements FavoriteDao {
    private EntityManagerFactory factory;

    public FavoriteDaolmpl() {
        factory = Persistence.createEntityManagerFactory("webvideo");
    }

    @Override
    public void addFavorite(int userId, int videoId) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            String sql = "INSERT INTO Favorite (UserId, VideoId, LikeDate) VALUES (:userId, :videoId, GETDATE())";
            em.createNativeQuery(sql)
              .setParameter("userId", userId)
              .setParameter("videoId", videoId)
              .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void removeFavorite(int userId, int videoId) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            String sql = "DELETE FROM Favorite WHERE UserId = :userId AND VideoId = :videoId";
            em.createNativeQuery(sql)
              .setParameter("userId", userId)
              .setParameter("videoId", videoId)
              .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    @Override
    public List<Favorite> getAllFavorites() {
        EntityManager em = factory.createEntityManager();
        List<Favorite> favorites = new ArrayList<>();
        try {
            String jpql = "SELECT f FROM Favorite f JOIN f.user u JOIN f.video v";
            TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
            favorites = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return favorites;
    }
    @Override
    public List<video> getFavoritesByUserId(int userId) {
        EntityManager em = factory.createEntityManager();
        List<video> favorites = new ArrayList<>();
        try {
            // Sửa đổi truy vấn để lấy video từ Favorite
            String jpql = "SELECT f.video FROM Favorite f WHERE f.user.id = :userId";
            TypedQuery<video> query = em.createQuery(jpql, video.class);
            query.setParameter("userId", userId);
            favorites = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return favorites;
    }
}