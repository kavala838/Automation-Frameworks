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

Feature: Calculator Page
  I want to use this template for my feature file
Background: To launch the chrome Browser
		

@UITest
Scenario Outline: Calculator UI Test number button
    Given Calculator page is open
    Then Calcaultor has number '<number>'

		Examples:
			|number|
			|0     |
			|1     |
			|2     |
			|3     |
			|4     |
			|5     |
			|6     |
			|7     |			
			|8     |
			|9     |
    
 @UITest
  Scenario Outline: Calculator UI Test opearotors
    Given Calculator page is open
    Then Calcaultor has operator '<operator>'
    Examples:
			|operator|
			|+     |
			|-     |
			|*     |
			|%     |
 			|=     |
 
 @UITest
  Scenario: Calculator UI Test Display screen
    Given Calculator page is open
    Then Calcaultor has display field
 
 @UITest
  Scenario Outline: Calculator UI Test Button color
    Given Calculator page is open
    Then Calcaultor '<button>'  has color '<color>'
    Examples:
			|button| color           |
			|+     | rgb(0,0,0)      |
			|-     | rgb(0,0,0)      |
			|*     | rgb(0,0,255)    |
			|%     | rgb(0,0,255)		 |
  		|=     | rgb(255,0,0)		 |
  		
  		
 @Functional 		
  Scenario Outline: Calculator digit should come on display screen
    Given Calculator page is open
    When number button is clicked '<number>'
    Then display screen should have number '<number>'
    
    Examples:
			|number|
			|0     |
			|1     |
			|2     |
			|3     |
			|4     |
			|5     |
			|6     |
			|7     |			
			|8     |
			|9     |
			
	@Functional 		
  Scenario Outline: Calculator operators should come on display screen
    Given Calculator page is open
    When number button is clicked '<operator>'
    Then display screen should have number '<operator>'
    
    Examples:
			|operator|
			|+     |
			|-     |
			|*     |
			|%     |
		

	 @Functional 		
  Scenario Outline: Calculator Typing should come on display screen
    Given Calculator page is open
    When numbers button is clicked
    |4|
    |4|
    |+|
    |3|
    |5|
    |4|
    Then display screen should have number '44+354'

    
    
    
  @Functional 		
  Scenario Outline: Calculator Add functionality
    Given Calculator page is open
    When number  is typed '<number>'
    When Operator button is clicked '+'
    When number  is typed '<number1>'
    When Operator button is clicked '='
    Then display screen should have number '<result>'
    
    Examples:
			|number |number1	|  result |
			|2    	| 4 	  |   6     |
			
	@Functional 		
  Scenario Outline: Calculator subtract functionality
    Given Calculator page is open
    When number  is typed '<number>'
    When Operator button is clicked '-'
    When number  is typed '<number1>'
    When Operator button is clicked '='
    Then display screen should have number '<result>'
    
    Examples:
			|number |number1	|  result |
			|10    	| 4 	  |   6     |

			
	@Functional 		
  Scenario Outline: Calculator multiply functionality
    Given Calculator page is open
    When number  is typed '<number>'
    When Operator button is clicked '*'
    When number  is typed '<number1>'
    When Operator button is clicked '='
    Then display screen should have number '<result>'
    
    Examples:
			|number |number1	|  result |
			|4    	| 2 	  |   8     |
			
	@Functional 		
  Scenario Outline: Calculator divide functionality
    Given Calculator page is open
    When number  is typed '<number>'
    When Operator button is clicked '%'
    When number  is typed '<number1>'
    When Operator button is clicked '='
    Then display screen should have number '<result>'
    
    Examples:
			|number |number1 |  result |
			|4    	| 2 	  |   2     |