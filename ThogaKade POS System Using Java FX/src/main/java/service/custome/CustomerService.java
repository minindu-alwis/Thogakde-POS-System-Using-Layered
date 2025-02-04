package service.custome;

import Models.Customer;
import service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerService extends SuperService {

    List<Customer> getAll();

    boolean saveCustomer(Customer customer) throws SQLException;

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(String cusId);

    Customer searchCustomer(String customerid);

    String generateId();

    ArrayList<String> getAllCustomerIds() throws SQLException;

    Customer searchCustomerforOrderForm(String customerid);
}
