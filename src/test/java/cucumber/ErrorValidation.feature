
@tag
Feature: Login failure error validation
  I want to use this template for my feature file

  @ErrorValidationTest
  Scenario Outline: Negative test of Error Validation
    Given I landed on ecommerce website
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." error message displayed

    Examples: 
      | name  									| password 		|
      | virendrarai17@gmail.com	| TimeValue@22342|
