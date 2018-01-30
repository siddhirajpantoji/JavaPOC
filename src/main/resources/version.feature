Feature: the  can be retrieved
  Scenario: client makes call to GET /api/greeting
    When the client calls /api/greeting with name=Sidd
    Then the client receives status code of 200
    And the client receives server 