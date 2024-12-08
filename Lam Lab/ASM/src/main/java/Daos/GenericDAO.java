package Daos;

import java.util.List;

public interface GenericDAO<T,ID> {
    List<T> findAll();
    T findByID(ID id);
    void create(T item);

    void update(T item);

    void deleteById(ID id);
}
