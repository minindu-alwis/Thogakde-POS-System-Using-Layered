package repository.custom;

import Models.Customer;
import repository.CrudDao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao extends CrudDao<Customer,String> {

    String generateId() throws SQLException;

    ArrayList<String> getAllCustomerIds() throws SQLException;

    Customer searchCustomerforOrderForm(String customerid) throws SQLException;

}
