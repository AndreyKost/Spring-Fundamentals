package myexam.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category  extends BaseEntity{
    private CategoryName categoryName;
    private String description;
    private Set<Product> products;

    public Category() {
    }

    public Category(CategoryName categoryName,String description) {
        this.categoryName=categoryName;
        this.description=description;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    @Enumerated(EnumType.ORDINAL)
    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    @Column(name="description",columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "category")
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
