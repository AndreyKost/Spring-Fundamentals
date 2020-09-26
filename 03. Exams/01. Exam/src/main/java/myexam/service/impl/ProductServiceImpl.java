package myexam.service.impl;

import myexam.model.entity.CategoryName;
import myexam.model.entity.Product;
import myexam.model.service.ProductServiceModel;
import myexam.model.view.ProductViewModel;
import myexam.repository.ProductRepository;
import myexam.service.CategoryService;
import myexam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }


    @Override
    public List<ProductViewModel> findAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(product -> {
                    ProductViewModel productViewModel=this.modelMapper.map(product,ProductViewModel.class);
                    return productViewModel;
                })
                .collect(Collectors.toList());

    }

    @Override
    public BigDecimal findAllProductsPriceCount() {
        return this.productRepository.findSumOfAllPrice();
    }

    @Override
    public void addItem(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);

        product.setCategory(this.categoryService
                .findByCategoryName(productServiceModel.getCategory().getCategoryName()));

        this.productRepository.saveAndFlush(product);
    }

    @Override
    public ProductViewModel findById(String id) {
        return productRepository.findById(id)
                .map(product -> {
                    ProductViewModel productViewModel=this.modelMapper.map(product,ProductViewModel.class);
                    productViewModel.setImgUrl(String.format("/img/%s.png",product.getCategory().getCategoryName().toString().toLowerCase()));
                    return productViewModel;
                }).orElse(null);
    }

    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public List<ProductViewModel> getProducts(CategoryName category) {
       return this.productRepository.findAll().stream().filter(e-> e.getCategory().getCategoryName().equals(category))
                .map(product -> modelMapper.map(product,ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buyAll() {
        this.productRepository.deleteAll();
    }


}
