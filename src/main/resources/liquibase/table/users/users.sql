--liquibase formatted sql

--changeset belkinmike:create_users_table
CREATE TABLE IF NOT EXISTS users
(
    id                  BIGINT PRIMARY KEY,
    created_at          TIMESTAMPTZ   NOT NULL default current_timestamp,
    updated_at          TIMESTAMPTZ   NOT NULL default current_timestamp,
    telegram_login      TEXT UNIQUE   NOT NULL,
    telegram_id         BIGINT UNIQUE NOT NULL,
    first_name          TEXT          NOT NULL,
    last_name           TEXT          NOT NULL,
    city                TEXT,
    country             TEXT,
    rating              FLOAT,
    photo_url           TEXT,
    dialog_stage        TEXT          NOT NULL DEFAULT 'UNKNOWN',
    previous_message_id BIGINT,
    current_offer_id    BIGINT,
    next_offer_config_property_number BIGINT
);

CREATE SEQUENCE IF NOT EXISTS users_seq
    MINVALUE 1
    INCREMENT BY 100
    OWNED BY users.id;

CREATE UNIQUE INDEX IF NOT EXISTS idx_telegram_login ON users (telegram_login);
CREATE UNIQUE INDEX IF NOT EXISTS idx_telegram_id ON users (telegram_id);
