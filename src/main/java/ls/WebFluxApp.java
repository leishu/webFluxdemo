package ls;


import ls.entity.ChatRoom;
import ls.repository.ChatRoomRepository;
import ls.ws.ChatWebSocketHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leishu on 17-5-24.
 */
@SpringBootApplication
@EnableConfigurationProperties({MongoProperties.class})
public class WebFluxApp  {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebFluxApp.class, args);
    }

/*    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(EchoHandle echoHandle) {
        return route(POST("/echo"), echoHandle::echo);
    }*/

    @Bean
    public HandlerMapping webSocketMapping() {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/chat", new ChatWebSocketHandler());

        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(map);
        return mapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    @Bean
    public CommandLineRunner createChatRooms(ChatRoomRepository repository) {
        Flux<ChatRoom> rooms = Flux.just(
                new ChatRoom("Java", "Java room."),
                new ChatRoom("Spring5", "Spring room."));

        return args -> {
            repository.deleteAll()
                    .thenMany(repository.saveAll(rooms)).blockLast();
        };
    }
}
