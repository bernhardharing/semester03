rem
    call %utilCore%\jEnv
rem
    set CLASSPATH=%CLASSPATH%;..\..\..\..\resources\company\inheritance
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore  ^
                           company.inheritance.domain.EmployeeTest
