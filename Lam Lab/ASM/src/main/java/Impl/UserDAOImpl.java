package Impl;

import Daos.*;
import Entity.*;
import Utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;

public class UserDAOImpl implements UserDAO {

	private EntityManager em = XJPA.getEntityManager();

	@Override
	public User findById(String id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		return em.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	@Override
	public void save(User user) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	@Override
	public void update(User user) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(user); // Cáº­p nháº­t ngÆ°á»�i dÃ¹ng
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	@Override
	public void delete(User user) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.remove(em.contains(user) ? user : em.merge(user));
		transaction.commit();
	}

	@Override
	public User findByUsernameAndPassword(String fullname, String password) {
		TypedQuery<User> query = em.createQuery(
				"SELECT u FROM User u WHERE u.fullname = :fullname AND u.password = :password", User.class);
		query.setParameter("fullname", fullname);
		query.setParameter("password", password);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public User findByUsername(String fullname) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.fullname = :fullname", User.class);
		query.setParameter("fullname", fullname);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public void deleteById(String userId) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			User user = em.find(User.class, userId);
			if (user != null) {
				em.remove(user);
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	

	@Override
	public List<User> findByUsernameAndAdmin(String username, Boolean isAdmin) {
		String jpql = "SELECT u FROM User u WHERE 1=1";
		if (username != null && !username.isEmpty()) {
			jpql += " AND LOWER(u.fullname) LIKE LOWER(:username)";
		}
		if (isAdmin != null) {
			jpql += " AND u.admin = :isAdmin";
		}
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		if (username != null && !username.isEmpty()) {
			query.setParameter("username", "%" + username.toLowerCase() + "%");
		}
		if (isAdmin != null) {
			query.setParameter("isAdmin", isAdmin);
		}
		return query.getResultList();
	}

	@Override
	public User findByIdOrEmail(String idOrEmail) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id = :idOrEmail OR u.email = :idOrEmail",
				User.class);
		query.setParameter("idOrEmail", idOrEmail);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	public List <User> showpage(int page) {
		// Số trang bắt đầu từ 1 nên cần trừ 1 khi tính offset
		int pageNumber = page - 1;  // Điều chỉnh pageNumber
		int pageSize = 5;

		// Tính offset (vị trí bắt đầu)
		int offset = pageNumber * pageSize;

		try {
			String jpql = "SELECT o FROM User o ";
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setFirstResult(offset);      // Sử dụng offset đã tính
			query.setMaxResults(pageSize);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();  // Trả về list rỗng nếu có lỗi
		}
	}

	public Long countUser(){
		String jpql = "SELECT count(o) FROM User o ";
		TypedQuery<Long> query = em.createQuery(jpql,Long.class);
		Long so = query.getSingleResult();
		return so;
	}

	public List<User> search(String fullname, boolean admin) {
		String jpql = "SELECT o FROM User o WHERE o.fullname LIKE :searchname AND o.admin = :searchadmin";
		TypedQuery<User> query = em.createQuery(jpql,User.class);
		query.setParameter("searchname","%" + fullname + "%");
		query.setParameter("searchadmin",admin);
		List<User> list = query.getResultList();
		return list;
	}
	
	public static void main(String[] args) {
		UserDAOImpl userDAO = new UserDAOImpl();
		String idOrEmail = "USR001"; 
		User user = userDAO.findByIdOrEmail(idOrEmail);

		if (user != null) {
			System.out.println("Tìm thấy người dùng: " + user);
		} else {
			System.out.println("Không tìm thấy người dùng với ID hoặc Email: " + idOrEmail);
		}
	}
}
