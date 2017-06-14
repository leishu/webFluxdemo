package ls.redis;

import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by leishu on 17-6-7.
 */
@NoRepositoryBean
public interface RedisRepository<T> {
    Mono<Long> save(T t);

    Flux<T> list(T t);
}
