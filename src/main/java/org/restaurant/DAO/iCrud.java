package org.restaurant.DAO;

import java.sql.SQLException;
import java.util.List;

public interface iCrud <T>{

    void insert(T obj);
    List<T> getAll();
    T getById(Long id);
    void update(T obj, Long id);
    void deleteById(Long id);

}
