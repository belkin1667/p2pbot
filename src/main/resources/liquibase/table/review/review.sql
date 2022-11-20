--liquibase formatted sql

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