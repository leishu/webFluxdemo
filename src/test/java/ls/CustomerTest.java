package ls;

import ls.entity.Customer;
import ls.entity.output.OperationStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.assertThat;

/**
 * Created by leishu on 17-5-26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void create() throws Exception {
        this.webClient.post().uri("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just("{\"name\": \"abc\", \"status\": 0}"), String.class)
                .exchange()
                .expectBody(String.class)
                .isEqualTo(Integer.toString(OperationStatus.SUCCESS.getOperationId()));
    }

    @Test
    public void nameNotUnique() throws Exception {
        this.webClient.post().uri("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just("{\"name\": \"github\", \"status\": 0}"), String.class)
                .exchange()
                .expectBody(String.class)
                .isEqualTo(Integer.toString(OperationStatus.NOTUNIQUE.getOperationId()));
    }

    @Test
    public void findAll() throws Exception {
        FluxExchangeResult<Customer> result = webClient
                .get().uri("/customers")
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .returnResult(Customer.class);

        StepVerifier.create(result.getResponseBody())
                .expectNext(new Customer(0, "github", 0),
                        new Customer(1, "linux", 0))
                .consumeNextWith(customer ->
                        assertThat(customer.getName(), endsWith("nginx")))
                .thenCancel().verify();
    }
}
