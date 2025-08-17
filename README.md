
# Commands

## TDD
mvn -Dtest="**/tdd/*Test.java" test

## BDD
mvn -Dtest=CucumberTest test

# 1. Test-Driven Development (TDD) & Behavior-Driven Development (BDD)

For TDD there is an intentional bug in UserService.java file
        so when I run TDD test (UserServiceTest.java), it will fail (RED)
        then when I comment out that buggy code and un-comment correct code (Refactor) and re-run test, it will pass (Green)