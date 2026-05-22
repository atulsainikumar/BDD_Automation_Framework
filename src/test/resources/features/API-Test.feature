@API
Feature: API test


  Scenario: Should see LIST USERS of all existing users
    Given I request the user list and note the total count
    When I collect users across all pages
    Then the number of collected user ids should equal the total count


  Scenario: Should see SINGLE USER data
    Given I make a search for user 3
    Then I should see the following user data
      | first_name | email               |
      | Emma       | emma.wong@reqres.in |


  Scenario: Should see SINGLE USER NOT FOUND error code
    Given I make a search for user 55
    Then I receive error code 404 in response


  Scenario Outline: CREATE a user
    Given I create a user with following <Name> <Job>
    Then response should contain the following data
      | name | job | id | createdAt |

    Examples:
      | Name  | Job     |
      | Peter | Manager |
      | Liza  | Sales   |


  Scenario: LOGIN - SUCCESSFUL by a user
    Given I send a login request with valid credentials
    Then I should get a response code of 200

  Scenario: LOGIN - UNSUCCESSFUL by a user
    Given I send a login request with missing password
    Then I should get a response code of 400
    And I should see the following response message:
      | "error": "Missing password" |

  Scenario: Should see the list of users with DELAYED RESPONSE
    Given I wait for the user list to load
    Then I should see that every user has a unique id
