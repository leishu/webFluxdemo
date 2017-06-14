package ls.config;


import ls.redis.EnableReactiveRedisRepositories;
import ls.repository.LoginRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * Created by leishu on 17-6-5.
 */
@Configuration
@EnableReactiveRedisRepositories(
        basePackageClasses = {LoginRepository.class})
public class RedisConfig {
    @Bean("redisConnectionFactory")
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

}
