Feature: My Feature

  Scenario Outline: Search for Ben
    Given Request 3 users from randomuser.me as "random_group"
    Given I pick name <celebrityName> as "some alias"
    Given Load google page
    Given Accept Google cookies if present
    When I google for "some alias"
    Then I can see 3 search results for name "some alias"
    Examples:
      | celebrityName   |
      | "random_name"   |
      | "Ben Affleck"   |
      | "random_name"   |
      | "Margot Robbie" |
      | "random_name"   |