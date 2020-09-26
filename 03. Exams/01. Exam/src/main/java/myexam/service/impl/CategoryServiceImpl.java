package myexam.service.impl;

import myexam.model.entity.Category;
import myexam.model.entity.CategoryName;
import myexam.repository.CategoryRepository;
import myexam.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    private final ModelMapper modelMapper;


    @Override
    public void initCategories() {
        if(this.categoryRepository.count()==0){
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        this.categoryRepository.save(new Category(categoryName,String.format("Description for %s",categoryName.name())));
                    });
        }
    }

    @Override
    public Category findByCategoryName(CategoryName categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName)
                .orElse(null);
    }
}
