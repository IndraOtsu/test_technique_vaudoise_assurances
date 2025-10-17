# Vaudoise Insurance API

This is a technical test for Vaudoise, I hope you like it and is up to your standards!
## Tech Stack
- Java 21, Spring Boot 3 (Web, Data JPA)
- PostgreSQL
- Gradle
- springdoc-openapi (Swagger UI)
- Docker (optional)

## Architecture & Design
- Layered architecture:
  - Controller layer exposes REST endpoints.
  - Service layer holds business logic (CRUD, filters, aggregations).
  - JPA entities/DTOs model the domain (Person, Client, Company, Contract).
- IDs are UUIDs; JPA with PostgreSQL for persistence.
- OpenAPI annotations provide generated API docs (Swagger UI).
- Profiles:
  - pd (production-like): server port 8080. In Docker, datasource is provided via env vars.
  - dv (development): server port 8085; datasource for local development.

This is the preferred Java Springboot tech stack for such a service, balancing simplicity and functionality. 
The layered architecture keeps concerns separated, making it maintainable and extensible. Using JPA with PostgreSQL is a common choice for relational data, and UUIDs ensure unique identifiers across distributed systems. The use of profiles allows easy switching between production-like and development configurations.
## Run the service
Prerequisites for local run: Java 21, Docker Desktop (optional for DB), and internet access to download dependencies.

Option A — Docker (app + DB)
1. From the repository root, start the stack:
```
docker compose -f docker\docker-compose.yml up -d --build
```
2. App will be available at http://localhost:8080 (profile pd). Swagger UI at http://localhost:8080/swagger-ui/index.html.
3. Stop and clean:
```
docker compose -f docker\docker-compose.yml down -v
```

Option B — Local app, Docker DB
1. Start only the database:
```
cd C:\Users\Indra\OneDrive\Documentos\Projects\test_technique_vaudoise_assurances
docker compose -f docker\docker-compose.yml up -d db
```
2. Run the app with the dv profile:
```
cd vaudoise
gradlew.bat bootRun --args="--spring.profiles.active=dv"
```
3. App will be at http://localhost:8085 (Swagger at /swagger-ui/index.html).

Notes
- Docker compose sets these env vars for the app: SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/Vaudoise, SPRING_DATASOURCE_USERNAME=root, SPRING_DATASOURCE_PASSWORD=Vaudoise_Cest_Cool_1410, SPRING_PROFILES_ACTIVE=pd.
- DB schema and seed live under docker/database (db_definition.sql, initial_data.sql).

## API overview
Base URL
- Docker (dv/pd): http://localhost:8080
- Local dv: http://localhost:8085

Health
- GET /smoke → plain text health check.

Person
- GET /person — list all.
- GET /person/{id} — get by UUID.
- POST /person — create (JSON body).
- PUT /person/{id} — update (JSON body).
- DELETE /person/{id} — delete.

Client
- GET /client — list all.
- GET /client/{id} — get by UUID.

Company
- GET /company — list all.
- GET /company/{id} — get by UUID.
- POST /company — create.
- PUT /company/{id} — update.
- DELETE /company/{id} — delete.

Contract
- GET /contract — list with optional filters:
  - client_id (UUID), update_date (ISO-8601), inactive (boolean; include expired when true).
- GET /contract/{id} — get by UUID.
- GET /contract/sum/{client_id} — total of non-expired contracts for a client.
- POST /contract — create.
- PUT /contract/{id} — update.
- DELETE /contract/{id} — delete.

Test data
- GET /test/generate-data?client_amount=N&contract_amount_per_client=M — generates sample data.

## Documentation & Tools
- Swagger UI: /swagger-ui/index.html
- OpenAPI JSON: /v3/api-docs
- Postman collection: docs/Vaudoise.postman_collection.json (set `baseURL` to the Base URL above).

## Development
Install dependencies and run tests:
```
cd vaudoise
gradlew.bat clean test
```
Run the app (default profile):
```
gradlew.bat bootRun
```
Select profile via JVM args or Spring args, e.g.:
```
gradlew.bat bootRun --args="--spring.profiles.active=dv"
```

## Troubleshooting
- Port already in use: stop previous instance or change server.port in the active profile.
- DB connection errors:
  - Docker: ensure the db container is healthy (compose waits for it).
  - Local dv: verify PostgreSQL is reachable on localhost:5432 and credentials match the profile.
- Swagger not loading: confirm app is running and you’re using the correct port/profile.

