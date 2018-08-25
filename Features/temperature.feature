Feature: Convert Temperature 

  Scenario Outline: Successful conversion
    Given I input a valid input '<input>' in '<originUnit>'
	  And I choose '<targetUnit>' as the result unit
    Then I get a result of '<result>'

  Examples:
    | originUnit | input    | targetUnit | result |
    | Kelvin     | 0        | Celsius    |-273.15 |
    | Kelvin     | 333      | Celsius    |59.85   |
    | Kelvin     | -333     | Celsius    |-606.15 |
    | Kelvin     | 0        | Fahrenheit |-459.67 |
    | Kelvin     | 333      | Fahrenheit |139.73  |
    | Kelvin     | -333     | Fahrenheit |-1059.07|
    | Kelvin     | 0        | Kelvin     |0       |
    | Kelvin     | 333      | Kelvin     |333     |
    | Kelvin     | -333     | Kelvin     |-333    |
    | Celsius    | 0        | Kelvin     |273.15  |
    | Celsius    | 333      | Kelvin     |606.15  |
    | Celsius    | -333     | Kelvin     |-59.85  |
    | Celsius    | 0        | Fahrenheit |32      |
    | Celsius    | 333      | Fahrenheit |631.4   |
    | Celsius    | -333     | Fahrenheit |-567.4  |
    | Celsius    | 0        | Celsius    |0       |
    | Celsius    | 333      | Celsius    |333     |
    | Celsius    | -333     | Celsius    |-333    |
    | Fahrenheit | 0        | Celsius    |-17.78  |
    | Fahrenheit | 333      | Celsius    |167.22  |
    | Fahrenheit | -333     | Celsius    |-202.78 |
    | Fahrenheit | 0        | Kelvin     |255.37  |
    | Fahrenheit | 333      | Kelvin     |440.37  |
    | Fahrenheit | -333     | Kelvin     |70.37   |
    | Fahrenheit | 0        | Fahrenheit | 0      |
    | Fahrenheit | 333      | Fahrenheit | 333    |
    | Fahrenheit | -333     | Fahrenheit |-333    |

  Scenario Outline: Failed conversion
    Given I input a invalid input '<input>' in '<originUnit>'
	  And I choose '<targetUnit>' as the result unit
    Then I get a alert with a message of 'You have entered an invalid input'

  Examples:
    | originUnit | input    | targetUnit |
    | Kelvin     | W        | Celsius    |
    | Kelvin     | asd      | Celsius    |
    | Kelvin     | 6.       | Celsius    |
    | Kelvin     | W        | Fahrenheit |
    | Kelvin     | asd      | Fahrenheit |
    | Kelvin     | 6.       | Fahrenheit |
    | Kelvin     | W        | Kelvin     |
    | Kelvin     | asd      | Kelvin     |
    | Kelvin     | 6.       | Kelvin     |
    | Celsius    | W        | Kelvin     |
    | Celsius    | asd      | Kelvin     |
    | Celsius    | 6.       | Kelvin     |
    | Celsius    | W        | Fahrenheit |
    | Celsius    | asd      | Fahrenheit |
    | Celsius    | 6.       | Fahrenheit |
    | Celsius    | W        | Celsius    |
    | Celsius    | asd      | Celsius    |
    | Celsius    | 6.       | Celsius    |
    | Fahrenheit | W        | Celsius    |
    | Fahrenheit | asd      | Celsius    |
    | Fahrenheit | 6.       | Celsius    |
    | Fahrenheit | W        | Kelvin     |
    | Fahrenheit | asd      | Kelvin     |
    | Fahrenheit | 6.       | Kelvin     |
    | Fahrenheit | W        | Fahrenheit |
    | Fahrenheit | asd      | Fahrenheit |
    | Fahrenheit | 6.       | Fahrenheit |