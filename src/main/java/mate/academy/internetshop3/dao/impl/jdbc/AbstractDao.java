package mate.academy.internetshop3.dao.impl.jdbc;

import java.sql.Connection;

public class AbstractDao<T> {
    protected final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }
}
