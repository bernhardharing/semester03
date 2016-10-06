-- company\manyToOne-BiDir\sql

-- note : type SERIAL is specific for Postgres (!)

CREATE TABLE manyToOne.DEPARTMENT (
                         ID             SERIAL  NOT NULL,
                         NAME           VARCHAR
                         );

ALTER TABLE manyToOne.DEPARTMENT
    ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY (ID);


CREATE TABLE manyToOne.EMPLOYEE (
                        ID              SERIAL NOT NULL,
                        FIRSTNAME       VARCHAR,
                        LASTNAME        VARCHAR,
                        SALARY          BIGINT,
                        DEPARTMENT_ID   INTEGER
                        );

ALTER TABLE manyToOne.EMPLOYEE
    ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY (ID);

ALTER TABLE manyToOne.EMPLOYEE
    ADD CONSTRAINT FK_DEPARTMENT    FOREIGN KEY (DEPARTMENT_ID)
                                    REFERENCES   manyToOne.DEPARTMENT;


