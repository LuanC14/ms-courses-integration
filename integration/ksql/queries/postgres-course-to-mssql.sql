    CREATE STREAM IF NOT EXISTS STR_COURSES_POSTGRES (
            id VARCHAR,
            name VARCHAR,
            description VARCHAR,
            capacity INT,
            integrated BOOLEAN

    ) WITH (KAFKA_TOPIC='TP-CREATED-COURSES-AVRO', VALUE_FORMAT='AVRO');

CREATE STREAM IF NOT EXISTS STR_COURSES_POSTGRES_TO_ORACLE AS SELECT
    id AS ID,
    name AS NOME,
    description AS DESCRICAO,
    capacity AS CAPACIDADE,
    integrated AS INTEGRATED
FROM STR_COURSES_POSTGRES;