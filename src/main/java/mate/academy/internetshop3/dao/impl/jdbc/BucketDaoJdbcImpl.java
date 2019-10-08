package mate.academy.internetshop3.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop3.dao.BucketDao;
import mate.academy.internetshop3.lib.Dao;
import mate.academy.internetshop3.model.Bucket;
import mate.academy.internetshop3.model.Item;
import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {

    private static Logger logger = Logger.getLogger(BucketDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) {
        String query = "INSERT INTO buckets (user_id) VALUES (?);";
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, bucket.getUserId());
            preparedStatement.executeUpdate();
            ResultSet getGeneratedKey = preparedStatement.getGeneratedKeys();
            while (getGeneratedKey.next()) {
                Long bucketId = getGeneratedKey.getLong(1);
                bucket.setId(bucketId);
            }
        } catch (SQLException e) {
            logger.error("Cant create bucket", e);
        }
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        String query = "UPDATE buckets SET user_id = ? WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucket.getUserId());
            preparedStatement.setLong(2, bucket.getId());
            preparedStatement.executeUpdate();
            return bucket;
        } catch (SQLException e) {
            logger.error("Cant update bucket", e);
        }
        return null;
    }

    @Override
    public Bucket get(Long bucketId) {
        String query = "SELECT * FROM buckets WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("user_id");
                Bucket bucket = new Bucket(bucketId);
                bucket.setId(bucketId);
                bucket.setUserId(userId);
                return bucket;
            }

        } catch (SQLException e) {
            logger.error("Cant get bucket", e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM buckets WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cant delete bucket", e);
        }
    }

    @Override
    public Bucket deleteItem(Long bucketId, Long itemId) {
        String query = "DELETE FROM buckets_items WHERE bucket_id = ? AND item_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            preparedStatement.setLong(2, itemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cant delete item", e);
        }
        return get(bucketId);
    }

    @Override
    public Boolean addItem(Long itemId, Long bucketId) {
        String query = "INSERT INTO buckets_items (item_id, bucket_id) VALUES(?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, itemId);
            preparedStatement.setLong(2, bucketId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Cant add items to bucket", e);
            return false;
        }
    }

    @Override
    public Long getBucketId(Long userId) {
        String query = "SELECT * FROM buckets WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long bucketId = resultSet.getLong("bucket_id");
                return bucketId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getAllItems(Long bucketId) {
        List<Item> itemList = new ArrayList<>();
        String query = "SELECT * FROM items INNER JOIN buckets_items "
                + "ON items.item_id = buckets_items.item_id WHERE bucket_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item(name, price);
                item.setPrice(price);
                item.setPrice(price);
                item.setId(itemId);
                itemList.add(item);
            }
        } catch (SQLException e) {
            logger.error("Cant get all items in a bucket", e);
        }
        return itemList;
    }
}
