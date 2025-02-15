Feature: Save Person

  Scenario: Save a new person with documents
    Given a valid person and a list of documents
    When the person is saved
    Then the person should be saved with encrypted password
