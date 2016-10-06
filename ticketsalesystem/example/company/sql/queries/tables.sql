-- company\sql\queries

-- Department   -------------------------------------------------------


CREATE TABLE DEPARTMENT (
                         ID             INTEGER  NOT NULL
                                        DEFAULT nextval ('Department_Sequence'),
                         NAME           VARCHAR
                         );

ALTER TABLE DEPARTMENT
    ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY (ID);


-- ParkingSpace   -----------------------------------------------------

CREATE TABLE PARKING_SPACE (
                            ID          INTEGER  NOT NULL
                                        DEFAULT nextval ('ParkingSpace_Sequence'),
                            LOT         INTEGER,
                            LOCATION    VARCHAR
                            );

ALTER TABLE PARKING_SPACE
    ADD CONSTRAINT PK_PARKING_SPACE PRIMARY KEY (ID);

-- Employee   ---------------------------------------------------------

CREATE TABLE EMPLOYEE (
                        ID                  INTEGER NOT NULL
                                            DEFAULT nextval ('Employee_Sequence'),
                        DTYPE               VARCHAR,
                        FIRSTNAME           VARCHAR NOT NULL,
                        LASTNAME            VARCHAR NOT NULL,

                        DEPARTMENT_ID       INTEGER,
                        PARKING_SPACE_ID    INTEGER UNIQUE
                       );

ALTER TABLE EMPLOYEE
      ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY (ID);

ALTER TABLE EMPLOYEE
    ADD CONSTRAINT FK_DEPARTMENT    FOREIGN KEY (DEPARTMENT_ID)
                                    REFERENCES   DEPARTMENT;

ALTER TABLE EMPLOYEE
    ADD CONSTRAINT FK_PARKING_SPACE  FOREIGN KEY (PARKING_SPACE_ID)
                                     REFERENCES   PARKING_SPACE;

CREATE TABLE FULL_TIME_EMPLOYEE (
    ID          INTEGER NOT NULL,
    SALARY      BIGINT
);

ALTER TABLE FULL_TIME_EMPLOYEE
      ADD CONSTRAINT PK_FULL_TIME_EMPLOYEE PRIMARY KEY (ID);


CREATE TABLE PART_TIME_EMPLOYEE (
    ID          INTEGER NOT NULL,
    HOURLYRATE  DECIMAL
);

ALTER TABLE PART_TIME_EMPLOYEE
      ADD CONSTRAINT PK_PART_TIME_EMPLOYEE PRIMARY KEY (ID);

-- Project   ----------------------------------------------------------


CREATE TABLE PROJECT
                      ( ID              INTEGER NOT NULL
                                        DEFAULT nextval ('Project_Sequence'),
                        NAME            VARCHAR
                        );

ALTER TABLE PROJECT
      ADD CONSTRAINT PK_PROJECT PRIMARY KEY (ID);


-- Employee-Project Junction   ----------------------------------------


CREATE TABLE EMPLOYEE_PROJECT ( PROJECTS_ID  INTEGER NOT NULL,
                                        EMPLOYEES_ID INTEGER NOT NULL
                                      );


ALTER TABLE EMPLOYEE_PROJECT
    ADD CONSTRAINT PK_EMPLOYEE_PROJECT PRIMARY KEY ( PROJECTS_ID
                                                    ,EMPLOYEES_ID );

ALTER TABLE EMPLOYEE_PROJECT
    ADD CONSTRAINT FK_PROJECTS FOREIGN KEY (PROJECTS_ID)
    REFERENCES PROJECT;

ALTER TABLE EMPLOYEE_PROJECT
    ADD CONSTRAINT FK_EMPLOYEES FOREIGN KEY (EMPLOYEES_ID)
    REFERENCES EMPLOYEE;

