@Regression
Feature: Purchase the Order from Ecom website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page

  @PurchaseOrder
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <username> and password <password>
    When I add the product <product> to the Cart
    And Checkout <product> and submit the order
    Then I verify "THANKYOU FOR THE ORDER." message is displayed on the confirmation page

    Examples: 
      | username                 |     password    | product         |
      | harishbanavatu@gmail.com |     Harish@0605 | ADIDAS ORIGINAL |
      | anshika@gmail.com        |     Iamking@000 | ZARA COAT 3     |
