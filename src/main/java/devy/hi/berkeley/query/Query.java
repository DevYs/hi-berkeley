package devy.hi.berkeley.query;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;

/**
 * Primary Database를 조회하기 위한 IQuery 인터페이스 구현체
 * Query를 생성하는 가장 쉬운 방법은 QueryBuilder.class를 사용하는 것입니다.
 */
public class Query implements IQuery {

    private String dbName;
    private Class keyClass;
    private Class valueBaseClass;
    private boolean writeAllowed = true;

    public Query(String dbName, Class keyClass, Class valueBaseClass, boolean writeAllowed) {
        this.dbName = dbName;
        this.keyClass = keyClass;
        this.valueBaseClass = valueBaseClass;
        this.writeAllowed = writeAllowed;
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
    public boolean isValid() {
        return NullChecker.check(this.dbName, this.keyClass, this.valueBaseClass);
    }

    @Override
    public String toString() {
        return "Query{" +
                "dbName='" + dbName + '\'' +
                ", keyClass=" + keyClass +
                ", valueBaseClass=" + valueBaseClass +
                ", writeAllowed=" + writeAllowed +
                '}';
    }

}