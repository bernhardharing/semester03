-- company\sql\oneToOneSamePK

CREATE TABLE oneToOneSamePK.EMPLOYEE (
                        ID                  INTEGER,
                        FIRSTNAME           VARCHAR,
                        LASTNAME            VARCHAR,
                        SALARY              BIGINT
                        );

ALTER TABLE oneToOneSamePK.EMPLOYEE
    ADD CONSTRAINT EMPLOYEE_PK PRIMARY KEY (ID);



CREATE TABLE oneToOneSamePK.NON_DISCLOSURE (
                            EMPLOYEE_ID INTEGER,
                            GRADE       VARCHAR,
                            INFO        VARCHAR
                            );

ALTER TABLE oneToOneSamePK.NON_DISCLOSURE
    ADD CONSTRAINT NON_DISCLOSURE_PK PRIMARY KEY (EMPLOYEE_ID);


ALTER TABLE oneToOneSamePK.NON_DISCLOSURE
    ADD CONSTRAINT EMPLOYEE_PK  FOREIGN KEY (EMPLOYEE_ID)
                                REFERENCES  oneToOneSamePK.EMPLOYEE;


