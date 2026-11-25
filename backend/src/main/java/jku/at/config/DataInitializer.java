package jku.at.config;

import jku.at.entity.Course;
import jku.at.entity.Flashcard;
import jku.at.entity.StudyDocument;
import jku.at.entity.Summary;
import jku.at.entity.User;
import jku.at.repository.CourseRepository;
import jku.at.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// Legt beim Start Testdaten für das Team an.
// Läuft nur, wenn es noch keine Kurse gibt, damit nichts doppelt angelegt wird.
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public DataInitializer(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        User sandy = getOrCreateUser("sandy");
        User firdes = getOrCreateUser("firdes");
        User selina = getOrCreateUser("selina");
        User elisabeth = getOrCreateUser("elisabeth");

        if (courseRepository.count() > 0) {
            return;
        }

        // --- Sandy: Data Warehousing ---
        Course dwh = createCourse(sandy, "Data Warehousing");

        StudyDocument dwhVo1 = createDocument(dwh, "DWH_VO01_Einfuehrung.pdf",
                "Ein Data Warehouse ist eine zentrale Datenbank, die Daten aus verschiedenen Quellsystemen "
                        + "zusammenführt und für Analysen bereitstellt. Die Daten werden über einen ETL-Prozess "
                        + "(Extract, Transform, Load) geladen. Im Gegensatz zu operativen Systemen (OLTP) ist ein "
                        + "Data Warehouse für Auswertungen (OLAP) optimiert.");
        createSummary(dwhVo1, "Ein Data Warehouse sammelt Daten aus mehreren Quellsystemen an einer zentralen Stelle. "
                + "Die Daten kommen über den ETL-Prozess hinein und werden für Analysen (OLAP) optimiert gespeichert, "
                + "während operative Systeme (OLTP) auf das Tagesgeschäft ausgelegt sind.");
        createFlashcard(dwhVo1, "Wofür steht ETL?", "Extract, Transform, Load");
        createFlashcard(dwhVo1, "Was ist der Unterschied zwischen OLTP und OLAP?",
                "OLTP unterstützt das operative Tagesgeschäft mit vielen kleinen Transaktionen, "
                        + "OLAP ist auf komplexe Auswertungen über große Datenmengen ausgelegt.");

        StudyDocument dwhVo2 = createDocument(dwh, "DWH_VO02_Sternschema.pdf",
                "Das Sternschema besteht aus einer zentralen Faktentabelle und mehreren Dimensionstabellen. "
                        + "Die Faktentabelle enthält Kennzahlen wie Umsatz oder Menge, die Dimensionstabellen "
                        + "beschreibende Attribute wie Zeit, Produkt oder Filiale.");
        createFlashcard(dwhVo2, "Woraus besteht ein Sternschema?",
                "Aus einer zentralen Faktentabelle mit Kennzahlen und mehreren Dimensionstabellen mit beschreibenden Attributen.");

        // --- Sandy: Service Engineering ---
        Course se = createCourse(sandy, "Service Engineering");

        StudyDocument seVo1 = createDocument(se, "SE_VO01_REST.pdf",
                "REST ist ein Architekturstil für verteilte Systeme. Ressourcen werden über URLs adressiert "
                        + "und mit HTTP-Methoden bearbeitet: GET liest, POST legt an, PUT ändert und DELETE löscht. "
                        + "Die Kommunikation ist zustandslos.");
        createSummary(seVo1, "REST adressiert Ressourcen über URLs und nutzt die HTTP-Methoden GET, POST, PUT und DELETE. "
                + "Jede Anfrage ist zustandslos und enthält alle nötigen Informationen.");
        createFlashcard(seVo1, "Was bedeutet zustandslos bei REST?",
                "Der Server speichert keinen Zustand zwischen Anfragen, jede Anfrage enthält alle nötigen Informationen.");
        createFlashcard(seVo1, "Welche HTTP-Methode wird zum Anlegen einer Ressource verwendet?", "POST");

        // --- Firdes: Softwareentwicklung ---
        Course swe = createCourse(firdes, "Softwareentwicklung");

        StudyDocument sweVo1 = createDocument(swe, "SWE_VO01_OOP.pdf",
                "Objektorientierte Programmierung baut auf Klassen und Objekten auf. Wichtige Prinzipien sind "
                        + "Kapselung, Vererbung und Polymorphie. Kapselung bedeutet, dass der innere Zustand eines "
                        + "Objekts nur über definierte Methoden verändert werden kann.");
        createSummary(sweVo1, "OOP arbeitet mit Klassen und Objekten. Die drei zentralen Prinzipien sind Kapselung, "
                + "Vererbung und Polymorphie.");
        createFlashcard(sweVo1, "Was bedeutet Kapselung?",
                "Der innere Zustand eines Objekts kann nur über definierte Methoden verändert werden.");
        createFlashcard(sweVo1, "Nenne die drei zentralen Prinzipien der OOP.", "Kapselung, Vererbung und Polymorphie");

        // --- Selina: DevOps und Cloud Computing ---
        Course devops = createCourse(selina, "DevOps und Cloud Computing");

        StudyDocument devopsVo1 = createDocument(devops, "DEVOPS_VO01_Container.pdf",
                "Container verpacken eine Anwendung mit allen Abhängigkeiten in eine isolierte Einheit. "
                        + "Docker ist das bekannteste Werkzeug dafür. Ein Image ist die Vorlage, ein Container die "
                        + "laufende Instanz eines Images. Mit Docker Compose lassen sich mehrere Container gemeinsam starten.");
        createSummary(devopsVo1, "Container bündeln eine Anwendung mit ihren Abhängigkeiten. Aus einem Image entsteht "
                + "ein laufender Container, mehrere Container lassen sich mit Docker Compose gemeinsam starten.");
        createFlashcard(devopsVo1, "Was ist der Unterschied zwischen Image und Container?",
                "Ein Image ist die Vorlage, ein Container ist die laufende Instanz eines Images.");
        createFlashcard(devopsVo1, "Wofür wird Docker Compose verwendet?",
                "Um mehrere Container gemeinsam zu beschreiben und zu starten.");

        // --- Elisabeth: Advanced Business Engineering ---
        Course abe = createCourse(elisabeth, "Advanced Business Engineering");

        StudyDocument abeVo1 = createDocument(abe, "ABE_VO01_BPMN.pdf",
                "BPMN (Business Process Model and Notation) ist ein Standard zur grafischen Modellierung von "
                        + "Geschäftsprozessen. Die wichtigsten Elemente sind Ereignisse, Aktivitäten, Gateways und "
                        + "Sequenzflüsse. Pools und Lanes zeigen, welche Organisation oder Rolle eine Aufgabe übernimmt.");
        createSummary(abeVo1, "BPMN ist der Standard zur Modellierung von Geschäftsprozessen. Prozesse bestehen aus "
                + "Ereignissen, Aktivitäten, Gateways und Sequenzflüssen, Pools und Lanes ordnen Aufgaben Rollen zu.");
        createFlashcard(abeVo1, "Wofür steht BPMN?", "Business Process Model and Notation");
        createFlashcard(abeVo1, "Wozu dienen Pools und Lanes in BPMN?",
                "Sie zeigen, welche Organisation oder Rolle eine Aufgabe übernimmt.");

        courseRepository.save(dwh);
        courseRepository.save(se);
        courseRepository.save(swe);
        courseRepository.save(devops);
        courseRepository.save(abe);
    }

    private User getOrCreateUser(String username) {
        return userRepository.findByUsername(username).orElseGet(() -> {
            User user = new User();
            user.setUsername(username);
            return userRepository.save(user);
        });
    }

    private Course createCourse(User user, String name) {
        Course course = new Course();
        course.setName(name);
        course.setUser(user);
        user.getCourses().add(course);
        return course;
    }

    private StudyDocument createDocument(Course course, String fileName, String content) {
        StudyDocument document = new StudyDocument();
        document.setFileName(fileName);
        document.setStoragePath("uploads/demo/" + fileName);
        document.setContent(content);
        document.setCourse(course);
        course.getDocuments().add(document);
        return document;
    }

    private void createSummary(StudyDocument document, String content) {
        Summary summary = new Summary();
        summary.setContent(content);
        summary.setStudyDocument(document);
        document.getSummaries().add(summary);
    }

    private void createFlashcard(StudyDocument document, String question, String answer) {
        Flashcard flashcard = new Flashcard();
        flashcard.setQuestion(question);
        flashcard.setAnswer(answer);
        flashcard.setStudyDocument(document);
        document.getFlashcards().add(flashcard);
    }
}
