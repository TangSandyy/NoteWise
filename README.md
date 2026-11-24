# NoteWise

Web-Service zur Erstellung von Lernhilfen (Zusammenfassungen, Karteikarten, Chat) aus hochgeladenen Dateien.

Team: Elisabeth Heumel, Firdes Kiraci, Selina Kopf, Sandy Tang

## Projektstruktur

```
NoteWise/
├── backend/    Spring Boot (Java 17, Maven), REST-API, Datenbank, KI-Anbindung
├── frontend/   Nuxt (TypeScript, Nuxt UI, Pinia)
└── docs/       Datenmodell, Systemarchitektur
```

## Backend starten

**Voraussetzungen**
- Java 17 oder neuer
- Docker Desktop installiert und gestartet

**Starten**

```bash
cd backend
./mvnw spring-boot:run
```

Die PostgreSQL-Datenbank startet dabei automatisch über Docker Compose (Container `notewise-db`) und läuft nach dem Stoppen der App weiter. Beim ersten Start werden Testdaten angelegt.

**In IntelliJ**
1. Den Ordner `NoteWise` öffnen und `backend/pom.xml` als Maven-Projekt laden („Load Maven Project“).
2. `NoteWiseApplication` starten. Falls der Fehler „No Docker Compose file found“ kommt: Run → Edit Configurations → **Working directory** auf den Ordner `backend` setzen.

**OpenAI-API-Key**

`backend/.env.example` nach `backend/.env` kopieren und den Key eintragen. Die `.env` wird nicht gepusht. Ohne Key startet die App trotzdem, nur die KI-Aufrufe funktionieren nicht.

## API testen

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI-Definition (JSON): http://localhost:8080/v3/api-docs
- Health-Check: http://localhost:8080/actuator/health

## Branches

- `main` enthält nur fertige, lauffähige Stände
- pro Feature ein eigener Branch von `main`, benannt nach dem Feature:
  `feature/login`, `feature/dokument-upload`, `feature/zusammenfassungen`, `feature/karteikarten`, `feature/chat`
- ein Branch gilt für das ganze Feature (Frontend und Backend)
- Zusammenführen über Pull Request in `main`
