package Impl;

import Daos.UserDAO;
import Entity.User;
import Utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDAOImpl implements UserDAO {
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
        return em.find(User.class, id);
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
    public void update(User entity) {
        User user = em.find(User.class, entity.getId());
        user.setFullname(entity.getFullname());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setAdmin(entity.getAdmin());
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

    public List<User> findUserLike(){
        String jpql = "SELECT u FROM User u WHERE u.favorites IS NOT EMPTY";
        TypedQuery<User> query = em.createQuery(jpql,User.class);
        List<User> users = query.getResultList();
//        users.forEach(user -> {
//            String id = user.getId();
//            String email = user.getEmail();
//            String fullname = user.getFullname();
//            String password = user.getPassword();
//            boolean admin = user.getAdmin();
//            System.out.println(fullname + ":" + admin + ":" + password);
//        });
        return users;
    }

    public static void main(String[] args) {
        UserDAOImpl u = new UserDAOImpl();
//        u.findUserLike();
        u.findAll();
    }
}
