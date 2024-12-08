package DAO;

import java.util.List;
import entity.user;

public interface UserDao {
    List<user> findAll();
    user findById(String userId); 
    user validateUser(String Email, String password);
    void create(user newUser);
    void update(user updatedUser);
    void deleteById(String userId);
    boolean emailExists(String email);
    List<user> findByPageAndRole(int pageNumber, int pageSize, String search, Boolean role);
    int countUsers(String search, Boolean role);
//    void findByEmail();
}