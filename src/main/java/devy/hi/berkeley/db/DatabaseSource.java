package devy.hi.berkeley.db;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.collections.TransactionRunner;
import com.sleepycat.collections.TupleSerialFactory;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Environment;
import com.sleepycat.je.SecondaryConfig;

/**
 * 설정에 대한 객체들을 모아놓은 클래스
 */
public class DatabaseSource {

    private final String CLASS_CATALOG = "java_class_catalog";
    private Environment environment;
    private DatabaseConfig databaseConfig;
    private SecondaryConfig secondaryConfig;
    private StoredClassCatalog storedClassCatalog;
    private TupleSerialFactory tupleSerialFactory;
    private TransactionRunner txnRunner;

    public DatabaseSource() {
        this.environment = new EnvironmentBuilder().build();
        this.databaseConfig = new DatabaseConfigBuilder().build();
        this.secondaryConfig = new SecondaryConfigBuilder().build();
        this.storedClassCatalog = new StoredClassCatalog(
                this.environment.openDatabase(null, CLASS_CATALOG, this.databaseConfig)
        );
        this.tupleSerialFactory = new TupleSerialFactory(this.storedClassCatalog);
        this.txnRunner = new TransactionRunner(this.environment);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public DatabaseSource setEnvironment(Environment environment) {
        this.environment = environment;
        return this;
    }

    public DatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }

    public DatabaseSource setDatabaseConfig(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
        return this;
    }

    public SecondaryConfig getSecondaryConfig() {
        return secondaryConfig;
    }

    public DatabaseSource setSecondaryConfig(SecondaryConfig secondaryConfig) {
        this.secondaryConfig = secondaryConfig;
        return this;
    }

    public TupleSerialFactory getTupleSerialFactory() {
        return this.tupleSerialFactory;
    }

    public TransactionRunner getTxnRunner() {
        return txnRunner;
    }
}