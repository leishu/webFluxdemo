package ls.service;

import ls.entity.output.OperationStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Created by leishu on 17-6-5.
 */
@Service
public class ChatService {

    public Mono<Integer> enter() {
        return Mono.just(OperationStatus.SUCCESS.getOperationId());
    }
}
