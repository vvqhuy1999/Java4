package DAO;

import java.sql.Connection;
import java.util.List;
import entity.user;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UserDaoImpl4 implements UserDao {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("webvideo");
    EntityManager em = factory.createEntityManager();


    @Override
    public user validateUser(String email, String password) {
        EntityManager em = factory.createEntityManager();
        try {
        	TypedQuery<user> query = em.createQuery("SELECT u FROM user u WHERE u.Email = :email AND u.Password = :password", user.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // Nếu không tìm thấy người dùng
        } finally {
            em.close();
        }
    }
    @Override
    public List<user> findAll() { // Thay đổi kiểu trả về
        EntityManager em = factory.createEntityManager();
        String jpql = "SELECT u FROM user u"; // Đổi tên thực thể thành User
        TypedQuery<user> query = em.createQuery(jpql, user.class);
        List<user> users = query.getResultList();
        em.close();
        return users; // Trả về danh sách người dùng
    }

    @Override
    public user  findById(String userId) {
    	 EntityManager em = factory.createEntityManager();
    	    user foundUser = null; 
    	    try {
    	        foundUser = em.find(user.class, userId); 
    	    } finally {
    	        em.close(); 
    	    }
    	    return foundUser;
    }

    @Override
    public void create(user newUser) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(user updatedUser) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(updatedUser);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    @Override
    public void deleteById(String userId) {
        EntityManager em = factory.createEntityManager();
        user user = em.find(user.class, userId);
        if (user != null) {
            try {
                em.getTransaction().begin();
                em.remove(user);
                em.getTransaction().commit();
                System.out.println("Đã xóa thành công");
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Không tìm thấy");
        }
        em.close();
    }

    @Override
    public boolean emailExists(String email) {
        EntityManager em = factory.createEntityManager();
        String hql = "SELECT COUNT(u.id) FROM user u WHERE u.email = :email"; // Ensure 'user' is the correct entity name
        try {
            TypedQuery<Long> query = em.createQuery(hql, Long.class);
            query.setParameter("email", email);
            Long count = query.getSingleResult();
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Consider logging the exception
        } finally {
            em.close(); // Ensure the EntityManager is closed
        }
    }
    @Override
    public List<user> findByPageAndRole(int pageNumber, int pageSize, String search, Boolean role) {
        EntityManager em = factory.createEntityManager();
        String jpql = "SELECT u FROM user u WHERE (:search IS NULL OR u.Email LIKE CONCAT('%', :search, '%')) AND (:role IS NULL OR u.Admin = :role)";
        TypedQuery<user> query = em.createQuery(jpql, user.class);

        if (search != null && !search.isEmpty()) {
            query.setParameter("search", search);
        } else {
            query.setParameter("search", null);
        }

        query.setParameter("role", role);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        
        List<user> users = query.getResultList();
        em.close();
        return users;
    }
    @Override
    public int countUsers(String search, Boolean role) {
        EntityManager em = factory.createEntityManager();
        String jpql = "SELECT COUNT(u) FROM user u WHERE (:search IS NULL OR u.Email LIKE :search) AND (:role IS NULL OR u.Admin = :role)";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        
        if (search != null && !search.isEmpty()) {
            query.setParameter("search", "%" + search + "%");
        } else {
            query.setParameter("search", null);
        }

        query.setParameter("role", role);
        Long count = query.getSingleResult();
        em.close();
        return count.intValue();
    }

    public List<user> findByPage(int pageNumber, int pageSize) {
        String jpql = "SELECT o FROM user o";
        TypedQuery<user> query = em.createQuery(jpql, user.class);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
}