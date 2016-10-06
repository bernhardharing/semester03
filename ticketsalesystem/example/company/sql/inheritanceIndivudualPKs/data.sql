-- company\inheritanceIndivudualPKs\sql

INSERT INTO inheritanceIndivudualPKs.PART_TIME_EMPLOYEE ( HOURLYRATE )
                                                 VALUES ( 7.77 );

INSERT INTO inheritanceIndivudualPKs.EMPLOYEE (ID, FIRSTNAME, LASTNAME, DTYPE)
            VALUES ( currval ('inheritanceIndivudualPKs.PartTimeEmployee_Sequence'),
                    'Joan', 'Oak',  'PartTimeEmployee' );


INSERT INTO inheritanceIndivudualPKs.FULL_TIME_EMPLOYEE ( SALARY )
                                                 VALUES ( 3210 );

INSERT INTO inheritanceIndivudualPKs.EMPLOYEE (ID, FIRSTNAME, LASTNAME, DTYPE)
            VALUES ( currval ('inheritanceIndivudualPKs.FullTimeEmployee_Sequence'),
                    'John',      'Pot',  'FullTimeEmployee' );


