package myexam.model.service;


import myexam.model.entity.CategoryName;
import myexam.model.entity.Product;

import java.util.Set;

public class CategoryServiceModel extends BaseServiceModel{
    private CategoryName categoryName;
    private String description;
    private Set<ProductServiceModel> products;

    public CategoryServiceModel() {
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProductServiceModel> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductServiceModel> products) {
        this.products = products;
    }
}
