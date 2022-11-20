--liquibase formatted sql

--changeset belkinmike:offer_config_table
CREATE TABLE IF NOT EXISTS offer_config
(
    id         BIGINT PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    status     TEXT        NOT NULL DEFAULT 'ACTIVE',
    name       TEXT        NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS offer_config_seq
    MINVALUE 1
    INCREMENT BY 100
    OWNED BY offer_config.id;

CREATE INDEX IF NOT EXISTS idx_offer_config_name ON offer_config (name);