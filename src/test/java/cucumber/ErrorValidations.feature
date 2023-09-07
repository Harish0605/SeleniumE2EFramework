@ErrorValidations
Feature: 
  I want to validate the errors upon login to Portal
 
  @ErrorValidation
  Scenario Outline: Negative test case for login
  Given I landed on Ecommerce page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username                 |     password    |
      | harishbanavatu@gmail.com |     Harish@0603 |
      | anshika@gmail.com        |     Iamking@001 |
