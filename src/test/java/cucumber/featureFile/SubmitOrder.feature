@tag
Feature: Purchase the order from Ecomerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecomerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with <username> and <password>
    When I add <productName> from Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | username  									| password 				|	productName |
      | contact.sonali@gmail.com 		| sonaliDemo123 	|	ZARA COAT 3 |
      
