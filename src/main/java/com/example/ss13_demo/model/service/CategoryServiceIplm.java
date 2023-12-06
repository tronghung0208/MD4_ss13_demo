package com.example.ss13_demo.model.service;

import com.example.ss13_demo.model.dao.CategoryDAO;
import com.example.ss13_demo.model.dao.CategoryDAOIplm;
import com.example.ss13_demo.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceIplm implements CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    @Override
    public Boolean create(Category category) {
        return categoryDAO.create(category);
    }

    @Override
    public Boolean update(Category category, Integer id) {
        return categoryDAO.update(category,id);
    }

    @Override
    public Category findById(Integer id) {
        return categoryDAO.findById(id);
    }

    @Override
    public void delete(Integer integer) {

    }
}
