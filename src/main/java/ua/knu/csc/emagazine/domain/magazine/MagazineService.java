package ua.knu.csc.emagazine.domain.magazine;

import org.springframework.stereotype.Service;
import ua.knu.csc.emagazine.api.exception.EntityNotFoundException;
import ua.knu.csc.emagazine.repository.MagazineRepository;

import java.util.Optional;

@Service
public class MagazineService {
    public static final String NOT_EXIST_MESSAGE = "Magazine with given id does not exist";

    private final MagazineRepository magazineRepository;

    public MagazineService(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    public Magazine findById(Integer id) {
        Optional<Magazine> found = magazineRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new EntityNotFoundException(NOT_EXIST_MESSAGE);
        }
    }

    public Magazine update(Magazine magazine) {
        if (magazineRepository.existsById(magazine.getId())) {
            return magazineRepository.save(magazine);
        } else {
            throw new EntityNotFoundException(NOT_EXIST_MESSAGE);
        }
    }

    public void deleteById(Integer id) {
        if (magazineRepository.existsById(id)) {
            magazineRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(NOT_EXIST_MESSAGE);
        }
    }
}
