package mate.academy.internetshop3.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop3.dao.ItemDao;
import mate.academy.internetshop3.lib.Dao;
import mate.academy.internetshop3.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static String DB_NAME = "internetshop";
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        PreparedStatement preparedStatement = null;
        String query = String.format("INSERT INTO " + DB_NAME
                + ".items (name, price)" + " VALUES (?, ?);");
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.executeUpdate();
            return item;
        } catch (SQLException e) {
            logger.error("Cant create new item ", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public Item get(Long id) {
        Statement statement = null;
        String query = String.format("SELECT * FROM " + DB_NAME
                + ".items where item_id " + id + ";");

        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Long item_id = result.getLong("item_id");
                String name = result.getString("name");
                Double price = result.getDouble("price");
                Item item = new Item(id);
                item.setName(name);
                item.setPrice(price);
                return item;
            }
        } catch (SQLException e) {
            logger.warn("Cant get item by id=" + id);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        PreparedStatement preparedStatement = null;
        String query = String.format("UPDATE " + DB_NAME
                + ".items SET name= ?, price= ?" + "WHERE item_id= ?;");
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setLong(3, item.getId());
            preparedStatement.executeUpdate();
            return item;
        } catch (SQLException e) {
            logger.error("Cant update item ", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        PreparedStatement preparedStatement = null;
        String query = String.format("DELETE FROM "
                + DB_NAME + ".items WHERE item_id=?;");
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cant remove item ", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> allItems = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM " + DB_NAME + ".items";
        try {
            ResultSet result = preparedStatement.executeQuery(query);
            while (result.next()) {
                Long id = result.getLong("item_id");
                allItems.add(get(id));
            }
            return allItems;
        } catch (SQLException e) {
            logger.error("Cant get all items ", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
