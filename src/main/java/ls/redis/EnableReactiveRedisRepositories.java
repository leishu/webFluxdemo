package ls.redis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by leishu on 17-6-7.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ReactiveRedisRepositoriesRegistrar.class)
public @interface EnableReactiveRedisRepositories {

    Class<?>[] basePackageClasses() default {};

}
