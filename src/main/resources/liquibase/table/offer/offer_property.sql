--liquibase formatted sql

--changeset belkinmike:offer_property_table
CREATE TABLE IF NOT EXISTS offer_property
(
    id            BIGINT PRIMARY KEY,
    created_at    TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at    TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    type          TEXT        NOT NULL,
    name          TEXT        NOT NULL,
    values        JSONB       NOT NULL,
    config_id     BIGINT      NOT NULL,
    constraint_id BIGINT,
    FOREIGN KEY (constraint_id) REFERENCES property_constraint (id) ON DELETE CASCADE,
    FOREIGN KEY (config_id) REFERENCES offer_config (id) ON DELETE CASCADE
);

CREATE SEQUENCE IF NOT EXISTS offer_property_seq
    MINVALUE 1
    INCREMENT BY 100
    OWNED BY offer_property.id;