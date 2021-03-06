package newexam.demo.repository;

import newexam.demo.model.entity.Classification;
import newexam.demo.model.entity.ClassificationName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, String> {
    Optional<Classification> findByClassificationName(ClassificationName classificationName);
}
