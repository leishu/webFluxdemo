package ls;

import ls.entity.output.OperationStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

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
}
