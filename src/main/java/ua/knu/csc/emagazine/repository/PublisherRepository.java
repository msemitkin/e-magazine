package ua.knu.csc.emagazine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.csc.emagazine.domain.publisher.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
