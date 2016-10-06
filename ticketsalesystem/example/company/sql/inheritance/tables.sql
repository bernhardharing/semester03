-- company\inheritance\sql

CREATE TABLE inheritance.EMPLOYEE (
    ID          INTEGER  NOT NULL
                DEFAULT nextval ('inheritance.Employee_Sequence'),
    DTYPE       VARCHAR,      -- discriminator column - required by eclipseLink :-((
    FIRSTNAME   VARCHAR,
    LASTNAME    VARCHAR
);

ALTER TABLE inheritance.EMPLOYEE
      ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY (ID);


CREATE TABLE inheritance.FULL_TIME_EMPLOYEE (
    ID          INTEGER NOT NULL,
    SALARY      BIGINT
);

ALTER TABLE inheritance.FULL_TIME_EMPLOYEE
      ADD CONSTRAINT PK_FULL_TIME_EMPLOYEE PRIMARY KEY (ID);


CREATE TABLE inheritance.PART_TIME_EMPLOYEE (
    ID          INTEGER NOT NULL,
    HOURLYRATE  DECIMAL
);

ALTER TABLE inheritance.PART_TIME_EMPLOYEE
      ADD CONSTRAINT PK_PART_TIME_EMPLOYEE PRIMARY KEY (ID);

