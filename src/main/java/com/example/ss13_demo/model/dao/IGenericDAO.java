package com.example.ss13_demo.model.dao;

import java.util.List;

public interface IGenericDAO<T,ID> {
    List<T> getAll();
    Boolean create(T t);
    Boolean update(T t,ID id);
    T findById(ID id);
    void delete(ID id);
}
