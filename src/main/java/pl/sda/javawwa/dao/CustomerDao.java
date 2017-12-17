package pl.sda.javawwa.dao;

import pl.sda.javawwa.entity.Customer;

import java.util.List;

public interface CustomerDao {

    public List getAllCustomers();

    public Customer findCustomerByEmail(String email);

}
