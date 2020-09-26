package myexam.service;

import myexam.model.entity.Category;
import myexam.model.entity.CategoryName;

public interface CategoryService {

    void initCategories();

    Category findByCategoryName(CategoryName categoryName);
}
