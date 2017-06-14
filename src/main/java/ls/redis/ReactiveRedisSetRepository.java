package ls.redis;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.DefaultReactiveSetOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by leishu on 17-6-8.
 */
public class ReactiveRedisSetRepository<T, ID> implements BeanFactoryAware, InitializingBean, RedisRepository<T> {

    @Override
    public Mono<Long> save(T t) {
        String key = getKey(t);

        return operations.add(key, t);
    }

    @Override
    public Flux<T> list(T t) {
        String key = getKey(t);

        return operations.members(key);
    }

    private String getKey(T t) {
        Annotation[] annotations = t.getClass().getDeclaredAnnotations();
        Assert.notEmpty(annotations, "must has annotations.");

        Field[] fields = t.getClass().getDeclaredFields();
        Assert.notEmpty(fields, "must has fields.");


        String value = (String)AnnotationUtils.getValue(annotations[0]);

        for(int i=0; i<fields.length; i++) {
            if (fields[i].isAnnotationPresent(Id.class)) {
                BeanWrapperImpl wrapper = new BeanWrapperImpl(t);
                String id = (String)wrapper.getPropertyValue(fields[i].getName());
                return value.concat(id);
            }
        }
        return "";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        final ReactiveRedisConnectionFactory factory = beanFactory.getBean(ReactiveRedisConnectionFactory.class);
        final ReactiveRedisTemplate template = new ReactiveRedisTemplate(factory, redisSerializationContext);
        operations = new DefaultReactiveSetOperations(template, redisSerializationContext);
    }

    DefaultReactiveSetOperations operations;
    BeanFactory beanFactory;
    RedisSerializationContext redisSerializationContext;

    public void setRedisSerializationContext(RedisSerializationContext redisSerializationContext) {
        this.redisSerializationContext = redisSerializationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
