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

--changeset targimec:change_set_name
ALTER TABLE users
    ADD COLUMN IF NOT EXISTS   first_name  TEXT,
    ADD COLUMN IF NOT EXISTS   last_name   TEXT,
    ADD COLUMN IF NOT EXISTS   city        TEXT,
    ADD COLUMN IF NOT EXISTS   country     TEXT,
    ADD COLUMN IF NOT EXISTS   rating      FLOAT,
    ADD COLUMN IF NOT EXISTS   photo_url   TEXT;

CREATE TABLE IF NOT EXISTS review
(
    id              BIGINT  PRIMARY KEY,
    user_id         BIGINT  NOT NULL,
    text            TEXT    NOT NULL,
    author_id       BIGINT  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);

--changeset belkinmike:add_telegram_id
ALTER TABLE users
    ADD COLUMN telegram_id BIGINT,
    ADD COLUMN stage TEXT;

CREATE INDEX idx_telegram_id ON users (telegram_id);
