package ua.knu.csc.emagazine.domain.magazine;

import org.springframework.stereotype.Service;
import ua.knu.csc.emagazine.api.exception.EntityNotFoundException;
import ua.knu.csc.emagazine.repository.MagazineRepository;

import java.util.Optional;

@Service
public class MagazineService {

    private final MagazineRepository magazineRepository;

    public MagazineService(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    public Magazine findById(Integer id) {
        Optional<Magazine> found = magazineRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new EntityNotFoundException("Magazine with given id was not found");
        }
    }

    public Magazine update(Magazine magazine) {
        if (magazineRepository.existsById(magazine.getId())) {
            return magazineRepository.save(magazine);
        } else {
            throw new EntityNotFoundException("Magazine with given id does not exist");
        }
    }

    public void deleteById(Integer id) {
        if (magazineRepository.existsById(id)) {
            magazineRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Magazine with given id does not exist");
        }
    }
}
