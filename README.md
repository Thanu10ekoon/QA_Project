
# Commands

## TDD
mvn -Dtest="**/tdd/*Test.java" test

## BDD
mvn -Dtest=CucumberTest test

## Selenium
npm test -- --watchAll=false

# 1. Test-Driven Development (TDD) & Behavior-Driven Development (BDD)

For TDD there is an intentional bug in UserService.java file
        so when I run TDD test (UserServiceTest.java), it will fail (RED)
        then when I comment out that buggy code and un-comment correct code (Refactor) and re-run test, it will pass (Green)

# 2.

run backend
run frontend
in frontend run "npm test -- --watchAll=false" to do Seleium Tests