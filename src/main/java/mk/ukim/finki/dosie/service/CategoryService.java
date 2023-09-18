package mk.ukim.finki.dosie.service;

import mk.ukim.finki.dosie.model.Category;
import mk.ukim.finki.dosie.model.exceptions.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<Category> findAllCategories();

    Category findById(Long id) throws CategoryNotFoundException;
}
