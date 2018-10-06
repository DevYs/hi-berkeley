package devy.hi.berkeley.db;

import com.sleepycat.je.Database;
import devy.hi.berkeley.query.IForeignKeySecondaryQuery;
import devy.hi.berkeley.query.IIndexSecondaryQuery;
import devy.hi.berkeley.query.IQuery;

import java.util.HashMap;
import java.util.Map;

/**
 * DatabaseFactory에 의해 생성된 Database, SecondaryDatabase 등을 관리합니다.
 */
public class DatabaseMap {

    private Map<String, Database> instance;

    public DatabaseMap() {
        this.instance = new HashMap<>();
    }
    
    public boolean containsDatabase(IQuery iQuery) {
        return containsDatabase(iQuery.getDbName());
    }

    public boolean containsDatabase(String dbName) {
        return this.instance.containsKey(dbName);
    }

    public void put(IQuery iQuery, Database database) {
        put(iQuery.getDbName(), database);
    }

    public void put(String dbName, Database database) {
        this.instance.put(dbName, database);
    }

    public Database get(String dbName) {
        return this.instance.get(dbName);
    }

    public Database get(IQuery iQuery) {
        return get(iQuery.getDbName());
    }

    public Database getPrimaryDatabase(IIndexSecondaryQuery indexSecondaryQuery) {
        return get(indexSecondaryQuery.getPrimaryDbName());
    }

    public Database getForeignKeyDatabase(IForeignKeySecondaryQuery foreignKeySecondaryQuery) {
        return get(foreignKeySecondaryQuery.getForeignKeyDbName());
    }

}