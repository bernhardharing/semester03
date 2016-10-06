-- @from :  wiki.postgresql.org/wiki/Audit_trigger
--
-- add the trigger to our table(s) worth auditing :
   CREATE TRIGGER employee_audit
   AFTER INSERT OR UPDATE OR DELETE ON employee
   FOR EACH ROW EXECUTE PROCEDURE audit.if_modified_func();

   CREATE TRIGGER full_time_employee_audit
   AFTER INSERT OR UPDATE OR DELETE ON full_time_employee
   FOR EACH ROW EXECUTE PROCEDURE audit.if_modified_func();

