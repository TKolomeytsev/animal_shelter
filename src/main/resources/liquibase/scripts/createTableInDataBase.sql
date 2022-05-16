-- liquibase formatted sql
-- changeset TelegaBase:1
CREATE TABLE data_messages
(
    id character varying(255) PRIMARY KEY
    , message character varying(255)
    , chat_id bigint NOT NULL
    , date_send timestamp without time zone
);