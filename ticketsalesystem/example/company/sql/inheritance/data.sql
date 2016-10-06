-- company\sql\inheritance

INSERT INTO inheritance.EMPLOYEE (FIRSTNAME, LASTNAME, DTYPE)
            VALUES ('Joan',      'Oak',  'PartTimeEmployee' );
INSERT INTO inheritance.PART_TIME_EMPLOYEE (ID, HOURLYRATE)
            VALUES ( currval ('inheritance.Employee_Sequence'), 7.77 );


INSERT INTO inheritance.EMPLOYEE (FIRSTNAME, LASTNAME, DTYPE)
            VALUES ('John',      'Pot',  'FullTimeEmployee' );
INSERT INTO inheritance.FULL_TIME_EMPLOYEE (ID, SALARY)
            VALUES ( currval ('inheritance.Employee_Sequence'), 3210 );

