
# Commands

## TDD
mvn -Dtest="**/tdd/*Test.java" test

## BDD
mvn -Dtest=CucumberTest test

## Selenium
npm test -- --watchAll=false

## JMeter

powershell -NoLogo -NoProfile -ExecutionPolicy Bypass -File test\jmeter\run_load_test.ps1 -JMeterPath "C:\Tools\apache-jmeter-5.6.3" -Clean Cleaning old JMeter artifacts (test/jmeter/results_*.jtl, report_*/)...

# 1. Test-Driven Development (TDD) & Behavior-Driven Development (BDD)

For TDD there is an intentional bug in UserService.java file
        so when I run TDD test (UserServiceTest.java), it will fail (RED)
        then when I comment out that buggy code and un-comment correct code (Refactor) and re-run test, it will pass (Green)

# 2. Test Automation & Continuous Integration

## Selenium
run backend
run frontend
in frontend run "npm test -- --watchAll=false" to do Seleium Tests

## CI/CD

Just push something to github and check Actions tab 

# 3. Performance, Security, and Usability Testing

## Jmeter
run backend
run frontend

run 

powershell -NoLogo -NoProfile -ExecutionPolicy Bypass -File test\jmeter\run_load_test.ps1 -JMeterPath "C:\Tools\apache-jmeter-5.6.3" -Clean Cleaning old JMeter artifacts (test/jmeter/results_*.jtl, report_*/)...

in root
