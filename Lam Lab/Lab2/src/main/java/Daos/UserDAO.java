package Daos;

import Entity.User;

import java.util.List;

public interface UserDAO extends  GenericDAO<User, String>{
    List<User> findAll();
    User findByID(String id);
    void create(User user);
    void update(User user);
    void deleteById(String id);

}
