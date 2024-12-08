package DAO;

import java.util.List;

import entity.Favorite;
import entity.video;

public interface FavoriteDao {
    void addFavorite(int userId, int videoId);
    void removeFavorite(int userId, int videoId);
    List<video> getFavoritesByUserId(int userId);
    List<Favorite> getAllFavorites();
}

