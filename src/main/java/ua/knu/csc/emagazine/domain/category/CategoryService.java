package ua.knu.csc.emagazine.domain.category;

import org.springframework.stereotype.Service;
import ua.knu.csc.emagazine.api.exception.EntityNotFoundException;
import ua.knu.csc.emagazine.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category) {
        if (categoryRepository.existsCategoryByValue(category.getValue())) {
            throw new CategoryAlreadyExistsException("Category already exists");
        }
        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Integer id) {
        Optional<Category> found = categoryRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new EntityNotFoundException("Category with given id doe not exist");
        }
    }

    public Category update(Category category) {
        if (categoryRepository.existsById(category.getId())) {
            return categoryRepository.save(category);
        } else {
            throw new EntityNotFoundException("Category with given id not found");
        }
    }
}
