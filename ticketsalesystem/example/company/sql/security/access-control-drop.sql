DROP VIEW  IF EXISTS testview;    --  created by USER burns in ...\test\company\security\CompanyAdminTest.java

DROP  USER homer;
DROP  USER larry;
DROP  USER smithers;
DROP  USER burns;

-- before dropping the roles we MUST drop privileges on owned tables :
DROP OWNED BY  READER_ROLE      CASCADE;
DROP OWNED BY  WRITER_ROLE      CASCADE;
DROP OWNED BY  ADMIN_ROLE       CASCADE;

DROP  ROLE READER_ROLE;
DROP  ROLE WRITER_ROLE;
DROP  ROLE ADMIN_ROLE;

