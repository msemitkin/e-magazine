package ua.knu.csc.emagazine.domain.magazine;

import org.springframework.stereotype.Service;
import ua.knu.csc.emagazine.api.exception.EntityNotFoundException;
import ua.knu.csc.emagazine.repository.MagazineRepository;

import java.util.List;
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

    public List<Magazine> findByNameSubString(String subString) {
        return magazineRepository.findByNameContaining(subString);
    }

    public List<Magazine> findByCategory(int categoryId) {
        return magazineRepository.findByCategoryId(categoryId);
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
