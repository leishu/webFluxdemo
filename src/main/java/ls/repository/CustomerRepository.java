package ls.repository;

import ls.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.sql.DataSource;
import java.sql.ResultSet;
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

    public List<Customer> findAll() {
        String sql = "select id, name, status from customer order by id";
        return temp.query(sql,
                (ResultSet rs, int i) -> {
                    Customer customer = new Customer();
                    customer.setId(rs.getInt("id"));
                    customer.setName(rs.getString("name"));
                    customer.setStatus(rs.getInt("status"));
                    return customer;
                });
    }

    public Integer create(Customer customer) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(temp.getDataSource())
                .withTableName("customer").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("name", customer.getName());
        parameters.put("status", customer.getStatus());

        return insert.execute(parameters);
    }
}
