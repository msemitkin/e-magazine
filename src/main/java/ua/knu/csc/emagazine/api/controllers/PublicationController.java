package ua.knu.csc.emagazine.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.knu.csc.emagazine.api.dto.publication.CreatePublicationDTO;
import ua.knu.csc.emagazine.api.dto.publication.PublicationDTO;
import ua.knu.csc.emagazine.api.mapper.PublicationMapper;
import ua.knu.csc.emagazine.domain.magazine.Magazine;
import ua.knu.csc.emagazine.domain.publication.Publication;
import ua.knu.csc.emagazine.domain.magazine.MagazineService;
import ua.knu.csc.emagazine.domain.publication.PublicationService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PublicationController {
    private final MagazineService magazineService;
    private final PublicationService publicationService;
    private final PublicationMapper publicationMapper;

    public PublicationController(
        MagazineService magazineService,
        PublicationService publicationService,
        PublicationMapper publicationMapper
    ) {
        this.magazineService = magazineService;
        this.publicationService = publicationService;
        this.publicationMapper = publicationMapper;
    }

    @PostMapping("/api/magazines/{magazineId}/publications")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
        @PathVariable("magazineId") Integer magazineId,
        @Valid @RequestBody CreatePublicationDTO publicationDTO
    ) {
        Publication publication = publicationMapper.toEntity(publicationDTO);
        publication.setPublicationDateTime(LocalDateTime.now());
        Magazine magazine = magazineService.findById(magazineId);
        magazine.getPublications().add(publication);
        magazineService.update(magazine);
    }


    @GetMapping("/api/magazines/{magazineId}/publications")
    public List<PublicationDTO> getAllByMagazineId(@PathVariable("magazineId") Integer magazineId) {
        return magazineService.findById(magazineId).getPublications().stream()
            .map(publicationMapper::toDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/api/publications/{id}")
    public PublicationDTO getById(@PathVariable("id") Integer id) {
        Publication found = publicationService.findById(id);
        return publicationMapper.toDTO(found);
    }

    @GetMapping("/api/publications")
    public List<PublicationDTO> getAllByKeyWords(@RequestParam Set<Integer> keyWordsIds) {
        return publicationService.findByKeyWords(keyWordsIds).stream()
            .map(publicationMapper::toDTO)
            .collect(Collectors.toList());
    }

    @PutMapping("/api/publications/{id}")
    public PublicationDTO update(
        @PathVariable("id") Integer id,
        @Valid @RequestBody CreatePublicationDTO publicationDTO
    ) {
        Publication publication = publicationMapper.toEntity(publicationDTO);
        publication.setId(id);
        publication.setPublicationDateTime(LocalDateTime.now());
        Publication updated = publicationService.update(publication);
        return publicationMapper.toDTO(updated);
    }

    @DeleteMapping("/api/publications/{id}")
    public void delete(@PathVariable("id") Integer id) {
        publicationService.delete(id);
    }
}
