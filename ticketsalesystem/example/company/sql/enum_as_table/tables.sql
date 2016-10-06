-- company\enum_as_table\sql

CREATE TABLE enum_as_table.EMPLOYEETYPE (
                        ID          INTEGER NOT NULL,
                        TYPE        VARCHAR(64)
                        );

ALTER TABLE enum_as_table.EMPLOYEETYPE
    ADD CONSTRAINT pk_EMPLOYEETYPE PRIMARY KEY (ID);


CREATE TABLE enum_as_table.EMPLOYEE (
                        ID              INTEGER NOT NULL,
                        NAME            VARCHAR,
                        SALARY          BIGINT,
                        EMPLOYEETYPE_ID INTEGER
                        );

ALTER TABLE enum_as_table.EMPLOYEE
    ADD CONSTRAINT pk_EMPLOYEE PRIMARY KEY (ID);

ALTER TABLE enum_as_table.EMPLOYEE
    ADD CONSTRAINT FK_EMPLOYEETYPE  FOREIGN KEY (EMPLOYEETYPE_ID)
                                    REFERENCES   enum_as_table.EMPLOYEETYPE;

