
-- Narrow rights for ALL USERS (PUBLIC)  ------------------------------

REVOKE CREATE /* SCHEMA*/  ON DATABASE company  FROM PUBLIC /* e.g. all users */;
REVOKE CREATE /* OBJECTS*/ ON SCHEMA   public   FROM PUBLIC /* e.g. all users */;

-- Reader   -----------------------------------------------------------

CREATE USER homer WITH PASSWORD  'donut';
CREATE USER larry WITH PASSWORD  'donut';

GRANT  READER_ROLE TO homer;
GRANT  READER_ROLE TO larry;

-- WRITER   -------------------------------------------------------

CREATE USER smithers WITH PASSWORD  'burns';

GRANT       WRITER_ROLE     TO smithers;

-- Admin    -----------------------------------------------------------

CREATE USER burns WITH PASSWORD  'smithers';

GRANT       ADMIN_ROLE        TO burns;
