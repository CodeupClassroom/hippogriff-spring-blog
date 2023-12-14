package com.codeup.hippogriffspringblog.dao;

import com.codeup.hippogriffspringblog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Long> {

    Category findCategoryByName(String name);
}
