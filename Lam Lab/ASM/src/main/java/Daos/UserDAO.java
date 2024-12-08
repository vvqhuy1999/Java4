package Daos;

import java.util.List;

import Entity.*;
public interface UserDAO {
    User findById(String id);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(User user);  // Change this method to accept a User object
    void deleteById(String userId);
    User findByUsernameAndPassword(String fullname, String password);
    User findByUsername(String fullname);
    List<User> findByUsernameAndAdmin(String username, Boolean isAdmin);
    
    User findByIdOrEmail(String idOrEmail);
}

