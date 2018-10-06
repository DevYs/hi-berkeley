package devy.hi.berkeley.db;

import com.sleepycat.collections.TransactionRunner;
import com.sleepycat.collections.TupleSerialFactory;
import com.sleepycat.je.Database;
import com.sleepycat.je.SecondaryDatabase;
import devy.hi.berkeley.query.IForeignKeySecondaryQuery;
import devy.hi.berkeley.query.IIndexSecondaryQuery;
import devy.hi.berkeley.query.IQuery;

public class DatabaseManager {

    private final DatabaseSource dbSource = new DatabaseSource();
    private final DatabaseFactory dbFactory = new DatabaseFactory();
    private final DatabaseViewFactory dbViewFactory = new DatabaseViewFactory();
    private final DatabaseMap databaseMap = new DatabaseMap();

    private static class LazyHolder {
        public static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager instance() {
        return DatabaseManager.LazyHolder.INSTANCE;
    }

    public Database getDatabase(IQuery iQuery) {

        if(this.databaseMap.containsDatabase(iQuery)) {
            return this.databaseMap.get(iQuery);
        }

        /*
         * IForeignKeySecondaryQuery 인터페이스 먼저 확인해야합니다.
         * IForeignKeySecondaryQuery 인터페이스는 IIndexSecondaryQuery 인터페이스를 상속했습니다.
         */
        if (iQuery instanceof IForeignKeySecondaryQuery) {
            return getForeignKeySecondaryDatabase((IForeignKeySecondaryQuery) iQuery);
        } else if (iQuery instanceof IIndexSecondaryQuery) {
            return getSecondaryDatabase((IIndexSecondaryQuery) iQuery);
        }

        return getDatabase(iQuery.getDbName());
    }

    private Database getDatabase(String dbName) {
        Database primaryDb = this.dbFactory.openDatabase(this.dbSource.getEnvironment(), this.dbSource.getDatabaseConfig(), dbName);
        this.databaseMap.put(dbName, primaryDb);
        return this.databaseMap.get(dbName);
    }

    private SecondaryDatabase getSecondaryDatabase(IIndexSecondaryQuery idxQuery) {
        Database primaryDb = this.databaseMap.getPrimaryDatabase(idxQuery);
        SecondaryDatabase idxDb = this.dbFactory.openSecondaryDatabase(
                this.dbSource.getEnvironment(),
                this.dbSource.getSecondaryConfig(),
                this.dbSource.getTupleSerialFactory(),
                idxQuery.getValueBaseClass(),
                idxQuery.getKeyName(),
                idxQuery.getDbName(),
                primaryDb
        );

        this.databaseMap.put(idxQuery, idxDb);

        return (SecondaryDatabase) this.databaseMap.get(idxQuery);
    }

    private SecondaryDatabase getForeignKeySecondaryDatabase(IForeignKeySecondaryQuery fkQuery) {
        Database primaryDb = this.databaseMap.getPrimaryDatabase(fkQuery);
        Database foreignKeyDb = this.databaseMap.getForeignKeyDatabase(fkQuery);
        SecondaryDatabase fkDb = this.dbFactory.openForeignKeySecondaryDatabase(
                this.dbSource.getEnvironment(),
                this.dbSource.getSecondaryConfig(),
                this.dbSource.getTupleSerialFactory(),
                fkQuery.getValueBaseClass(),
                fkQuery.getKeyName(),
                fkQuery.getDbName(),
                primaryDb,
                foreignKeyDb
        );

        this.databaseMap.put(fkQuery, fkDb);

        return (SecondaryDatabase) this.databaseMap.get(fkQuery);
    }

    public TupleSerialFactory getFactory() {
        return this.dbSource.getTupleSerialFactory();
    }

    public DatabaseViewFactory getDbViewFactory() {
        return dbViewFactory;
    }

    public TransactionRunner getTxnRunner() {
        return this.dbSource.getTxnRunner();
    }

}