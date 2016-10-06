-- company\sequence\sql


CREATE TABLE sequence.EMPLOYEE (
                        ID          INTEGER NOT NULL
                                    DEFAULT nextval ('sequence.Employee_Sequence'),
                        NAME        VARCHAR,
                        SALARY      BIGINT
                        );

ALTER TABLE sequence.EMPLOYEE
    ADD CONSTRAINT pk_EMPLOYEE PRIMARY KEY (ID);

