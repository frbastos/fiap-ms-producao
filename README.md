
# FIAP - Arquitetura de Software -> Sistema de Pedido

## Descrição do Projeto

Este projeto é um sistema de pedidos desenvolvido em **Java Spring Boot**, permitindo que clientes façam pedidos personalizados, realizem pagamentos via QRCode do **Mercado Pago**, acompanhem o status de seus pedidos e recebam notificações quando o pedido estiver pronto. O sistema também oferece gerenciamento de clientes, produtos e categorias, além de um painel administrativo para monitoramento de pedidos.

## Funcionalidades

### Pedido
- **Identificação do Cliente**: Clientes podem se identificar via CPF, nome e e-mail, ou optar por fazer pedidos anonimamente.
- **Montagem de Combo**: Clientes podem montar seus combos escolhendo:
    - Lanche
    - Acompanhamento
    - Bebida
    - Sobremesa
- Exibição de nome, descrição e preço dos produtos em cada etapa.

### Pagamento
- **QRCode Mercado Pago**: Pagamento integrado via QRCode.

### Acompanhamento
- **Status do Pedido**: Monitoramento em tempo real dos estados do pedido:
    - Recebido
    - Em preparação
    - Pronto
    - Finalizado

### Entrega
- **Notificação**: Notificação ao cliente quando o pedido estiver pronto para retirada.

### Gerenciamento
- **Clientes**: Gestão de clientes para campanhas promocionais.
- **Produtos e Categorias**: Gerenciamento de produtos com nome, categoria, preço, descrição e imagens.
- **Pedidos**: Acompanhamento de pedidos em andamento e tempo de espera.

## Microserviço de Pagamento

O Microserviço da Cozinha é responsável pelo gerenciamento da preparação dos pedidos. Ele recebe os pedidos confirmados do Microserviço de Pedidos e atualiza o status do pedido à medida que o preparo avança. Ao final, notifica o Microserviço de Pedidos quando o pedido estiver pronto para retirada.

**Fluxo de Comunicação**
1. O Microserviço de Pedidos envia um pedido confirmado para o Microserviço da Cozinha.
2. O Microserviço da Cozinha muda o status do pedido para "Em preparação".
3. Conforme o preparo avança, o status é atualizado para "Pronto".
4. Quando o pedido estiver pronto, o Microserviço de Pedidos é notificado.
5. O cliente recebe uma notificação de que o pedido está pronto para retirada.

### Passos para Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/frbastos/fiap-ms-producao
   cd fiap-ms-producao
   ```

2. Executar testes unitário:
    ```
        mvn test -Punit-test
    ```

3. Executar testes de sistema (BDD):
    ```
        mvn test -Psystem-test
    ```

4. Executar projeto com spring boot:
    ```
        mvn spring-boot:run
    ```

## Documentação Complementar

- [Notion do Projeto](https://global-gorilla-13f.notion.site/FIAP-Projeto-Lanchonete-26bfdcca5de84ce8974cbfad8286dcc2)
- [Miro com Fluxos](https://miro.com/app/board/uXjVK3DvRAo=/?share_link_id=212036327976)
- [Collection Postman](https://drive.google.com/file/d/1Ay9XiEuyfQ_aEJgu9B3uztIGKrQtaAK-/view?usp=sharing)

## Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).