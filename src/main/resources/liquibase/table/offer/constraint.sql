--liquibase formatted sql

--changeset belkinmike:constraint_table
CREATE TABLE IF NOT EXISTS property_constraint
(
    id         BIGINT PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    type       TEXT        NOT NULL,
    values     JSONB       NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS property_constraint_seq
    MINVALUE 1
    INCREMENT BY 100
    OWNED BY property_constraint.id;

CREATE INDEX IF NOT EXISTS idx_constraint_type_and_id_idx ON property_constraint (type, id);