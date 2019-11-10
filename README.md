# mockito-playground
This is a playground project to demonstate Mockito testing framework usage with JUnit4.

## Why Mockito doesn't mock private methods?
https://github.com/mockito/mockito/wiki/FAQ#can-i-mock-private-methods

## Why Mockito doesn't mock static methods?
https://github.com/mockito/mockito/wiki/FAQ#can-i-mock-static-methods 

## Which one should I use: mocks or spies?
Prefer mocks overs spies to keep things simple. Spies work partially as the real object and partially have the mocked/overriden behavior and may complicate things. So avoid them, if not necessary to use them.

## General best practices on unit tests
1. Readability/Clarity
- naming example: <method_name>Should<expected_result>When<condition_under_test>
- content or test input values: extract anything common to helper methods and supply to the test directly only the specific ones
- BDD style can help in organizing the test: given - when - then  
2. Tests should fail only when there are business logic failures
- the test scenarios should isolated from each other
- test scenarios order of execution should not matter
- data dependencies should not exist

## Sources
- Concepts demonstrated here were inspired by Ranga's Rao Karanam repository on testing and best practices: 
https://github.com/in28minutes/MockitoTutorialForBeginners
- https://github.com/ghsukumar/SFDC_Best_Practices/wiki/F.I.R.S.T-Principles-of-Unit-Testing
- https://github.com/mockito/mockito/wiki/How-to-write-good-tests
