package myexam.service;

import myexam.model.entity.CategoryName;
import myexam.model.service.ProductServiceModel;
import myexam.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductViewModel> findAllProducts();

    BigDecimal findAllProductsPriceCount();

    void addItem(ProductServiceModel productServiceModel);

    ProductViewModel findById(String id);

    void delete(String id);


    List<ProductViewModel> getProducts(CategoryName category);

    void buyAll();


}
