package Daos;

import Entity.*;
import java.util.List;

public interface VideoDAO {
    Video findById(String id);
    List<Video> findAll();
    void save(Video video);
    void update(Video video);
    void delete(Video video);
    List<Video> findRelatedVideos(String videoId);
    List<Video> findByTitle(String title);
    
}
