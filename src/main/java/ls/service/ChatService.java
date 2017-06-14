package ls.service;

import ls.entity.Online;
import ls.entity.output.LoginResult;
import ls.entity.output.OperationStatus;
import ls.repository.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Created by leishu on 17-6-5.
 */
@Service
public class ChatService {
    Logger logger = LoggerFactory.getLogger(ChatService.class);
    @Autowired
    private LoginRepository loginRepository;

    private Mono<Integer> enter(String room, String user) {
        Online login = new Online(room, user);

        return loginRepository.save(login)
                .timeout(Duration.ofSeconds(1))
                .map(s -> {
                    logger.info("{} enter room {} success.", user, room);
                    return s.intValue();
                })
                .onErrorResume(e -> {
                    logger.warn("{} enter room {} fail: {}.",
                            user, room, e.getClass().getName());
                    return Mono.empty();
                });

    }

    private Flux<Online> list(String room) {

        return loginRepository.list(new Online(room))
                .timeout(Duration.ofSeconds(1))
                .onErrorResume(e -> {
                    logger.warn("onlines list error: {}.", e.getClass().getName());
                    return Flux.empty();
                });
    }

    public Mono<LoginResult> login(String room, String user) {

        return Mono.from(enter(room, user))
                .and(s -> list(room).collectList())
                .map((tuple2) ->
                        new LoginResult(tuple2.getT1(),
                                tuple2.getT2()))
                .defaultIfEmpty(new LoginResult(
                        OperationStatus.ERROR.getOperationId(),
                        "login fail."));

    }
}
