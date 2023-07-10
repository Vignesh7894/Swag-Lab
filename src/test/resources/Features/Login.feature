Feature: Validate a Swaglabs login

  Background: 
    Given User must be in login page
    When User must login with valid username and password
      | standard_user | secret_sauce |
    And User must click on login button

  Scenario: Validate with valid credentials
    Then Verify user reddirected to home page

  #To validate list of products
  Scenario: To validate list of products
    And check all the products list
    Then check total products are matching with expected

  #Test the addition of products from the shopping cart
  @cart
  Scenario: Validate the addition of products and verify Cart
    When user select product to cart
      | Sauce Labs Bike Light    |
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |
    And user open cart page
    Then verify total no of products in cart

  #Test the sum of products from the shopping cart
  @cart
  Scenario: Validate the addition of products and verify Cart
    When user select product to cart
      | Sauce Labs Bike Light    |
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |
    And user open cart page
    

  @checkout
  Scenario Outline: validate your information page
    When user select product to cart
      | Sauce Labs Bike Light    |
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |
    And user open cart page
    When user checkout the cart
    And user enter "<firstname>","<lastname>","<postal>"
    And user click on continue
    Then verify sum of price in checkout
    And user click on fnish
    Then verify for order confirmation

    Examples: 
      | firstname | lastname | postal |
      | test      | user     | 600001 |
