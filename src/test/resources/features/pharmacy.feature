Feature: Adding pharmacy to DB

  Scenario: A user add pharmacy
    Given I have a following value of pharmacy to store in DB
      | name                      | address                     | lat     | lng     |
      | Pharmacie du boulevard    | Boulevard Ghassimbe Eyadema |  10.2   |  12.3   |
    When I ask for pharmacies list
    Then I should have a success response
    And The following pharmacies list should be in the response content
      | id | name                   | address                     | lat  | lng  |
      | 10000   | Pharmacie du boulevard | Boulevard Ghassimbe Eyadema | 10.2 | 12.3 |