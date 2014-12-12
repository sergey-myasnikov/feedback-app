Feature: Navigate to Feedback page
  As a user
  I want to navigate to feedback page and post feedback
  so that I can see confirmation page

  Scenario: Open feedback page http://fbgd.herokuapp.com/feedback
    When I open "http://fbgd.herokuapp.com/feedback"
    Then a browser title should be "Post a Feedback - Feedback App"
    Then a page header should be "Post a Feedback"
     
  Scenario: Navigate to feedback page from main page
  	Given the page is open "http://fbgd.herokuapp.com"
    When I click "Post a Feedback" button
    Then a browser title should be "Post a Feedback - Feedback App"
    Then a page header should be "Post a Feedback"
    
  Scenario: Post feedback
  	Given the page is open "http://fbgd.herokuapp.com/feedback"
    When I enter name "Test User"
     And I enter e-mail "test.user@mail.com"
     And I enter feedback "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
