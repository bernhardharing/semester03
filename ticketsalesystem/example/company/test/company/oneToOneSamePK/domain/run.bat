rem
    call %utilCore%\jEnv
rem
    set CLASSPATH=%CLASSPATH%;..\..\..\..\resources\company\oneToOneSamePK
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore  ^
                           company.oneToOneSamePK.domain.EmployeeNonDisclosureTest
