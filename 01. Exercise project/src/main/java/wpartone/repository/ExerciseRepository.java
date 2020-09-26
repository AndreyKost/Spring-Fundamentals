package wpartone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wpartone.model.entity.Exercise;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, String> {
    Optional<Exercise> findByName(String name);
}
