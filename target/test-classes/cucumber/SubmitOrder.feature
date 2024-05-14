@tag
Feature: Purchase the order from ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <Product> to cart
    And Checkout <Product> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  								 | password 					| Product  				|
      | siddharth684@gmail.com |     S!dpractice123 | ZARA COAT 3 		|
      #| siddharth685@gmail.com |     S!drahul123 		| ADIDAS ORIGINAL |
