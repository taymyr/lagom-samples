# --- !Ups

CREATE TABLE ENTITY (
  ID CHAR(36) PRIMARY KEY,
  PROPERTY VARCHAR(100 CHAR) NOT NULL,
);

CREATE TABLE ENTITY_PART (
  ENTITY_ID CHAR(36),
  NAME VARCHAR(100 CHAR),
  PRIMARY KEY (ENTITY_ID, NAME),
  CONSTRAINT ENTITY_PART_FK FOREIGN KEY (ENTITY_ID) REFERENCES ENTITY(ID)
);

# --- !Downs

DROP TABLE ENTITY_PART;
DROP TABLE ENTITY;