package at.fhj.swd;

import javax.persistence.*;

/**
 * Created by NUC on 29.10.2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.findAll",
                query = "SELECT c FROM Customer c"),
})

public class Customer {
    @SequenceGenerator( name="CustomerIdGenerator",
            sequenceName="Customer_Sequence",
            allocationSize = 1)

    @Id @GeneratedValue(generator="CustomerIdGenerator")
    @Column(name = "customer_number")
    private Integer customerNumber;
    private String name;
    private String address;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    protected Customer() {
    }


    @Column(name = "customer_number")
    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (customerNumber != null ? !customerNumber.equals(customer.customerNumber) : customer.customerNumber != null)
            return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerNumber != null ? customerNumber.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
