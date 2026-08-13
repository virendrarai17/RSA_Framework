@tag
Feature: Purchase the order from ecommerce website
  I want to use this template for my feature file

Background:
Given I landed on ecommerce website

  @SubmitOrderTest
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productname> to cart
    And checkout <productname> and submit the order
    Then I verify the "Thankyou for the order." confirmation message on confirmation page

    Examples: 
      | name  									| password 		| productname |
      | virendrarai17@gmail.com	| TimeValue@22| ZARA COAT 3	|	
