package at.fhj.swd;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Created by bernhard on 18.10.2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.findAll",
                query = "SELECT c FROM Category c"),
})

public class Customer {
    private Integer customerNumber;
    private String name;
    private String address;

    public Customer(Integer customerNumber, String name, String address) {
        setCustomerNumber(customerNumber);
        setName(name);
        setAddress(address);
    }

    public Customer() {
    }


    @Id
    @Column(name = "customer_number")
    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
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
