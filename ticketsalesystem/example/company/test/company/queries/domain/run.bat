rem
    call %utilCore%\jEnv
rem
    set CLASSPATH=%CLASSPATH%;..\..\..\..\resources\company\queries
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore ^
                           company.queries.domain.CompanyTest
