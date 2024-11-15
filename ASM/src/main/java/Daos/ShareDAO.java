package Daos;

import Entity.Share;

import java.util.List;

public interface ShareDAO extends  GenericDAO<Share, Long>{
    List<Share> findAll();
    Share findByID(Long id);
    void create(Share share);
    void update(Share share);
    void deleteById(Long id);
}
