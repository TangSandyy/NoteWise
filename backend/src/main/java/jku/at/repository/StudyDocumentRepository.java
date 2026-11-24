package jku.at.repository;

import jku.at.entity.StudyDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyDocumentRepository extends JpaRepository<StudyDocument, Long> {

    List<StudyDocument> findByCourseId(Long courseId);
}
