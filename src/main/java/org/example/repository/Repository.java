package org.example.repository;

import java.util.List;

public interface Repository <T>{
    List<T>getList();
    T getById(Long id);
    void save(T t);
    void  deleteById(Long id);
    void update(T t);


}
