package ls.repository;

import ls.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by leishu on 17-5-24.
 */
@Repository
public class CustomerRepository {
    @Autowired
    private JdbcTemplate temp;
    @Autowired
    private DataSource dataSource;

    public Integer create(Customer customer) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("customer").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("name", customer.getName());
        parameters.put("status", customer.getStatus());

        return insert.execute(parameters);
    }
}
