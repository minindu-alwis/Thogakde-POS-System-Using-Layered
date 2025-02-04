package service.custome;
import Models.Item;
import Models.OrderDetail;
import service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemService extends SuperService {
    List<Item> getAll();

    boolean saveItem(Item item) throws SQLException;

    boolean updateItem(Item item) throws SQLException;

    boolean deleteItem(String itemid);

    Item searchItem(String itemid);

    String generateId();

    boolean updateStock(ArrayList <OrderDetail>orderDetails) throws SQLException;
    boolean updateStock(OrderDetail orderDetail) throws SQLException;

    ArrayList<String> loadAllItemCodes() throws SQLException;

    Item searchItemforOrderForm(String itemid);


}
