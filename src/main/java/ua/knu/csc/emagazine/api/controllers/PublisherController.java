package ua.knu.csc.emagazine.api.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.knu.csc.emagazine.api.dto.publisher.CreatePublisherDTO;
import ua.knu.csc.emagazine.api.dto.publisher.PublisherDTO;
import ua.knu.csc.emagazine.api.mapper.PublisherMapper;
import ua.knu.csc.emagazine.domain.publisher.Publisher;
import ua.knu.csc.emagazine.domain.publisher.PublisherService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PublisherController {
    private final PublisherService publisherService;
    private final PublisherMapper publisherMapper;

    public PublisherController(
        PublisherService publisherService,
        PublisherMapper publisherMapper
    ) {
        this.publisherService = publisherService;
        this.publisherMapper = publisherMapper;
    }

    @PostMapping("/api/publishers")
    public PublisherDTO createPublisher(@Valid @RequestBody CreatePublisherDTO createPublisherDTO) {
        Publisher saved = publisherService.save(publisherMapper.toEntity(createPublisherDTO));
        return publisherMapper.toDTO(saved);
    }

    @GetMapping("/api/publishers")
    public List<PublisherDTO> getPublishers() {
        return publisherService.findAll().stream()
            .map(publisherMapper::toDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/api/publishers/{id}")
    public PublisherDTO getPublisher(@PathVariable("id") Integer publisherId) {
        Publisher found = publisherService.findById(publisherId);
        return publisherMapper.toDTO(found);
    }

    @PutMapping("/api/publishers/{id}")
    public PublisherDTO updatePublisher(
        @PathVariable("id") Integer publisherId,
        @Valid @RequestBody CreatePublisherDTO updatePublisherDTO
    ) {
        PublisherDTO publisherDTO = new PublisherDTO(publisherId, updatePublisherDTO.getName());
        Publisher updated = publisherService.update(publisherMapper.toEntity(publisherDTO));
        return publisherMapper.toDTO(updated);
    }

    @DeleteMapping("/api/publishers/{id}")
    public void delete(@PathVariable("id") int id) {
        publisherService.delete(id);
    }

}
