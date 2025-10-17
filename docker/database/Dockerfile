FROM postgres:16-alpine

ENV POSTGRES_DB=Vaudoise \
    POSTGRES_USER=root \
    POSTGRES_PASSWORD=Vaudoise_Cest_Cool_1410

COPY ./docker/database/db_definition.sql /docker-entrypoint-initdb.d/01_init.sql
COPY ./docker/database/initial_data.sql /docker-entrypoint-initdb.d/02_seed.sql

EXPOSE 5432

HEALTHCHECK --interval=10s --timeout=5s --retries=5 CMD pg_isready -U "$POSTGRES_USER" -d "$POSTGRES_DB" || exit 1
