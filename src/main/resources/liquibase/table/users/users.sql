--liquibase formatted sql

--changeset belkinmike:create_users_table
CREATE TABLE IF NOT EXISTS users
(
    id              BIGINT      PRIMARY KEY,
    created_at      TIMESTAMPTZ NOT NULL default current_timestamp,
    updated_at      TIMESTAMPTZ NOT NULL default current_timestamp,
    telegram_login  TEXT        UNIQUE NOT NULL,
    telegram_id     BIGINT      UNIQUE NOT NULL,
    first_name      TEXT        NOT NULL,
    last_name       TEXT        NOT NULL,
    city            TEXT,
    country         TEXT,
    rating          FLOAT,
    photo_url       TEXT,
    dialog_stage    TEXT NOT NULL DEFAULT 'UNKNOWN'
);

CREATE SEQUENCE IF NOT EXISTS users_seq
    MINVALUE 1
    INCREMENT BY 100
    OWNED BY users.id;

CREATE UNIQUE INDEX IF NOT EXISTS idx_telegram_login ON users (telegram_login);
CREATE UNIQUE INDEX IF NOT EXISTS idx_telegram_id ON users (telegram_id);

--changeset targimec:change_set_name
CREATE TABLE IF NOT EXISTS review
(
    id              BIGINT  PRIMARY KEY,
    user_id         BIGINT  NOT NULL,
    text            TEXT    NOT NULL,
    author_id       BIGINT  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE SEQUENCE IF NOT EXISTS review_seq
    MINVALUE 1
    INCREMENT BY 100
    OWNED BY review.id;

CREATE INDEX IF NOT EXISTS idx_review_user_id ON review (user_id);
CREATE INDEX IF NOT EXISTS idx_review_author_id ON review (author_id);
