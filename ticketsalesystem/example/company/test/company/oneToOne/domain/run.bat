rem
    call %utilCore%\jEnv
rem
    set CLASSPATH=%CLASSPATH%;..\..\..\..\resources\company\oneToOne
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore  ^
                           company.oneToOne.domain.EmployeeParkingSpaceTest
