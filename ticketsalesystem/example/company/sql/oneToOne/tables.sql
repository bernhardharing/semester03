-- company\oneToOne\sql

-- note : type SERIAL is spefic for Postgres (!)

CREATE TABLE oneToOne.PARKING_SPACE (
                            ID          SERIAL  NOT NULL,
                            LOT         INTEGER,
                            LOCATION    VARCHAR
                            );

ALTER TABLE oneToOne.PARKING_SPACE
    ADD CONSTRAINT PARKING_SPACE_PK PRIMARY KEY (ID);


CREATE TABLE oneToOne.EMPLOYEE (
                        ID                  SERIAL NOT NULL,
                        FIRSTNAME           VARCHAR,
                        LASTNAME            VARCHAR,
                        SALARY              BIGINT,
                        PARKING_SPACE_ID    INTEGER UNIQUE
                        );

ALTER TABLE oneToOne.EMPLOYEE
    ADD CONSTRAINT EMPLOYEE_PK PRIMARY KEY (ID);

ALTER TABLE oneToOne.EMPLOYEE
    ADD CONSTRAINT PARKING_SPACE_PK  FOREIGN KEY (PARKING_SPACE_ID)
                                     REFERENCES  oneToOne.PARKING_SPACE;


