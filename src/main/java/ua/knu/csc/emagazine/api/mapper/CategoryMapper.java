package ua.knu.csc.emagazine.api.mapper;

import org.springframework.stereotype.Component;
import ua.knu.csc.emagazine.api.dto.category.CategoryDTO;
import ua.knu.csc.emagazine.api.dto.category.CreateCategoryDTO;
import ua.knu.csc.emagazine.domain.category.Category;

@Component
public class CategoryMapper {

    public Category toEntity(CreateCategoryDTO createCategoryDTO) {
        return Category.withValue(createCategoryDTO.getValue());
    }

    public CategoryDTO toDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getValue());
    }

    public Category toEntity(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getValue());
    }
}
