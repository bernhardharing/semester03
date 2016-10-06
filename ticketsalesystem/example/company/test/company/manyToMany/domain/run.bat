rem
    call %utilCore%\jEnv
rem
    set CLASSPATH=%CLASSPATH%;..\..\..\..\resources\company\manyToMany
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore  ^
                           company.manyToMany.domain.EmployeeProjectTest
