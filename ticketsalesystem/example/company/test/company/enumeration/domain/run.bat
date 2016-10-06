rem
    call %utilCore%\jEnv
rem
    set CLASSPATH=%CLASSPATH%;..\..\..\..\resources\company\enumeration
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore  ^
                           company.enumeration.domain.EmployeeTest

