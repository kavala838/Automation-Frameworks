#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Test LogIn Functionality
  I want to use this template for my feature file

Background: to launch the chrome browser
			Given Open Browser


 Scenario Outline: Login page with various credential
	Given user in logIn Page 
	And username is '<username>' and password is '<password>'
	When user click on login button
	Then user should navigate to '<page>'
	
	Examples:
			| username  | password | page        |
      | Admin     |U@qBLVtm09|dashboard    |
      | Ad        |     abcd |retryLogin   |
