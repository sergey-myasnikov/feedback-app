feedback-app
============

Feedback App is a simple web application designed to try out various automated testing stuff like Selenium frameworks and API testing tools.

## Application description

Consists of 5 pages:

Page | URL
------------- | -------------
Welcome page | /, /main, /index, /home
Feedback form | /feedback
Confirmation page | redirection from Feedback form
Admin page with a list of Feedbacks | /admin
Login page | /login

Since it is a testing "sandbox" all Feedbacks are stored in memory (no DB layer) and will disappear after application restart.

## API description:


## How to use?

To run application locally use Maven command ```mvn spring-boot:run```

Application also deployed to Heroku: http://fbgd.herokuapp.com




