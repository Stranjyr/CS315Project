Use Cases
---------

1. Validate (Joe 
2. Bill for service (Joe
3. Request Directory (Joe
4. Member Report (Ben
5. Provider Report (Ben
6. EFT (Ben
7. Summery Report (Ben
8. Accounting Procedure report Generator (Austin, HTML Report)
9. Add User (Justin
10. edit User (Justin
11. Delete User (Justin
12. Add provider (William
13. Edit Provider (William
14. Delete provider (William


##Validate##
A. Actors
	1. Provider
B. Steps
	1. Provider swipes card
	1.a Ok Keys in number
	2. Software contacts database
	3. database validates number
	4. Terminal displays "Validated"
	4.a If failed, display error
	4.a.1 Invalid
	4.a.2 Member Suspended

	
##Billing##
A. Actors
	1. Provider
B. Steps
	1. Use Validate Method
	2. Enter date of service
	3. enter Service code
	4. Software display service name
	4.a If invalid code, display error
	5. Ask for conformation
	5.a If not right service, go to step 3
	5.b Can add comments here
	6. Write record to disk (See table A)
	7. Look up fee, and Display fee on terminal
	8. Prompt user to fill out paper form

##Request Directory##
A. Actors
	1. Provider
B. Steps
	1. Provider requests directory (Service Names, Codes, and Fees)
	2. Software creates directory as file
	2.a Ordered Alphabetically (Service Names)
	2. Software emails directory to Provider
	
##Member Report##
A. Actors
	1. Managers
	2. Accounting Procedure
	3. Customer
B. Steps
	1. Report Requested
		1.a. By manager
		1.b By Accounting
	2. Get Weeks file of services
	3. Aggregate Data from database using member number
	3.a(see table B)
	4. Save report to file
	5. Email it to customer
	5.a or manager (on request)
	
##Provider Report##
A. Actors
	1. Provider
	2. Accounting Procedure
	3. Manager
		
B. Steps
	1. Report requested
		1.a. By provider
		1.b. By manager
		1.c By accounting
	2. Get Weeks file of services
	3. Aggregate Data from database using provider number
	3.a(see table C)
	4. Save report to file
	5. Email it to Provider
	5.a or manager (on request)

##EFT##
A. Actors
	1. Banking Computer
	2. Accounting Procedure
B. Steps
	1. EFT requested by Accounting Procedure
	2. Aggregates Provider name, number, and total weekly fee
	3. Saves as file

##Accounting Summery Report##
A. Actors
	1. Accounting Procedure 
	2. Accounts Payable Manager
	3. Chocoholics Manager
B. Steps
	1. report requested
	2. Saves Summery Data to file
	2.a See table D
	3. Email file to Manager for accounts payable
	3.a or chocoholics manager (at request)
	
##Friday Night report##
A. Actors
	1. Accounting Procedure 
	2. Accounts Payable Manager
	3. Customers
B. Steps
	1. Started by Accounting Procedure Friday at midnight
	2. Member report for every member
	3. Provider report for every provider
	4. EFT
	5. Summery report for Accounts payable manager
	
#Interactive Mode#
##Adding Member##
A. Actor
	1. Data centrer operators
B. Steps
	1. Operator Requests add new member
	2. Operator fills out table 
	2.a See Table E
	3. Software validates data
	3.a If error, return to step 2
	4. Member number generated
	5. Conformation Screen
	5.a Operator clicks ok
	5.b Operator clicks cancel, return to step 2
	6. Save to database
	
##Edit Member##
A. Actor
	1. Data centrer operators
B. Steps
	1. Operator Selects member(s) on table
	1.a Or searched member number/name
	2. Operator Clicks edit
	2.a Operator taken to edit screen
	3. Operator changes any member fields
	4. Software validates data
	4.a If error, return to step 3
	5. Conformation Screen
	5.a Operator clicks ok
	5.b Operator clicks cancel, return to step 3
	6. Save to database
	

##Delete Member##
A. Actor
	1. Data centrer operators
B. Steps
	1. Operator Selects member(s) on table
	1.a Or searched member number/name
	2. operators clicks delete
	3. Conformation Screen
	3.a Operator clicks ok
	3.b Operator clicks cancel, return to step 1
	4. Save to database
	
	
##Adding Provider##
A. Actor
	1. Data centrer operators
B. Steps
	1. Operator Requests add new provider
	2. Operator fills out table 
	2.a See Table F
	3. Software validates data
	3.a If error, return to step 2
	4. Provider number generated
	5. Conformation Screen
	5.a Operator clicks ok
	5.b Operator clicks cancel, return to step 2
	6. Save to database
	
##Edit Provider##
A. Actor
	1. Data centrer operators
B. Steps
	1. Operator Selects providers(s) on table
	1.a Or searched provider number/name
	2. Operator Clicks edit
	2.a Operator taken to edit screen
	3. Operator changes any provider fields
	4. Software validates data
	4.a If error, return to step 3
	5. Conformation Screen
	5.a Operator clicks ok
	5.b Operator clicks cancel, return to step 3
	6. Save to database
	

##Delete provider##
A. Actor
	1. Data centrer operators
B. Steps
	1. Operator Selects providers(s) on table
	1.a Or searched provider number/name
	2. operators clicks delete
	3. Conformation Screen
	3.a Operator clicks ok
	3.b Operator clicks cancel, return to step 1
	4. Save to database
	
#Appendix#
##table A##
Current date and time (MM–DD–YYYY HH:MM:SS).
Date service was provided (MM–DD–YYYY).
Provider number (9 digits).
Member number (9 digits).
Service code (6 digits).
Comments (100 characters) (optional).

##Table B##
Member name (25 characters).
Member number (9 digits).
Member street address (25 characters).
Member city (14 characters).
Member state (2 letters).
Member ZIP code (5 digits).
For each service provided, the following details are required:
Date of service (MM–DD–YYYY).
Provider name (25 characters).
Service name (20 characters).

##Table C##
Provider name (25 characters).
Provider number (9 digits).
Provider street address (25 characters).
Provider city (14 characters).
Provider state (2 letters).
Provider ZIP code (5 digits).
For each service provided, the following details are required:
Date of service (MM–DD–YYYY).
Date and time data were received by the computer (MM–DD–YYYY HH:MM:SS).
Member name (25 characters).
Member number (9 digits).
Service code (6 digits).
Fee to be paid (up to $999.99).
Total number of consultations with members (3 digits).
Total fee for week (up to $99,999.99).

##Table D##
Each provider to be paid that week
the number of consultations each provider had
and his or her total fee for that week
the total number of providers who provided services 
the total number of consultations 
the overall fee total

##Table E##
Member name (25 characters).
Member street address (25 characters).
Member city (14 characters).
Member state (2 letters).
Member ZIP code (5 digits).

##Table F##
Provider name (25 characters).
Provider street address (25 characters).
Provider city (14 characters).
Provider state (2 letters).
Provider ZIP code (5 digits).