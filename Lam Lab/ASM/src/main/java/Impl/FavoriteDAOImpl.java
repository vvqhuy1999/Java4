package Impl;

import Daos.*;
import Entity.*;
import Utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class FavoriteDAOImpl implements FavoriteDAO {

	private EntityManager em = XJPA.getEntityManager();

	@Override
	public void addFavorite(Favorite favorite) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(favorite);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void updateFavorite(Favorite favorite) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(favorite);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFavorite(Integer id) {
		Favorite favorite = em.find(Favorite.class, id);
		EntityTransaction transaction = em.getTransaction();
		try {
			if (favorite != null) {
				transaction.begin();
				em.remove(favorite);
				transaction.commit();
			}
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Favorite getFavoriteById(Integer id) {
		return em.find(Favorite.class, id);
	}

	@Override
	public List<Favorite> getAllFavorites() {
		String jpql = "SELECT f FROM Favorite f";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		return query.getResultList();
	}

	@Override
	public List<Favorite> getFavoritesByUserId(String userId) {
		String jpql = "SELECT f FROM Favorite f WHERE f.user.id = :userId";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public List<Favorite> getFavoritesByVideoId(String videoId) {
		String jpql = "SELECT f FROM Favorite f WHERE f.video.id = :videoId";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		query.setParameter("videoId", videoId);
		return query.getResultList();
	}

	@Override
	public List<Favorite> getFavoritesByVideoTitle(String videoTitle) {
		String jpql = "SELECT f FROM Favorite f WHERE LOWER(f.video.title) LIKE LOWER(:videoTitle)";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		query.setParameter("videoTitle", "%" + videoTitle.toLowerCase() + "%");
		return query.getResultList();
	}
	
	@Override
	public List<Video> getVideosWithNoFavorites() {
	    String jpql = "SELECT v FROM Video v WHERE v.id NOT IN (SELECT f.video.id FROM Favorite f)";
	    TypedQuery<Video> query = em.createQuery(jpql, Video.class);
	    return query.getResultList();
	}

	// Tiêu đề video, Số lượt thích, ngày thích đầu tiên, ngày thích
	//cuối cùng)
	public List<Object[]> getFavoritedVideos(){
		String jpql ="SELECT s.video.title ,COUNT(s.video.id),MIN(s.likeDate),MAX(s.likeDate) FROM Favorite s  GROUP BY s.video.title, s.video.id  ";
		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		List<Object[]> titledates = query.getResultList();
//        titledates.forEach(titledate ->{
//            System.out.println(titledate[0] + " || " + titledate[1] + " || " + titledate[2] + " || " + titledate[3] );
//        });
		return titledates;
	}


	@Override
	public List<Object[]> getTop10MostFavoritedVideos() {
		String jpql = "SELECT f.video.id, f.video.title, COUNT(f) AS favoriteCount " + "FROM Favorite f "
				+ "GROUP BY f.video.id, f.video.title " + "ORDER BY favoriteCount DESC";
		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		query.setMaxResults(10);
		return query.getResultList();
	}

	public boolean checkUserLikedVideo(String userId, String videoId) {
		try {
			String jpql = "SELECT COUNT(f) FROM Favorite f WHERE f.user.id = :userId AND f.video.id = :videoId";
			Long count = em.createQuery(jpql, Long.class)
					.setParameter("userId", userId)
					.setParameter("videoId", videoId)
					.getSingleResult();
			return count > 0;
		} catch (Exception e) {
			return false;
		}
	}
	public void removeFavorite(String userId, String videoId) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();

			String jpql = "DELETE FROM Favorite f WHERE f.user.id = :userId AND f.video.id = :videoId";
			int deletedCount = em.createQuery(jpql)
					.setParameter("userId", userId)
					.setParameter("videoId", videoId)
					.executeUpdate();

			transaction.commit();

			if (deletedCount == 0) {
				throw new Exception("No favorite found to remove");
			}

		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw new RuntimeException("Failed to remove favorite: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		FavoriteDAOImpl favoriteDAO = new FavoriteDAOImpl();
//		List<Object[]> top10Videos = favoriteDAO.getTop10MostFavoritedVideos();
//
//		if (top10Videos.isEmpty()) {
//			System.out.println("Không có video yêu thích nào.");
//		} else {
//			for (Object[] result : top10Videos) {
//				String videoId = (String) result[0];
//				String videoTitle = (String) result[1];
//				Long favoriteCount = (Long) result[2];
//
//				System.out.println(
//						"Video ID: " + videoId + ", Title: " + videoTitle + ", Số lượt yêu thích: " + favoriteCount);
//			}
//		}
		
		List<Video> videosNoFavorites = favoriteDAO.getVideosWithNoFavorites();

	    if (videosNoFavorites.isEmpty()) {
	        System.out.println("Không có video nào không được yêu thích.");
	    } else {
	        for (Video video : videosNoFavorites) {
	            System.out.println("Video không có ai yêu thích: " + video.getTitle());
	        }
	    }
	}

}