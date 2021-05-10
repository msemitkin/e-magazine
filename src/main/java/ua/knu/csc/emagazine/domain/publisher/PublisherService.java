package ua.knu.csc.emagazine.domain.publisher;

import org.springframework.stereotype.Service;
import ua.knu.csc.emagazine.api.exception.EntityNotFoundException;
import ua.knu.csc.emagazine.repository.PublisherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

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
        throw new EntityNotFoundException("Publisher with given id not found");
    }

    public Publisher update(Publisher publisher) {
        if (publisherRepository.existsById(publisher.getId())) {
            return publisherRepository.save(publisher);
        } else {
            throw new EntityNotFoundException("Publisher with given id not found");
        }
    }
}
