rem
    call %utilCore%\jEnv
rem
    set CLASSPATH=%CLASSPATH%;..\..\..\..\resources\company\simplest
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore  ^
                           company.simplest.domain.EmployeeTest
