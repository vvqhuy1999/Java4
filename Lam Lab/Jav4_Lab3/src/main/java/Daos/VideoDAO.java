package Daos;

import Entity.Video;

import java.util.List;

public interface VideoDAO extends  GenericDAO<Video, String>{
    List<Video> findAll();
    Video findByID(String id);
    void create(Video video);
    void update(Video video);
    void deleteById(String id);
}
