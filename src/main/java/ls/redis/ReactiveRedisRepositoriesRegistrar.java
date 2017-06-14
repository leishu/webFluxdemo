package ls.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;


/**
 * Created by leishu on 17-6-7.
 */
public class ReactiveRedisRepositoriesRegistrar implements ImportBeanDefinitionRegistrar {

    Logger logger = LoggerFactory.getLogger(ReactiveRedisRepositoriesRegistrar.class);

    private static final String BASE_PACKAGE_CLASSES = "basePackageClasses";

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        logger.info("start register redis repositories.");
        AnnotationAttributes attributes = new AnnotationAttributes(
                annotationMetadata.getAnnotationAttributes(getAnnotation().getName()));
        Class[] repositories = attributes.getClassArray(BASE_PACKAGE_CLASSES);
        Arrays.stream(repositories).forEach((repository) -> {

            Type[] types = repository.getGenericInterfaces();
            final Type entity = ((ParameterizedTypeImpl) types[0])
                    .getActualTypeArguments()[0];

            Class oper = ((Class) entity).getDeclaredAnnotations()[0]
                    .annotationType();


            if (oper.equals(RedisSet.class)) {

                ProxyFactory proxy = new ProxyFactory(new ReactiveRedisSetRepository<>());
                proxy.setInterfaces(repository, InitializingBean.class, BeanFactoryAware.class);

                RootBeanDefinition beanDef = new RootBeanDefinition(repository);
                beanDef.setInstanceSupplier(() -> {
                    try {
                        ReactiveRedisSetRepository rr = (ReactiveRedisSetRepository)
                                proxy.getTargetSource().getTarget();
                        rr.setRedisSerializationContext(getEntitySerialization(entity));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return proxy.getProxy();
                });
                beanDef.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_NAME);
                beanDef.setLazyInit(true);

                registry.registerBeanDefinition(repository.getName(), beanDef);

            }

        });
    }

    private RedisSerializationContext getEntitySerialization(Type entity) {
        RedisSerializer<String> keySerializer = new StringRedisSerializer();
        RedisSerializer jackson = new Jackson2JsonRedisSerializer<>((Class) entity);
        return new RedisSerializationContext() {
            @Override
            public SerializationPair getKeySerializationPair() {
                return SerializationPair.fromSerializer(keySerializer);
            }

            @Override
            public SerializationPair getValueSerializationPair() {
                return SerializationPair.fromSerializer(jackson);
            }

            @Override
            public SerializationPair getHashKeySerializationPair() {
                return SerializationPair.fromSerializer(keySerializer);
            }

            @Override
            public SerializationPair getHashValueSerializationPair() {
                return SerializationPair.fromSerializer(jackson);
            }

            @Override
            public SerializationPair<String> getStringSerializationPair() {
                return SerializationPair.fromSerializer(keySerializer);
            }
        };
    }


    private Class<? extends Annotation> getAnnotation() {
        return EnableReactiveRedisRepositories.class;
    }
}
