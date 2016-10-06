-- how 'busy' are the employees ?

SELECT    junction.EMPLOYEE_ID, SUM (junction.TIME_PERCENT) AS busy
FROM      manyToMany.EMPLOYEE_PROJECT  junction
GROUP BY  junction.EMPLOYEE_ID
ORDER BY  busy;

-- same query, except that the result doesnt contain the "busy" column

SELECT    junction.EMPLOYEE_ID
FROM      manyToMany.EMPLOYEE_PROJECT  junction
GROUP BY  junction.EMPLOYEE_ID
ORDER BY  SUM (junction.TIME_PERCENT);

-- now join with employee info

SELECT emp.FIRSTNAME
 FROM  manyToMany.EMPLOYEE emp,
       (SELECT    junction.EMPLOYEE_ID  AS EMPLOYEE_ID
        FROM      manyToMany.EMPLOYEE_PROJECT     junction
        GROUP BY  junction.EMPLOYEE_ID
        ORDER BY  SUM (junction.TIME_PERCENT))    workload
 WHERE emp.id = workload.EMPLOYEE_ID;
