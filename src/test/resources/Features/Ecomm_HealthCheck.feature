@healthCheck
Feature: E-commerce website healthCheck

Background: Navigate to the URL
Given User navigate to url and open the landing page 

##------------------------- validation of base URL ------------------##
@URLRedirection
Scenario: User naviaget to URL, User redirect to landing page with expected URL
When User is on landing page
Then Validate current URL of landing page with expected URL is "https://www.candere.com/"

##---------------------------Scenario 1---------------------------##
@LandingPageTitle
Scenario: User navigate to landing page with expected page title
When User is on landing page
Then Validate title of landing page with expected title as "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"

##---------------------------Scenario 2---------------------------##
@SearchProduct
Scenario: User is able to open the application and able to performe search for majestic solitaire diamond ring
When User search for product "majestic solitaire diamond ring"
Then Validate search result is displayed as "Majestic Solitaire Diamond Ring" 

##---------------------------Scenario 3---------------------------##
@SearchAndClickOnProduct
Scenario: User is able to performe search for majestic solitaire diamond ring and click on it
When User search for product "majestic solitaire diamond ring"
Then Validate search result is displayed as "Majestic Solitaire Diamond Ring" 
And Click on "Majestic Solitaire Diamond Ring" 
And User is able to visit to next page and after clicking expected title of the page as "Majestic Solitaire Diamond Ring"
And User is able to select ring size from drop down
And Validate notification text as "Price updated"

##---------------------------Scenario 4---------------------------##
@AboutUsFooterLinks
Scenario: Validate About Us all options on landing page of application
When User scroll down to bottom of landing page until About Us visible
And User is able to see 6 options of About Us category  
Then Under About Us category below options are visible

| About Our Company   |
| Terms and Conditions|
| Privacy Policy      |
| FAQ                 |
| Offers T&Cs         |
| Customer Reviews    |

##---------------------------Scenario 5---------------------------##
@FollowUsFooterSection
Scenario Outline: Validate the redirection for social media handles which are on FOLLOW US footer section
When User scroll down to bottom of landing page until FOLLOW US visible
And User click on "<socialMedia_name>"
Then Browser opened with "<socialMedia_name>"
And  URL should contain "<URLContain>"
And User able to see text as "<presentText>" on perticular "<socialMedia_name>" page

 Examples:
|socialMedia_name| URLContain     |    presentText             |
|  facebook      |canderejewellery| Candere by Kalyan Jewellers|
|  instagram     |canderejewellery| canderejewellery           |
|   twitter      |canderebyKalyan | Candere By Kalyan Jewellers|








