--liquibase formatted sql

--changeset belkinmike:create_users_table
CREATE TABLE IF NOT EXISTS users
(
    id              BIGINT      PRIMARY KEY,
    created_at      TIMESTAMPTZ NOT NULL,
    updated_at      TIMESTAMPTZ NOT NULL,
    telegram_login  TEXT        UNIQUE NOT NULL
);

CREATE SEQUENCE users_seq
    MINVALUE 1
    INCREMENT BY 100
    OWNED BY users.id;

CREATE INDEX idx_telegram_login ON users (telegram_login);