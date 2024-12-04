package com.example.blogsystem.Service;

import com.example.blogsystem.API.ApiException;
import com.example.blogsystem.Model.Category;
import com.example.blogsystem.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(Integer id , Category category){
        Category oldCategory = categoryRepository.findCategoriesByCategoryId(id);
        if(oldCategory == null){
            throw new ApiException("Category not found");
        }
        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
    }

    public void deleteCategory(Integer id){
        Category category = categoryRepository.findCategoriesByCategoryId(id);
        if(category == null){
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(category);
    }

    public Category findCategoryByName(String name){
        Category category = categoryRepository.findCategoriesByName(name);
        if(category == null){
            throw new ApiException("Category not found");
        }
        return category;
    }


}
