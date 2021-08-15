package edu.miu.cs545.project.onlinestore.controller;

import edu.miu.cs545.project.onlinestore.domain.Category;
import edu.miu.cs545.project.onlinestore.service.CategoryService;
import edu.miu.cs545.project.onlinestore.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAll(){
        List<Category> categories = categoryService.getAll();
        return categories.stream().map(category->modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @PostMapping()
    public void createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
    }

    @GetMapping("/category")
    public CategoryDTO getCategoryByName(@RequestParam("name") String name){
        Category category = categoryService.getCategoryByName(name);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable("id") Long id){

        Category category = categoryService.getCategoryById(id);
        return modelMapper.map(category, CategoryDTO.class);
    }
}
