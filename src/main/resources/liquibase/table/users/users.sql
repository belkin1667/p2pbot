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

CREATE TABLE IF NOT EXISTS userbase
(
    id              BIGINT      PRIMARY KEY
    first_name       STRING      NOT NULL
    last_name        STRING      NOT NULL
    telegram_login   STRING      NOT NULL
)

CREATE TABLE IF NOT EXISTS reviews
(
    id          BIGINT PRIMARY KEY
    user_id      BIGINT NOT NULL
    text        STRING NOT NULL
    author_id    BIGINT NOT NULL
    FOREIGN KEY (userid) REFERENCES userbase(id) ON DELETE CASCADE
    FOREIGN KEY (authorid) REFERENCES userbase(id) ON DELETE CASCADE
)

CREATE TABLE IF NOT EXISTS userglobal
(
    id          BIGINT  PRIMARY KEY
    base_id      BIGINT  NOT NULL
    city        STRING  NOT NULL
    country     STRING  NOT NULL
    rating      FLOAT  NOT NULL
    photo_url    STRING  NOT NULL
    reviews     BIGINT  NOT NULL
    FOREIGN KEY (baseid) REFERENCES userbase(id) ON DELETE CASCADE
    FOREIGN KEY (reviews) REFERENCES reviews(id) ON DELETE CASCADE
)