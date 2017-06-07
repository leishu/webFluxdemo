package ls.config;


import ls.repository.LoginRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * Created by leishu on 17-6-5.
 */
@Configuration
@EnableRedisRepositories(
        basePackageClasses = {LoginRepository.class})
public class RedisConfig {
    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public ReactiveRedisConnection reactiveRedisConnection() {
        return reactiveRedisConnectionFactory().getReactiveConnection();
    }
}
