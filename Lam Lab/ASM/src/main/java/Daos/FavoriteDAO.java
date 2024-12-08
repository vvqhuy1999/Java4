package Daos;

import Entity.*;

import java.util.List;



public interface FavoriteDAO {
    void addFavorite(Favorite favorite);         
    void updateFavorite(Favorite favorite);        
    void deleteFavorite(Integer id);             
    Favorite getFavoriteById(Integer id);          // Lấy Favorite theo ID
    List<Favorite> getAllFavorites();              // Lấy danh sách tất cả Favorite
    List<Favorite> getFavoritesByUserId(String userId); // Lấy danh sách Favorite theo userId
    List<Favorite> getFavoritesByVideoId(String videoId); // Lấy danh sách Favorite theo videoId
    List<Favorite> getFavoritesByVideoTitle(String videoTitle);
    List<Object[]> getTop10MostFavoritedVideos();
    List<Video> getVideosWithNoFavorites();
}
