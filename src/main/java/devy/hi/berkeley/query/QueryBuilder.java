package devy.hi.berkeley.query;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;

/**
 * 데이터베이스를 생성, 질의하기 위한 IQuery를 생성합니다.
 * 빌드를 하기 위해 호출하는 메서드들은 모두 Class를 매개변수로 받습니다.
 */
public class QueryBuilder {

    private String dbName;
    private Class keyClass;
    private Class valueBaseClass;
    private boolean writeAllowed = true;
    private String primaryDbName;
    private String keyName;
    private String foreignKeyDbName;

    /**
     * QueryBuilder 객체 재사용시 멤버변수를 초기화한 후 사용하세요.
     */
    public QueryBuilder init() {
        this.dbName = null;
        this.keyClass = null;
        this.valueBaseClass = null;
        this.writeAllowed = true;
        this.primaryDbName = null;
        this.keyName = null;
        this.foreignKeyDbName = null;

        return this;
    }

    /**
     * keyClass는 검색에 필요한 키 정보를 담기 위한 객체 생성에 사용됩니다.
     * 만약 특정값을 조회하고 싶을 경우 key 정보를 담을 MarshalledTupleEntry 인터페이스를 구현해야 합니다.
     * keyClass는 Database를 open할 때 필수로 사용됩니다.
     * SecondaryDatabase를 open할 때는 keyName이 dbName이 됩니다.
     */
    public <V extends MarshalledTupleEntry> QueryBuilder setKeyClass(Class<V> keyClass) {
        this.keyClass = keyClass;
        this.keyName = keyClass.getSimpleName();
        return this;
    }

    /**
     * valueBaseClass는 데이터를 담기위한 객체 생성에 사용됩니다.
     * 어떠한 상황에서도 데이터를 조회하면 데이터는 valueBaseClass에 데이터가 담깁니다.
     * Index, ForeignKey 제약조건을 갖는 SecondaryDatabase를 생성할 때는 키를 질의할 primary db의 이름됩니다.
     */
    public <V extends MarshalledTupleKeyEntity> QueryBuilder setValueBaseClass(Class<V> valueBaseClass) {
        this.dbName = valueBaseClass.getSimpleName();
        this.valueBaseClass = valueBaseClass;
        this.primaryDbName = valueBaseClass.getSimpleName();
        return this;
    }

    /**
     * ForeignKey 제약조건을 갖는 SecondaryDabase 객체 생성에 사용됩니다.
     */
    public <V extends MarshalledTupleKeyEntity> QueryBuilder setForeignKeyDbClass(Class<V> foreignKeyDbClass) {
        this.foreignKeyDbName = foreignKeyDbClass.getSimpleName();
        return this;
    }

    public QueryBuilder setWriteAllowed(boolean writeAllowed) {
        this.writeAllowed = writeAllowed;
        return this;
    }

    /**
     * Database, SecondaryDatabase 등을 조회하기 위한 IQuery를 생성합니다.
     */
    public IQuery build(QueryType queryType) {

        IQuery query = null;

        switch (queryType) {
            case PRIMARY:
                query = new Query(this.dbName, this.keyClass, this.valueBaseClass, this.writeAllowed);
                break;
            case INDEX:
                this.dbName = this.keyClass.getSimpleName();
                query = new IndexSecondaryQuery(this.dbName, this.keyClass, this.valueBaseClass, this.writeAllowed, this.primaryDbName, this.keyName);
                break;
            case FOREIGNKEY:
                this.dbName = this.keyClass.getSimpleName();
                query = new ForeignKeySecondaryQuery(this.dbName, this.keyClass, this.valueBaseClass, this.writeAllowed, this.primaryDbName, this.keyName, this.foreignKeyDbName);
                break;
        }

        if(!query.isValid()) {
            throw new NullPointerException(query.toString());
        }

        return query;
    }

    @Override
    public String toString() {
        return "QueryBuilder{" +
                "dbName='" + dbName + '\'' +
                ", keyClass=" + keyClass +
                ", valueBaseClass=" + valueBaseClass +
                ", writeAllowed=" + writeAllowed +
                '}';
    }

}