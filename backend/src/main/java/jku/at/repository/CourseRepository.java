package jku.at.repository;

import jku.at.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByUserId(Long userId);

    // findet den Kurs nur, wenn er auch diesem User gehört
    Optional<Course> findByIdAndUserId(Long id, Long userId);
}
