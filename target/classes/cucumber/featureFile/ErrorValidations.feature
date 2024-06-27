
@tag
Feature: Error Validations
  I want to use this template for my feature file

  @ErrorValidations
  Scenario Outline: Error Validations Check
    Given I landed on Ecomerce Page
    When Logged in with <username> and <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username  									| password 				|
      | contact.sonali@gmail.com 		| sonaliDemo 			|
      
