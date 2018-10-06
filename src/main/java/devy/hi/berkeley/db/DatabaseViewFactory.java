package devy.hi.berkeley.db;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.*;
import com.sleepycat.je.Database;

public class DatabaseViewFactory {

    public <K, V extends MarshalledTupleKeyEntity> StoredMap<K, V> map(TupleSerialFactory factory, Database db, Class<K> keyClass, Class<V> valueBaseClass) {
        return factory.newMap(db, keyClass, valueBaseClass, true);
    }

    public <K, V extends MarshalledTupleKeyEntity> StoredSortedMap<K, V> sortedMap(TupleSerialFactory factory, Database db, Class<K> keyClass, Class<V> valueBaseClass) {
        return factory.newSortedMap(db, keyClass, valueBaseClass, true);
    }

    public <K, V extends MarshalledTupleKeyEntity> StoredValueSet set(TupleSerialFactory factory, Database db, Class<K> keyClass, Class<V> valueBaseClass) {
        return (StoredValueSet) factory.newMap(db, keyClass, valueBaseClass, true).values();
    }

    public <K, V extends MarshalledTupleKeyEntity> StoredSortedValueSet sortedSet(TupleSerialFactory factory, Database db, Class<K> keyClass, Class<V> valueBaseClass) {
        return (StoredSortedValueSet) factory.newSortedMap(db, keyClass, valueBaseClass, true).values();
    }

}