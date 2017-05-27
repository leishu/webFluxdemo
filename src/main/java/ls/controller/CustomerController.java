package ls.controller;

import ls.entity.Customer;
import ls.entity.output.OperationStatus;
import ls.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.function.Consumer;


/**
 * Created by leishu on 17-5-24.
 */
@RestController
public class CustomerController {
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService serv;

    @GetMapping("/customer/{id}")
    Mono<Customer> findById(@PathVariable Integer id) {
        return Mono.just(new Customer());
    }

    @PostMapping("/customer")
    Mono<Integer> create(@RequestBody Customer customer) {
        return serv.create(customer);
    }

    @GetMapping("/customers")
    Flux<Customer> findAll() {
        return serv.findAll();
    }
}
