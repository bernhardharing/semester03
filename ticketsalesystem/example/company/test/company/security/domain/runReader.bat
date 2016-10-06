rem
    call %utilCore%\jEnv
rem
rem
    call class-path-extender.bat
rem
    %JDKPath%\bin\java.exe org.junit.runner.JUnitCore  ^
                           company.security.domain.CompanyReaderTest
