
-- Reader   -----------------------------------------------------------

CREATE ROLE READER_ROLE;

GRANT  SELECT ON ALL TABLES    IN SCHEMA public TO  READER_ROLE;

-- WRITER   -------------------------------------------------------

CREATE ROLE WRITER_ROLE;

GRANT  READER_ROLE TO WRITER_ROLE;

GRANT  INSERT, UPDATE ON ALL TABLES    IN SCHEMA public TO   WRITER_ROLE;
GRANT  SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA public TO   WRITER_ROLE;

-- Admin    -----------------------------------------------------------

CREATE ROLE ADMIN_ROLE;

GRANT  WRITER_ROLE TO ADMIN_ROLE;

GRANT  CREATE ON SCHEMA public TO ADMIN_ROLE;

GRANT  DELETE         ON ALL TABLES    IN SCHEMA public TO   ADMIN_ROLE;
