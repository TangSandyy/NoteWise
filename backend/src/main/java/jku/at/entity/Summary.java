package jku.at.entity;

import jakarta.persistence.*;

@Entity
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "study_document_id", nullable = false)
    private StudyDocument studyDocument;

    public Summary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StudyDocument getStudyDocument() {
        return studyDocument;
    }

    public void setStudyDocument(StudyDocument studyDocument) {
        this.studyDocument = studyDocument;
    }
}
