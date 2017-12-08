# Users schema

# --- !Ups

CREATE TABLE TENANT (
    id SERIAL PRIMARY KEY  ,
    name varchar(255) NOT NULL,
    tenant_guid varchar(255) NOT NULL,
    description varchar(255) NOT NULL
);

# --- !Downs

DROP TABLE TENANT;