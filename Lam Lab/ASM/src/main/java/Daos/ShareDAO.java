package Daos;

import Entity.*;

import java.util.List;

public interface ShareDAO {
	Share findById(Integer id);

	List<Share> findAll();

	void save(Share share);

	void update(Share share);

	void delete(Share share);

	List<Share> findSharesByVideo(Video video);

	List<Share> findSharesIn2024();

}
