package ls.entity;

/**
 * Created by leishu on 17-5-24.
 */

public class Customer {

    private Integer id;

    private String name;

    private Integer status;

    public Customer() {

    }

    public Customer(Integer id, String name, Integer status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Customer customer = (Customer) other;
        return getId().equals(customer.getId())
                && getName().equals(customer.getName())
                && getStatus().equals(customer.getStatus());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
