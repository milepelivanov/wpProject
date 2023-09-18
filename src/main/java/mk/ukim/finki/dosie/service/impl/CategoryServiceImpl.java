package mk.ukim.finki.dosie.service.impl;

import mk.ukim.finki.dosie.model.Category;
import mk.ukim.finki.dosie.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.dosie.model.exceptions.CourseNotFoundException;
import mk.ukim.finki.dosie.repository.CategoryRepository;
import mk.ukim.finki.dosie.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) throws CategoryNotFoundException {
        return this.categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category is not found"));
    }
}
