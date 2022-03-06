Feature: LogIn Page
Description: Test the logIn functionality for orangeHRM website

Background: to launch the chrome browser
			#Given Open Browser

			
Scenario: LogIn Page with correct User
		Given user in logIn Page
		And username is 'Admin' and password is 'U@qBLVtm09'
		When user click on login button
		Then user should navigate to 'dashboard'
		

		
Scenario: LogIn Page with inorrect User
		Given user in logIn Page
		And username is 'Admin' and password is '1234'
		When user click on login button
		Then user should navigate to 'retryLogin'