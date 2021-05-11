package ua.knu.csc.emagazine.api.mapper;

import org.springframework.stereotype.Component;
import ua.knu.csc.emagazine.api.dto.publication.CreatePublicationDTO;
import ua.knu.csc.emagazine.api.dto.publication.PublicationDTO;
import ua.knu.csc.emagazine.domain.keyword.KeyWord;
import ua.knu.csc.emagazine.domain.publication.Publication;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublicationMapper {

    public PublicationDTO toDTO(Publication publication) {
        List<String> mappedKeyWords = publication.getKeyWords().stream()
            .map(KeyWord::getValue)
            .collect(Collectors.toList());
        return new PublicationDTO(publication.getId(), publication.getName(),
            publication.getText(), mappedKeyWords);
    }

    public Publication toEntity(CreatePublicationDTO publicationDTO) {
        List<KeyWord> mappedKeyWords = publicationDTO.getKeyWords().stream()
            .map(keyWordId -> new KeyWord(keyWordId, null))
            .collect(Collectors.toList());
        return new Publication(null, publicationDTO.getName(),
            publicationDTO.getText(), mappedKeyWords, null);
    }
}
