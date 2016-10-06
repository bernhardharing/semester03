-- company\inheritanceIndivudualPKs\sql

CREATE TABLE inheritanceIndivudualPKs.EMPLOYEE (
    ID          INTEGER  NOT NULL,
    DTYPE       VARCHAR,      -- discriminator column - required by eclipseLink :-((
    FIRSTNAME   VARCHAR,
    LASTNAME    VARCHAR
);

ALTER TABLE inheritanceIndivudualPKs.EMPLOYEE
      ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY (ID);


CREATE TABLE inheritanceIndivudualPKs.FULL_TIME_EMPLOYEE (
    ID          INTEGER NOT NULL
                DEFAULT nextval ('inheritanceIndivudualPKs.FullTimeEmployee_Sequence'),
    SALARY      BIGINT
);

ALTER TABLE inheritanceIndivudualPKs.FULL_TIME_EMPLOYEE
      ADD CONSTRAINT PK_FULL_TIME_EMPLOYEE PRIMARY KEY (ID);


CREATE TABLE inheritanceIndivudualPKs.PART_TIME_EMPLOYEE (
    ID          INTEGER NOT NULL
                DEFAULT nextval ('inheritanceIndivudualPKs.PartTimeEmployee_Sequence'),
    HOURLYRATE  DECIMAL
);

ALTER TABLE inheritanceIndivudualPKs.PART_TIME_EMPLOYEE
      ADD CONSTRAINT PK_PART_TIME_EMPLOYEE PRIMARY KEY (ID);

