# Bhargava Institute – API

Spring Boot 3 · Java 25 · PostgreSQL · springdoc OpenAPI

## Run

```bash
docker compose up -d          # PostgreSQL 16 on localhost:5432
mvn spring-boot:run
```

Hibernate creates or updates the PostgreSQL schema on startup.

- Optional manual schema and starter data: `psql -d bhargava_institute -f src/main/resources/db/init.sql`
- The SQL script seeds example catalog and quiz records; run it once against an empty database.
- Contact messages are submitted through the API and are not seeded.

- API:      http://localhost:8080/api
- Swagger:  http://localhost:8080/swagger-ui.html
- Health:   http://localhost:8080/actuator/health

## Endpoints

| Method | Path                  | Purpose                                              |
|--------|-----------------------|------------------------------------------------------|
| GET    | `/api/banners`        | Home page slider                                     |
| GET    | `/api/courses`        | Course cards                                         |
| GET    | `/api/courses/{id}`   | Course with its lessons                              |
| GET    | `/api/subjects`       | Subjects page                                        |
| GET    | `/api/materials`      | Study material list                                  |
| GET    | `/api/stats`          | Course and question counts for the home page         |
| GET    | `/api/quiz/questions` | Questions + options (correct answer is NOT included) |
| POST   | `/api/quiz/submit`    | `{answers:[{questionId,answer}]}` -> score + answers |
| POST   | `/api/contact`        | `{name,email,message}` -> 201                        |

Errors are JSON: `{timestamp,status,error,message,fieldErrors}`.

## Configuration (env vars)

| Variable               | Default                                               |
|------------------------|-------------------------------------------------------|
| `DB_URL`               | `jdbc:postgresql://localhost:5432/bhargava_institute` |
| `DB_USERNAME`          | `bhargava`                                            |
| `DB_PASSWORD`          | `bhargava`                                            |
| `CORS_ALLOWED_ORIGINS` | `http://localhost:5173,http://127.0.0.1:5173`         |
| `PORT`                 | `8080`                                                |

## Changing content

Manage catalog and quiz records in PostgreSQL directly.
Contact messages land in the `contact_message` table.
