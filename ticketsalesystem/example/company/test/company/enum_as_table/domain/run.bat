rem
    call %utilCore%\jEnv
rem
    set CLASSPATH=%CLASSPATH%;..\..\..\..\resources\company\enum_as_table
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore  ^
                           company.enum_as_table.domain.EmployeeTest

