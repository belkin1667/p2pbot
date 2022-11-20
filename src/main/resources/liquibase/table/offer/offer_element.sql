--liquibase formatted sql

--changeset belkinmike:offer_element_table
CREATE TABLE IF NOT EXISTS offer_element
(
    id          BIGINT PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    property_id BIGINT      NOT NULL,
    value       TEXT        NOT NULL,
    FOREIGN KEY (property_id) REFERENCES offer_property (id) ON DELETE CASCADE
);

CREATE SEQUENCE IF NOT EXISTS offer_element_seq
    MINVALUE 1
    INCREMENT BY 100
    OWNED BY offer_element.id;


--changeset belkinmike:add_offer_element_offer_id
ALTER TABLE offer_element ADD COLUMN offer_id BIGINT NOT NULL REFERENCES offer(id);