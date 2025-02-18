package br.com.fiap.producao.bdd;

import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.fiap.producao.infra.api.dto.ItemPedidoRequest;
import br.com.fiap.producao.infra.api.dto.PedidoRequest;
import br.com.fiap.producao.infra.api.dto.PedidoResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class CozinhaStep {

    private Response response;
    private PedidoResponse pedidoResponse;
    private final String ENDPOINT = "http://localhost:8082/cozinha";

    @When("registrar um novo pedido")
    public void registrarPedido() {

        ItemPedidoRequest itemRequest = new ItemPedidoRequest("X-Salada", 1, null);
        PedidoRequest pedidoRequest = new PedidoRequest(LocalDateTime.now(), 1010L, null, List.of(itemRequest));

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(pedidoRequest)
                .when()
                .post(ENDPOINT + "/pedido");
    }

    @Then("o pedido é registrado com sucesso")
    public void pedidoRegistradoComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value());
    }

    // @Given("que um pedido já foi criado")
    // public void pedidoJaCriado(){
    //     pedidoResponse = registrarPedido();
    // }

    // @When("requisitar a buscar do pedido")
    // public void buscarPedido(){
    //     response = given()
    //         .contentType(MediaType.APPLICATION_JSON_VALUE)
    //         .when()
    //         .get(ENDPOINT + "/{numeroPedido}", pedidoResponse.numeroPedido());
    // }

    // @Then("o pedido é exibido com sucesso")
    // public void pedidoExibidoComSucesso(){
    //     response.then()
    //         .statusCode(HttpStatus.OK.value())
    //         .body(matchesJsonSchemaInClasspath("./schemas/PedidoResponseSchema.json"));

    // }

    // @When("requisitar o acompanhamentos dos pedidos")
    // public void requisitarPedidosNaoFinalizados(){
    //     response = given()
    //         .contentType(MediaType.APPLICATION_JSON_VALUE)
    //         .when()
    //         .get(ENDPOINT + "/follow-up");
    // }

    // @Then("os pedidos devem ser apresentados")
    // public void pedidosFollowUp(){
    //     response.then()
    //         .statusCode(HttpStatus.OK.value())
    //         .body(matchesJsonSchemaInClasspath("./schemas/PedidosFollowUpSchema.json"));
    // }

    // @Then("apos 10 segundos o pagamento é processado")
    // public void processarPedido() throws InterruptedException{
    //     SECONDS.sleep(12);

    //     Response consultaResponse = given()
    //         .contentType(MediaType.APPLICATION_JSON_VALUE)
    //         .when()
    //         .get(ENDPOINT + "/statePayment/{orderNumber}", pedidoResponse.numeroPedido());

    //     consultaResponse.then().statusCode(HttpStatus.OK.value());

    //     String status = consultaResponse.jsonPath().getString("statusConfirmacaoPagamento");
    //     assertEquals("APROVADO", status);
    // }

}
