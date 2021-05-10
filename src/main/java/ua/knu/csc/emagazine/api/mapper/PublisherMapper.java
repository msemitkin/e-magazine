package ua.knu.csc.emagazine.api.mapper;

import org.springframework.stereotype.Component;
import ua.knu.csc.emagazine.api.dto.publisher.CreatePublisherDTO;
import ua.knu.csc.emagazine.api.dto.publisher.PublisherDTO;
import ua.knu.csc.emagazine.domain.publisher.Publisher;

@Component
public class PublisherMapper {

    public PublisherDTO toDTO(Publisher publisher) {
        return new PublisherDTO(publisher.getId(), publisher.getName());
    }

    public Publisher toEntity(CreatePublisherDTO publisherDTO) {
        return new Publisher(null, publisherDTO.getName(), null);
    }

    public Publisher toEntity(PublisherDTO publisherDTO) {
        return new Publisher(publisherDTO.getId(), publisherDTO.getName(), null);
    }
}
