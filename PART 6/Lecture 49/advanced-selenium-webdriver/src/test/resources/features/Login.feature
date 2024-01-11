Feature: test login functionality

#  Background:
#    Given user is on login page "https://the-internet.herokuapp.com"

  @ValidCredentials
  Scenario: user is able to login with correct credentials
    Given user is on login page
    When user enters correct user name and password
    Then user is successfully logged in

  @ValidCredentials1
  Scenario: user is able to login with correct credentials1
    Given user is on login page1
    When user enters correct user name and password1
    Then user is successfully logged in1