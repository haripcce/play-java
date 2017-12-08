# Users schema

# --- !Ups
DROP TABLE TENANT;
CREATE TABLE TENANT (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    tenant_guid varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE TENANT;