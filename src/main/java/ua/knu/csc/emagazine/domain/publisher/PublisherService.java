package ua.knu.csc.emagazine.domain.publisher;

import org.springframework.stereotype.Service;
import ua.knu.csc.emagazine.api.exception.EntityNotFoundException;
import ua.knu.csc.emagazine.repository.PublisherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    private static final String PUBLISHER_WITH_ID_NOT_FOUND_MESSAGE = "Publisher with given id not found";

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Publisher findById(Integer id) {
        Optional<Publisher> found = publisherRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        }
        throw new EntityNotFoundException(PUBLISHER_WITH_ID_NOT_FOUND_MESSAGE);
    }

    public Publisher update(Publisher publisher) {
        if (publisherRepository.existsById(publisher.getId())) {
            return publisherRepository.save(publisher);
        } else {
            throw new EntityNotFoundException(PUBLISHER_WITH_ID_NOT_FOUND_MESSAGE);
        }
    }

    public void delete(int id) {
        if (publisherRepository.existsById(id)) {
            publisherRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(PUBLISHER_WITH_ID_NOT_FOUND_MESSAGE);
        }
    }
}
