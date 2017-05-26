package ls;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by leishu on 17-5-24.
 */
@SpringBootApplication
public class WebFluxApp  {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebFluxApp.class, args);
    }

/*    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(EchoHandle echoHandle) {
        return route(POST("/echo"), echoHandle::echo);
    }*/
}
