package pl.sda.javawwa.dao;

import pl.sda.javawwa.entity.Customer;

import java.util.List;

public interface CustomerDao {

    public List<Customer> getAllCustomers();

    public Customer findCustomerByEmail(String email);

}
