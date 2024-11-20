package Daos;

import Entity.Favorite;

import java.util.List;

public interface FavoriteDAO extends  GenericDAO<Favorite, Long>{
    List<Favorite> findAll();
    Favorite findByID(Long id);
    void create(Favorite favorite);
    void update(Favorite favorite);
    void deleteById(Long id);
}
