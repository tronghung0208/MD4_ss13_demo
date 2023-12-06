package com.example.ss13_demo.model.service;
import java.util.List;

public interface IGennericService<T,ID> {

     List<T> getAll();
    Boolean create(T t);
    Boolean update(T t,ID id);
    T findById(ID id);
    void delete(ID id);

}
