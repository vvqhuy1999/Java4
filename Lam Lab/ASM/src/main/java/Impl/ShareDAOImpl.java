package Impl;

import Daos.*;
import Entity.*;
import Utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ShareDAOImpl implements ShareDAO {
	private EntityManager em = XJPA.getEntityManager();

	@Override
	public Share findById(Integer id) {
		return em.find(Share.class, id);
	}

	@Override
	public List<Share> findAll() {
		return em.createQuery("SELECT s FROM Share s", Share.class).getResultList();
	}

	@Override
	public void save(Share share) {
		em.getTransaction().begin();
		try{
			em.persist(share);
			em.getTransaction().commit();
		}catch (Exception e){
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(Share share) {
		em.getTransaction().begin();
		try {
			em.merge(share);
			em.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Share share) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.remove(em.contains(share) ? share : em.merge(share));
		transaction.commit();
	}

	public List<Share> getSharesByVideoTitle(String videoTitle) {
		String jpql = "SELECT s FROM Share s WHERE LOWER(s.video.title) LIKE LOWER(:videoTitle)";
		TypedQuery<Share> query = em.createQuery(jpql, Share.class);
		query.setParameter("videoTitle", "%" + videoTitle.toLowerCase() + "%");
		return query.getResultList();
	}
	@Override
	public List<Share> findSharesByVideo(Video video) {
		return em.createQuery("SELECT s FROM Share s WHERE s.video = :video", Share.class).setParameter("video", video)
				.getResultList();
	}

	@Override
	public List<Share> findSharesIn2024() {
		String jpql = "SELECT s FROM Share s WHERE YEAR(s.sharedate) = 2024 ORDER BY s.sharedate ASC";
		return em.createQuery(jpql, Share.class).getResultList();
	}

	public static void main(String[] args) {
		ShareDAOImpl shareDAO = new ShareDAOImpl();
		List<Share> sharesIn2024 = shareDAO.findSharesIn2024();

		if (sharesIn2024.isEmpty()) {
			System.out.println("Không có video nào được chia sẻ trong năm 2024.");
		} else {
			for (Share share : sharesIn2024) {
				System.out.println("Video ID: " + share.getVideo().getId() + ", Video Title: "
						+ share.getVideo().getTitle() + ", Share Date: " + share.getSharedate());
			}
		}
	}

}
