package DAO;

import entity.video;
import java.util.List;

public interface videoDao {
    List<video> findAll(); // Retrieve all videos

    List<video> findAlltrue();
    
    video findById(Integer videoId); // Find a video by its ID

    void create(video newVideo); // Create a new video

    void update(video updatedVideo); // Update an existing video

    void deleteById(Integer videoId); // Delete a video by its ID

    int countVideos(String search, Boolean active); // Count videos with optional search and active filters

    List<video> findByPage(int pageNumber, int pageSize); // Retrieve videos with pagination
    
    List<video> searchVideos(String searchTerm);
    
    void incrementViews(String videoId);
    
    List<video> findTop10Videos();

}