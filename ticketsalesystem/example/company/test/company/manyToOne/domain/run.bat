rem
    call %utilCore%\jEnv
rem
    set CLASSPATH=%CLASSPATH%;..\..\..\..\resources\company\manyToOne
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore ^
                           company.manyToOne.domain.EmployeeDepartmentTest
