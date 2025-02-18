Feature: Cozinha

    Scenario: Registrar pedido
        When registrar um novo pedido
        Then o pedido é registrado com sucesso