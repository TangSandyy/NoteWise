package jku.at.repository;

import jku.at.entity.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SummaryRepository extends JpaRepository<Summary, Long> {

    List<Summary> findByStudyDocumentId(Long studyDocumentId);
}
