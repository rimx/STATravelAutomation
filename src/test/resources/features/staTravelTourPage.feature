@StaTourPageFeature
Feature: StsTravel Tour Page Test

  Scenario Outline: Find a tour on Sta Travel tour page
    Given launch browser '<browserName>'
    When I am on "http://www.statravel.co.uk/tours-worldwide.htm"
    And I select country '<country>' from the AJAX
    And I click on Find a Tour button
    And I change the sort order on the search results to '<sortBy>'
    And I click on View More button on the page to display more or all results
    Then I verify if the <matchFound> matches found text is equal to the actual number of results displayed

    Examples: 
      | browserName | country | sortBy           | matchFound |
      | firefox      | India   | Price (Low-High) |         66 |
