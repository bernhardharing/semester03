-- company\manyToMany\sql

CREATE TABLE manyToMany.EMPLOYEE (
                        ID              INTEGER NOT NULL
                                        DEFAULT nextval ('manyToMany.Employee_Sequence'),
                        FIRSTNAME       VARCHAR,
                        LASTNAME        VARCHAR
                        );

ALTER TABLE manyToMany.EMPLOYEE
      ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY (ID);

CREATE TABLE manyToMany.PROJECT  (
                        ID              INTEGER NOT NULL
                                        DEFAULT nextval ('manyToMany.Project_Sequence'),
                        NAME            VARCHAR
                        );

ALTER TABLE manyToMany.PROJECT
      ADD CONSTRAINT PK_PROJECT PRIMARY KEY (ID);


CREATE TABLE manyToMany.EMPLOYEE_PROJECT (
                                PROJECT_ID  INTEGER NOT NULL,
                                EMPLOYEE_ID INTEGER NOT NULL
                              );


ALTER TABLE manyToMany.EMPLOYEE_PROJECT
    ADD CONSTRAINT PK_EMPLOYEE_PROJECT PRIMARY KEY ( PROJECT_ID
                                                    ,EMPLOYEE_ID );

ALTER TABLE manyToMany.EMPLOYEE_PROJECT
    ADD CONSTRAINT FK_PROJECTS FOREIGN KEY (PROJECT_ID)
    REFERENCES manyToMany.PROJECT;

ALTER TABLE manyToMany.EMPLOYEE_PROJECT
    ADD CONSTRAINT FK_EMPLOYEES FOREIGN KEY (EMPLOYEE_ID)
    REFERENCES manyToMany.EMPLOYEE;

