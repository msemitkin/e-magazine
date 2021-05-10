package ua.knu.csc.emagazine.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ua.knu.csc.emagazine.domain.category.Category;

import java.util.Optional;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void savesCategoryCorrect() {
        Category category = new Category(null, "someCategory");
        Category saved = categoryRepository.save(category);
        Optional<Category> found = categoryRepository.findById(saved.getId());
        Assertions.assertAll(() -> {
            Assertions.assertTrue(found.isPresent());
            Assertions.assertEquals(saved.getValue(), found.get().getValue());
        });
    }
}