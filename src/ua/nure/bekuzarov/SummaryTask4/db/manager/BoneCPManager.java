package ua.nure.bekuzarov.SummaryTask4.db.manager;

import com.jolbox.bonecp.BoneCPDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static ua.nure.bekuzarov.SummaryTask4.db.manager.DatabaseConfig.*;

/**
 * A connection manager, based on BoneCP tool.
 *
 */
@SuppressWarnings("unused")
public final class BoneCPManager implements ConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoneCPManager.class);
    private static final int ACQUIRE_INCREMENT = 5;
    private static final int PARTITION_COUNT = 3;
    private static final int MIN_CONNECTIONS = 5;
    private static final int MAX_CONNECTIONS = 40;
    private static final Map<String, String> DRIVERS;

    static {
        DRIVERS = new HashMap<>();
        DRIVERS.put("mysql", "com.mysql.jdbc.Driver");
    }

    private BoneCPDataSource dataSource;

    /**
     * Instantiates a new manager.
     */
    public BoneCPManager() {
        dataSource = new BoneCPDataSource();
        dataSource.setDisableConnectionTracking(true);
        dataSource.setDriverClass(DRIVERS.get(getRdbms()));
        dataSource.setJdbcUrl(getConnectionUrl());
        dataSource.setUsername(getUser());
        dataSource.setPassword(getPassword());
        dataSource.setDefaultTransactionIsolation(getTransactionIsolation());
        dataSource.setDefaultAutoCommit(false);
        dataSource.setAcquireIncrement(ACQUIRE_INCREMENT);
        dataSource.setPartitionCount(PARTITION_COUNT);
        dataSource.setMinConnectionsPerPartition(MIN_CONNECTIONS);
        dataSource.setMaxConnectionsPerPartition(MAX_CONNECTIONS);
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Connection wasn't set", e);
            throw new IllegalStateException("Connection wasn't set", e);
        }
    }

    @Override
    public void shutdown() {
        dataSource.close();
    }

}
