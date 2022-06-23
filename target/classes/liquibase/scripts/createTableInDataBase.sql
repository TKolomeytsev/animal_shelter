-- liquibase formatted sql
-- changeset TelegaBase:1
CREATE TABLE data_messages
(
    id character varying(255) PRIMARY KEY
    , message character varying(255)
    , chat_id bigint NOT NULL
    , date_send timestamp without time zone
);

CREATE TABLE nsi_commands(
    id character varying(255) PRIMARY KEY
    , command character varying(255)
);

CREATE TABLE nsi_animal_kind(
    id character varying(255) PRIMARY KEY
    , name character varying(255)
);

CREATE TABLE nsi_breed_animal(
    id character varying(255) PRIMARY KEY
    , name character varying(255)
);

CREATE TABLE data_animal_photo(
    id character varying(255) PRIMARY KEY
    , id_animal character varying(255)
    , description character varying(255)
    , content oid
);

CREATE TABLE data_animal(
    id character varying(255) PRIMARY KEY
    , nickname character varying(255)
    , id_kind character varying(255)
    , id_breed character varying(255)
    , age integer
    , weight integer
    , growth numeric
    , color character varying(255)
);

CREATE TABLE standart_response(
    id character varying(255) PRIMARY KEY
    , relation_id character varying(255)
    , response_name character varying(255)
    , response_text character varying(255)
);