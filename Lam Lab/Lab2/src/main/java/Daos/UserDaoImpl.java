package Daos;

import Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import  Utils.XJPA;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO{
//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
    EntityManager em = XJPA.getEntityManager();

    @Override
    public List<User> findAll() {
        String jpql = "SELECT o FROM User o";
        TypedQuery<User> query = em.createQuery(jpql,User.class);
        List<User> list = query.getResultList();
        list.forEach(user -> {
            String id = user.getId();
            String email = user.getEmail();
            String fullname = user.getFullname();
            String password = user.getPassword();
            boolean admin = user.getAdmin();
            System.out.println(fullname + ":" + admin + ":" + password);
        });
        return list;
    }

    @Override
    public User findByID(String id) {
        try{
            User user = em.find(User.class, id);
            if (user != null) {
                String fullname = user.getFullname();
                boolean admin = user.getAdmin();
                System.out.println(fullname + ":" + admin);
                return user;
            } else {
                System.out.println("User with ID not found.");
            }
        }catch (Exception e){
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void create(User user) {
        em.getTransaction().begin();
        try{
            em.persist(user);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(User userform) {
        User user = em.find(User.class, userform.getId());
        user.setFullname(userform.getFullname());
        user.setEmail(userform.getEmail());
        user.setPassword(userform.getPassword());
        user.setAdmin(userform.getAdmin());
        em.getTransaction().begin();
        try {
            em.merge(user);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        User user = em.find(User.class, id);
        em.getTransaction().begin();
        try{
            em.remove(user);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }
//    public void findmail(){
//        String jpql = "SELECT o FROM User o WHERE o.email LIKE :search AND o.admin=:role";
//        TypedQuery<User> query = em.createQuery(jpql,User.class);
//        query.setParameter("search","%@gmail.com");
//        query.setParameter("role",false);
//        List<User> list = query.getResultList();
//        list.forEach(user -> {
//            String fullname = user.getFullname();
//            String password = user.getPassword();
//            String email = user.getEmail();
//            boolean admin = user.getAdmin();
//            System.out.println(fullname + ":" + admin + ":" + password + email);
//        });
//    }

//    public List<User> showpage(int page){
//        int pageNumber = page ;
//        int pageSize =5;
//
//        String jpql = "SELECT o FROM User o ";
//        TypedQuery<User> query = em.createQuery(jpql,User.class);
//        query.setFirstResult(pageNumber * pageSize);
//        query.setMaxResults(pageSize);
//        List<User> list = query.getResultList();
//        return list;
//    }
    public List<User> showpage(int page) {
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

//    public List<User> searchtest() {
//
//        String jpql = "SELECT o FROM User o WHERE o.fullname LIKE :searchname AND o.admin = :searchadmin";
//        TypedQuery<User> query = em.createQuery(jpql,User.class);
//        query.setParameter("searchname","%One%");
//        query.setParameter("searchadmin",true);
//        List<User> list = query.getResultList();
//        list.forEach(user -> {
//            String id = user.getId();
//            String email = user.getEmail();
//            String fullname = user.getFullname();
//            String password = user.getPassword();
//            boolean admin = user.getAdmin();
//            System.out.println(fullname + ":" + admin + ":" + password);
//        });
//        return list;
//    }

//    public static void main(String[] args) {
//        UserDaoImpl u = new UserDaoImpl();
//        u.searchtest();
//
//    }

}
