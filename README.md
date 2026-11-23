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

```bash
cd backend
./mvnw spring-boot:run
```

In IntelliJ den Ordner `NoteWise` öffnen und `backend/pom.xml` als Maven-Projekt laden.

## Branches

- `main` enthält nur fertige, lauffähige Stände
- pro Feature ein eigener Branch von `main`, benannt nach dem Feature:
  `feature/login`, `feature/dokument-upload`, `feature/zusammenfassungen`, `feature/karteikarten`, `feature/chat`
- ein Branch gilt für das ganze Feature (Frontend und Backend)
- Zusammenführen über Pull Request in `main`
