Feature: My Feature

  Background: do before each
    Given I print "======================================" 1 times

  Scenario: Some Test Scenario
    Given I print "my test string" 1 times
    When I use different test data "some test data"
    Then I print another test data piece "AAAA"

  Scenario Outline: Some Test Scenario
    Given I print "my test string" 1 times
    When I use different test data <test_data>
    Then I print another test data piece <another_test_data>
    Examples:
      | test_data    | another_test_data     |
      | "test run 1" | "another test data 1" |
      | "test run 2" | "another test data 2" |
      | "test run 3" | "another test data 3" |