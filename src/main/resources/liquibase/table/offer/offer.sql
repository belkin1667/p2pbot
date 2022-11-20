--liquibase formatted sql

--changeset belkinmike:offer_table
CREATE TABLE IF NOT EXISTS offer
(
    id         BIGINT PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    config_id  BIGINT      NOT NULL,
    user_id    BIGINT      NOT NULL,
    FOREIGN KEY (config_id) REFERENCES offer_config(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id)   REFERENCES users(id) ON DELETE CASCADE
);

CREATE SEQUENCE IF NOT EXISTS offer_seq
    MINVALUE 1
    INCREMENT BY 100
    OWNED BY offer.id;