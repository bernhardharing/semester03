rem
    call %utilCore%\jEnv
rem
    set CLASSPATH=%CLASSPATH%;..\..\..\..\resources\company\PKCompound
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore  ^
                           company.PKCompound.domain.EmployeeTest
