package Impl;

import Daos.*;
import Entity.*;
import Utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class VideoDAOImpl implements VideoDAO {
	private EntityManager em = XJPA.getEntityManager();

	@Override
	public Video findById(String id) {
		return em.find(Video.class, id);
	}

	@Override
	public List<Video> findAll() {
		return em.createQuery("SELECT v FROM Video v", Video.class).getResultList();
	}

	@Override
	public void save(Video video) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(video);
		transaction.commit();
	}

	@Override
	public void update(Video video) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(video);
		transaction.commit();
	}

	@Override
	public void delete(Video video) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.remove(em.contains(video) ? video : em.merge(video));
		transaction.commit();
	}

	public List<Video> findRelatedVideos(String videoId) {
		String jpql = "SELECT v FROM Video v WHERE v.id != :videoId ORDER BY RAND()";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setParameter("videoId", videoId);
		query.setMaxResults(20);
		return query.getResultList();
	}

	public Video findOneVideo (String videoId) {
		String jpql = "SELECT v FROM Video v WHERE  v.id = :id";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setParameter("id", videoId);
		return query.getSingleResult();
	}

	@Override
	public List<Video> findByTitle(String title) {
		String jpql = "SELECT v FROM Video v WHERE v.title LIKE :title";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setParameter("title", "%" + title + "%");
		return query.getResultList();
	}

	public int increaseViews (String videoId) {
		int views = 0;
		EntityTransaction transaction = em.getTransaction();
		try{
			transaction.begin();
			Video video = em.find(Video.class, videoId);

			if (video != null) {
				// Thay đổi các thuộc tính của đối tượng Video
				views = video.getViews() + 1;
				video.setViews(views);
				// Cập nhật đối tượng Video vào cơ sở dữ liệu
				em.merge(video);
			}
			transaction.commit();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return views;
	}


	public List <Video> showpage(int page) {
		// Số trang bắt đầu từ 1 nên cần trừ 1 khi tính offset
		int pageNumber = page - 1;  // Điều chỉnh pageNumber
		int pageSize = 5;

		// Tính offset (vị trí bắt đầu)
		int offset = pageNumber * pageSize;

		try {
			String jpql = "SELECT o FROM Video o ";
			TypedQuery<Video> query = em.createQuery(jpql, Video.class);
			query.setFirstResult(offset);      // Sử dụng offset đã tính
			query.setMaxResults(pageSize);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();  // Trả về list rỗng nếu có lỗi
		}
	}

	public Long countVideo(){
		String jpql = "SELECT count(o) FROM Video o ";
		TypedQuery<Long> query = em.createQuery(jpql,Long.class);
		Long so = query.getSingleResult();
		return so;
	}

	public List<String> getTitleVideos(){
		String jpql = "SELECT v.title FROM Video v ";
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		return query.getResultList();
	}

	public static void main(String[] args) {
		VideoDAOImpl videoDAO = new VideoDAOImpl();

//		List<Video> allVideos = videoDAO.findAll();
//		for (Video v : allVideos) {
//			System.out.println(v.getId() + "," + v.getTitle());
//		}

		String keyword = "Shape";
		List<Video> videos = videoDAO.findByTitle(keyword);
		if (videos.isEmpty()) {
			System.out.println("Không tìm thấy " + keyword);
		} else {
			for (Video video : videos) {
				System.out.println("Video ID: " + video.getId() + ", Title: " + video.getTitle());
			}
		}
	}
}
