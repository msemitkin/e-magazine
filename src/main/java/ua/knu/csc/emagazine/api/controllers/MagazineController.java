package ua.knu.csc.emagazine.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.knu.csc.emagazine.api.dto.magazine.CreateMagazineDTO;
import ua.knu.csc.emagazine.api.dto.magazine.MagazineDTO;
import ua.knu.csc.emagazine.api.mapper.MagazineMapper;
import ua.knu.csc.emagazine.domain.category.Category;
import ua.knu.csc.emagazine.domain.category.CategoryService;
import ua.knu.csc.emagazine.domain.magazine.Magazine;
import ua.knu.csc.emagazine.domain.magazine.MagazineService;
import ua.knu.csc.emagazine.domain.publisher.Publisher;
import ua.knu.csc.emagazine.domain.publisher.PublisherService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MagazineController {

    private final PublisherService publisherService;
    private final MagazineService magazineService;
    private final CategoryService categoryService;
    private final MagazineMapper magazineMapper;

    public MagazineController(
        PublisherService publisherService,
        MagazineService magazineService,
        CategoryService categoryService,
        MagazineMapper magazineMapper
    ) {
        this.publisherService = publisherService;
        this.magazineService = magazineService;
        this.categoryService = categoryService;
        this.magazineMapper = magazineMapper;
    }

    @PostMapping("/api/publishers/{id}/magazines/")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
        @PathVariable("id") Integer publisherId,
        @Valid CreateMagazineDTO createMagazineDTO
    ) {
        Magazine magazine = magazineMapper.toEntity(createMagazineDTO);
        Category category = categoryService.findById(magazine.getCategory().getId());
        magazine.setCategory(category);
        Publisher publisher = publisherService.findById(publisherId);
        publisher.getMagazines().add(magazine);
        publisherService.update(publisher);
    }

    @GetMapping("/api/publishers/{id}/magazines/")
    public List<MagazineDTO> getByPublisherId(@PathVariable("id") Integer publisherId) {
        return publisherService.findById(publisherId).getMagazines().stream()
            .map(magazineMapper::toDTO)
            .collect(Collectors.toList());
    }


    @GetMapping("/api/magazines/{id}")
    public MagazineDTO getById(@PathVariable("id") Integer magazineId) {
        Magazine found = magazineService.findById(magazineId);
        return magazineMapper.toDTO(found);
    }

    @PutMapping("/api/magazines/{id}")
    public MagazineDTO update(
        @PathVariable("id") Integer magazineId,
        @Valid CreateMagazineDTO createMagazineDTO
    ) {
        Magazine magazine = magazineMapper.toEntity(createMagazineDTO);
        magazine.setId(magazineId);
        Magazine updated = magazineService.update(magazine);
        return magazineMapper.toDTO(updated);
    }

    @DeleteMapping("/api/magazines/{id}")
    public void deleteById(@PathVariable("id") Integer magazineId) {
        magazineService.deleteById(magazineId);
    }
}
