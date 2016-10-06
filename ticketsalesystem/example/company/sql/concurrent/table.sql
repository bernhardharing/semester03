-- company\sql\concurrent

CREATE TABLE concurrent.EMPLOYEE (
    ID          INTEGER NOT NULL PRIMARY KEY,
    FIRSTNAME   VARCHAR,
    LASTNAME    VARCHAR,
    SALARY      BIGINT,
    VERSION     INTEGER  DEFAULT 0    -- counterpart for @Version int version
);

