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

Voraussetzungen: Java 17+ und Docker Desktop (muss laufen)

```bash
cd backend
./mvnw spring-boot:run
```

Die PostgreSQL-Datenbank startet dabei automatisch über Docker Compose. Beim ersten Start werden Testdaten angelegt.

In IntelliJ den Ordner `NoteWise` öffnen und `backend/pom.xml` als Maven-Projekt laden.

**OpenAI-API-Key:** `backend/.env.example` nach `backend/.env` kopieren und den Key eintragen. Die `.env` wird nicht gepusht.

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
