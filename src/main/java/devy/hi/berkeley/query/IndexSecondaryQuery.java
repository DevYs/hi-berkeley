package devy.hi.berkeley.query;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;

/**
 * Index constraints를 갖는 SecondaryDatabase를 조회하기 위한 IIndexSecondaryQeury 구현체
 * IndexSecondaryQuery를 생성하는 가장 쉬운 방법은 QueryBuilder.class를 사용하는 것입니다.
 */
public class IndexSecondaryQuery implements IIndexSecondaryQuery {

    private String dbName;
    private Class keyClass;
    private Class valueBaseClass;
    private boolean writeAllowed = true;
    private String primaryDbName;
    private String keyName;

    public IndexSecondaryQuery(String dbName, Class keyClass, Class valueBaseClass, boolean writeAllowed, String primaryDbName, String keyName) {
        this.dbName = dbName;
        this.keyClass = keyClass;
        this.valueBaseClass = valueBaseClass;
        this.writeAllowed = writeAllowed;
        this.primaryDbName = primaryDbName;
        this.keyName = keyName;
    }

    @Override
    public String getDbName() {
        return this.dbName;
    }

    @Override
    public <V extends MarshalledTupleEntry> Class<V> getKeyClass() {
        return this.keyClass;
    }

    @Override
    public <V extends MarshalledTupleKeyEntity> Class<V> getValueBaseClass() {
        return this.valueBaseClass;
    }

    @Override
    public boolean getWriteAllowed() {
        return this.writeAllowed;
    }

    @Override
    public String getPrimaryDbName() {
        return this.primaryDbName;
    }

    @Override
    public String getKeyName() {
        return this.keyName;
    }

    @Override
    public boolean isValid() {
        return NullChecker.check(this.dbName, this.keyClass, this.valueBaseClass, this.primaryDbName, this.keyName);
    }

    @Override
    public String toString() {
        return "IndexSecondaryQuery{" +
                "dbName='" + dbName + '\'' +
                ", keyClass=" + keyClass +
                ", valueBaseClass=" + valueBaseClass +
                ", writeAllowed=" + writeAllowed +
                ", primaryDbName='" + primaryDbName + '\'' +
                ", keyName='" + keyName + '\'' +
                '}';
    }
}
