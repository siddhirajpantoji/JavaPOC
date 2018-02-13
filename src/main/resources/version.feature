#Feature: the  can be retrieved
#  Scenario: client makes call to GET /api/greeting
#    When the client calls /api/greeting with name=Sidd
#    Then the client receives status code of 200
#    And the client receives server 
   
Feature: User and His Card Save
  Scenario: User Comes and saves his card information 
    When User Registers for First Time 
    Then Logs in with the same Email Id
    Then Gets Card Details with the user Id
    Then Adds Card Details with user Id
    Then Gets Card Details with the user Id 
    And updates card Details 
  