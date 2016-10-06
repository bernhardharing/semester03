rem
    call %utilCore%\jEnv
rem
    set CLASSPATH=%CLASSPATH%;..\..\..\..\resources\company\sequence
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore  ^
                           company.sequence.domain.EmployeeTest
