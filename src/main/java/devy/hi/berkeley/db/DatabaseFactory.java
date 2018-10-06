package devy.hi.berkeley.db;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.TupleSerialFactory;
import com.sleepycat.je.*;

/**
 * Database, SecondaryDatabase 객체를 생성합니다.
 */
public class DatabaseFactory {

    public Database openDatabase(Environment env, DatabaseConfig dbConfig, String dbName) {
        return env.openDatabase(null, dbName, dbConfig);
    }

    public <V extends MarshalledTupleKeyEntity> SecondaryDatabase openSecondaryDatabase(Environment env, SecondaryConfig secConfig, TupleSerialFactory factory, Class<V> valueBaseClass, String keyName, String secDbName, Database primaryDatabase) {
        secConfig.setKeyCreator(factory.getKeyCreator(valueBaseClass, keyName));
        return openSecondaryDatabase(env, secConfig, secDbName, primaryDatabase);
    }

    public <V extends MarshalledTupleKeyEntity> SecondaryDatabase openForeignKeySecondaryDatabase(Environment env, SecondaryConfig secConfig, TupleSerialFactory factory, Class<V> valueBaseClass, String keyName, String secDbName, Database primaryDatabase, Database foreignKeyDb) {
        secConfig.setForeignKeyDatabase(foreignKeyDb)
                .setForeignKeyDeleteAction(ForeignKeyDeleteAction.CASCADE)
                .setKeyCreator(factory.getKeyCreator(valueBaseClass, keyName));
        return openSecondaryDatabase(env, secConfig, secDbName, primaryDatabase);
    }

    private SecondaryDatabase openSecondaryDatabase(Environment env, SecondaryConfig secConfig, String secDbName, Database primaryDatabase) {
        return env.openSecondaryDatabase(null, secDbName, primaryDatabase, secConfig);
    }

}
