rem
    call %utilCore%\jEnv
rem
    set CLASSPATH=%CLASSPATH%;..\..\..\..\resources\company\manyToManyAttributed
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore  ^
                           company.manyToManyAttributed.domain.EmployeeProjectTest
