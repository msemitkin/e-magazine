package ua.knu.csc.emagazine.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.knu.csc.emagazine.api.dto.CategoryDTO;
import ua.knu.csc.emagazine.api.dto.CreateCategoryDTO;
import ua.knu.csc.emagazine.api.dto.UpdateCategoryDTO;
import ua.knu.csc.emagazine.api.mapper.CategoryMapper;
import ua.knu.csc.emagazine.domain.Category;
import ua.knu.csc.emagazine.domain.service.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(
        CategoryService categoryService,
        CategoryMapper categoryMapper
    ) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping("/api/categories/")
    public CategoryDTO addCategory(@Valid CreateCategoryDTO createCategoryDTO) {
        Category category = categoryMapper.toEntity(createCategoryDTO);
        Category saved = categoryService.save(category);
        return categoryMapper.toDTO(saved);
    }

    @GetMapping("/api/categories/")
    public List<CategoryDTO> getCategories() {
        return categoryService.findAll().stream()
            .map(categoryMapper::toDTO)
            .collect(Collectors.toList());
    }

    @PutMapping("/api/categories/{id}")
    public CategoryDTO update(
        @PathVariable("id") Integer categoryId,
        @Valid CreateCategoryDTO createCategoryDTO
    ) {
        UpdateCategoryDTO categoryDTO =
            new UpdateCategoryDTO(categoryId, createCategoryDTO.getValue());
        Category category = categoryMapper.toEntity(categoryDTO);
        Category updated = categoryService.update(category);
        return categoryMapper.toDTO(updated);
    }
}
