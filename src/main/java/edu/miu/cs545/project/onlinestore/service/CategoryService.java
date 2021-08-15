package edu.miu.cs545.project.onlinestore.service;

import edu.miu.cs545.project.onlinestore.domain.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryByName(String name);
    List<Category> getAll();
    Category getCategoryById(Long id);
    void createCategory(Category category);
}
