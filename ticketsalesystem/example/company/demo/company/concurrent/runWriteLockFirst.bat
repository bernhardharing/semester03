rem
    call %utilCore%\jEnv
rem
    call class-path-extender.bat
rem
    %JDKPath%\bin\java.exe company.concurrent.EmployeeWriteLockDemo FIRST
