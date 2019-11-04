package mate.academy.internetshop3.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mate.academy.internetshop3.dao.OrderDao;
import mate.academy.internetshop3.lib.Dao;
import mate.academy.internetshop3.model.Item;
import mate.academy.internetshop3.model.Order;
import mate.academy.internetshop3.model.User;
import org.apache.log4j.Logger;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) VALUE (?);";
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, order.getUser().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                Long orderId = resultSet.getLong("order_id");
                order.setId(orderId);
            }
        } catch (SQLException e) {
            logger.error("Cant create order", e);
            return null;
        }

        String addItem = "INSERT INTO orders_items (order_id, item_id) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(addItem)) {
            for (Item item : order.getItems()) {
                preparedStatement.setLong(1, order.getId());
                preparedStatement.setLong(2, item.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Cant add items to order", e);
            return null;
        }
        return order;
    }

    @Override
    public Order get(Long id) {
        String query = "SELECT * FROM orders WHERE order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                Long orderId = resultSet.getLong("order_id");
                Order order = new Order();
                order.getUser().setId(userId);
                order.setItems(get(id).getItems());
                order.setId(orderId);
                return order;
            }
        } catch (SQLException e) {
            logger.error("Cant get order", e);
        }
        return null;
    }

    @Override
    public Order update(Order order) {
        String query = "UPDATE orders SET user_id = ? WHERE order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, order.getUser().getId());
            preparedStatement.setLong(2, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cant update order", e);
        }
        return order;
    }

    @Override
    public void delete(User user) {
        String query = "DELETE FROM orders WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cant delete order", e);
        }
    }
}
