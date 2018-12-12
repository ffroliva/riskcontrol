# language: en

# encoding: UTF-8
Feature: Word replacement from a given file

  Scenario: Check word1 is replaced by word2 in a file
    Given that I would like to replace the word "${name}"
    And for the word "Flavio Oliva"
    When the word replacement service is executed
    Then the resulted string should not have the word "${name}" in the final text

