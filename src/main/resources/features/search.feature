@Proje1
Feature: Navigate to main page
  Search for şort and gömlek and write on txt file.

  Scenario: User should navigate to main page
    And Verify page loads
    And Enter Şort to the field
    And Delete the Şort word
    And Enter Gömlek to the field
    And Click on Enter Key
    And Choose randomly one product
    And Write on txt file some information about product
    And Send to cart this product
    And Verify the price on cart and on page
    And Increase the piece of the product
    And Verify the pieces as
    And Delete products on cart
    Then Verify that the cart is empty

