CREATE TABLE users (

id BIGSERIAL PRIMARY KEY NOT NULL,
name VARCHAR NOT NULL,
password VARCHAR NOT NULL,
email VARCHAR NOT NULL,
birthday DATE,
role VARCHAR NOT NULL,
token VARCHAR
);

CREATE TABLE visitor (
id BIGSERIAL PRIMARY KEY NOT NULL,
visitor_name VARCHAR NOT NULL,
date_visit DATE DEFAULT CURRENT_DATE

);

CREATE TABLE visitor_infos (
id BIGSERIAL PRIMARY KEY NOT NULL,
about_us VARCHAR NOT NULL,
leadership VARCHAR NOT NULL,
social_media VARCHAR NOT NULL

);

CREATE TABLE groups (
id BIGSERIAL PRIMARY KEY NOT NULL,
participant VARCHAR NOT NULL,
messages VARCHAR,
type VARCHAR NOT NULL,
created_on DATE DEFAULT CURRENT_DATE

);

CREATE TABLE home_content (
id BIGSERIAL PRIMARY KEY NOT NULL,
title VARCHAR NOT NULL,
photo_id VARCHAR NOT NULL,
content VARCHAR NOT NULL

);

CREATE TABLE gallery (
id BIGSERIAL PRIMARY KEY NOT NULL,
title VARCHAR NOT NULL,
photo_id VARCHAR NOT NULL,
description VARCHAR,
type VARCHAR NOT NULL,
date_photo DATE DEFAULT CURRENT_DATE

);

CREATE TABLE agenda (
id BIGSERIAL PRIMARY KEY NOT NULL,
event VARCHAR NOT NULL,
date_event DATE DEFAULT CURRENT_DATE,
description VARCHAR,
type VARCHAR NOT NULL

);