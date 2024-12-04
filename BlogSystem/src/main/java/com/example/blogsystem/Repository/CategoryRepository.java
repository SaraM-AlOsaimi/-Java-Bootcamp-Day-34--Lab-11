package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findCategoriesByCategoryId(Integer categoryId);

    Category findCategoriesByName(String name);

}
