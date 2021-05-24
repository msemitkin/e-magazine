package ua.knu.csc.emagazine.domain.publication;

import org.springframework.stereotype.Service;
import ua.knu.csc.emagazine.api.exception.EntityNotFoundException;
import ua.knu.csc.emagazine.domain.keyword.KeyWord;
import ua.knu.csc.emagazine.repository.PublicationRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicationService {
    public static final String NOT_EXISTS_MESSAGE = "Publication with given id does not exist";

    private final PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public Publication findById(Integer id) {
        Optional<Publication> found = publicationRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new EntityNotFoundException(NOT_EXISTS_MESSAGE);
        }
    }

    public List<Publication> findByKeyWords(Collection<Integer> keyWordsIds) {
        return publicationRepository.findAll().stream()
            .filter(publication -> publication.getKeyWords().stream()
                .map(KeyWord::getId)
                .collect(Collectors.toList())
                .containsAll(keyWordsIds))
            .collect(Collectors.toList());
    }

    public Publication update(Publication publication) {
        if (publicationRepository.existsById(publication.getId())) {
            return publicationRepository.save(publication);
        } else {
            throw new EntityNotFoundException(NOT_EXISTS_MESSAGE);
        }
    }

    public void delete(Integer id) {
        if (publicationRepository.existsById(id)) {
            publicationRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(NOT_EXISTS_MESSAGE);
        }
    }


}
