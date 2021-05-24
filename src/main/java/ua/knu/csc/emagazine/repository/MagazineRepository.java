package ua.knu.csc.emagazine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.csc.emagazine.domain.magazine.Magazine;

import java.util.List;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Integer> {

    List<Magazine> findByNameContaining(String subString);

    List<Magazine> findByCategoryId(int id);
}
