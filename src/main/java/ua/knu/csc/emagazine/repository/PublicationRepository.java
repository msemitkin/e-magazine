package ua.knu.csc.emagazine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.csc.emagazine.domain.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {
}
