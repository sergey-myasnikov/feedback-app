Feature: Open home page
  As a user
  I want to open home page
  so that I can see welcome message and page title
 
  Scenario: Open home page http://fbgd.herokuapp.com
    When I open "http://fbgd.herokuapp.com"
    Then a browser URL should be "http://fbgd.herokuapp.com/main"
    Then a browser title should contain "Home - Feedback App"
    Then a page should contain header "Welcome to Feedback App"
 
  Scenario: Open home page alias http://fbgd.herokuapp.com/home
    When I open "http://fbgd.herokuapp.com/home"
    Then a browser URL should be "http://fbgd.herokuapp.com/main"
    Then a browser title should contain "Home - Feedback App"
    Then a page should contain header "Welcome to Feedback App"
    
  Scenario: Open home page alias http://fbgd.herokuapp.com/main
    When I open "http://fbgd.herokuapp.com/main"
    Then a browser URL should be "http://fbgd.herokuapp.com/main"
    Then a browser title should contain "Home - Feedback App"
    Then a page should contain header "Welcome to Feedback App"
    
  Scenario: Open home page alias http://fbgd.herokuapp.com/index
    When I open "http://fbgd.herokuapp.com/index"
    Then a browser URL should be "http://fbgd.herokuapp.com/main"
    Then a browser title should contain "Home - Feedback App"
    Then a page should contain header "Welcome to Feedback App"