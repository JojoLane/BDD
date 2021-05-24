Feature: TopUp Account
  This feature describes various scenarios for users adding funds to their revolut account(s)

  #As a user, I can topup my Revolut account using my debit card

  Scenario: Add money to Revolut account using debit card
    Given Danny has 10 euro in his euro Revolut account
    And Danny selects 100 euro as the topUp amount
    And Danny selects his DebitCard as his topUp method
    #And Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 110


  Scenario: Add money to Revolut account using debit card
    Given Danny has 20 euro in his euro Revolut account
    And Danny selects 200 euro as the topUp amount
    #And Danny selects his DebitCard as his topUp method
    And Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 220

  Scenario Outline: Add various amounts to Revolut account
    Given Danny has a starting balance of <startBalance>
    And Danny selects his DebitCard as his topUp method
    When Danny now tops up by <topUpAmount>
    Then The balance in his euro account should be <newBalance>
    Examples:
      | startBalance  | topUpAmount   | newBalance    |
      | 0             | 100.0         | 100.0         |
      | 14.0          | 20.0          | 34.0          |
      | 23.0          | 30.0          | 53.0          |


  Scenario Outline: Payment service rejects the request
    Given Danny has a starting balance of <startBalance>
    And Danny selects his DebitCard as his topUp method
    And Danny has insufficient amount in his DebitCard
    When Danny now tops up by <topUpAmount>
    Then The balance in his euro account should be <startBalance>
    Examples:
      | startBalance  | topUpAmount   |
      | 0             | 100.0         |
      | 14.0          | 20.0          |
      | 23.0          | 30.0          |

  Scenario Outline: Payment service accepts the request
    Given Danny has a starting balance of <startBalance>
    And Danny selects his DebitCard as his topUp method
    And Danny has sufficient amount in his DebitCard
    When Danny now tops up by <topUpAmount>
    Then The balance in his euro account should be <newBalance>
    Examples:
      | startBalance  | topUpAmount   | newBalance    |
      | 0             | 100.0         | 100.0         |
      | 14.0          | 20.0          | 34.0          |
      | 23.0          | 30.0          | 53.0          |

  Scenario: Danny wants to Convert EUR to USD
    Given Danny has 20 euro in his euro Revolut account
    When Danny exchanges 10 euro to USD with an exchange rate of 1.20
    Then The balance in his USD account should be 12 and his euro account should be 10.

  Scenario: Sending money to a friend using Revolut
    Given Danny has 20 euro in his euro Revolut account
    When Danny sends 10 euro to Sarah's account
    Then The new balance of his euro account should now be 10 and Sarah's account should have be 10.


