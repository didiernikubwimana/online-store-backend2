package edu.miu.cs545.project.onlinestore.service;
import java.util.List;
import edu.miu.cs545.project.onlinestore.domain.Category;
import edu.miu.cs545.project.onlinestore.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }
}
