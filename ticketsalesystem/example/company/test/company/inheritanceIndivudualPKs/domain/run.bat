rem
    call %utilCore%\jEnv
rem
    set CLASSPATH=%CLASSPATH%;..\..\..\..\resources\company\inheritanceIndivudualPKs
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore  ^
                           company.inheritanceIndivudualPKs.domain.EmployeeTest
