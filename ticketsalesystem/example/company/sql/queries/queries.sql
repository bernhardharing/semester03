-- company\sql\queries

-- example for left outer join

SELECT e.FIRSTNAME, d.NAME
FROM   EMPLOYEE e LEFT OUTER JOIN DEPARTMENT d
                  ON   e.DEPARTMENT_ID = d.ID;

