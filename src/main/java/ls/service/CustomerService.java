package ls.service;

import ls.entity.Customer;
import ls.entity.output.OperationStatus;
import ls.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.function.Consumer;

/**
 * Created by leishu on 17-5-24.
 */
@Service
public class CustomerService {
    static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository repository;

    public Flux<Customer> findAll() {
        return Flux.fromIterable(repository.findAll());
    }

    @Transactional
    public Mono<Integer> create(Customer customer) {
        logger.info("create customer {} start.", customer.getName());

        Mono<Integer> saved = Mono.just(customer)
                .map(c -> repository.create(customer));

        Consumer<MonoSink<Integer>> statusEmitter = sink -> {
            saved.subscribe(
                    s -> {
                        logger.info("create customer {} success.", customer.getName());
                        sink.success(OperationStatus.SUCCESS.getOperationId());
                    },
                    e -> {
                        if (e instanceof DuplicateKeyException) {
                            logger.info("create customer {} fail, name not unique.", customer.getName());
                            sink.success(OperationStatus.NOTUNIQUE.getOperationId());
                        }
                    });
        };
        return Mono.create(statusEmitter);

    }

}
