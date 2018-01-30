@Smoke
Feature: To smoke test functionalities of app

  Scenario: 
    Given User validates the splash screen open for five sec
    Then User validates "List" screen
    And click on any data
    And User validates "Details" screen
    And Image should load with detail description available on details screen
    And User click on back button
    And user Scroll down & click on last item of list
