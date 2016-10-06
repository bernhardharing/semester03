-- company\enum\sql

CREATE TABLE enumeration.EMPLOYEE (
                        ID          INTEGER NOT NULL,
                        NAME        VARCHAR,
                        SALARY      BIGINT,
                        TYPE        VARCHAR(64)
                        );

ALTER TABLE enumeration.EMPLOYEE
    ADD CONSTRAINT pk_EMPLOYEE PRIMARY KEY (ID);

