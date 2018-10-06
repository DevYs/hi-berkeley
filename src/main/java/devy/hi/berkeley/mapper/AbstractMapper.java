package devy.hi.berkeley.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.*;
import devy.hi.berkeley.db.DatabaseManager;
import devy.hi.berkeley.db.DatabaseViewFactory;
import devy.hi.berkeley.query.IQuery;
import devy.hi.berkeley.query.QueryBuilder;

/**
 * 해당 라이브러리를 사용하기 위한 가장 쉬운 방법 중 하나는 AbstractMapper를 상속받는 것입니다.
 */
public abstract class AbstractMapper implements IMapper {

    private DatabaseManager dbMgr = DatabaseManager.instance();
    private final DatabaseViewFactory dbViewFactory = this.dbMgr.getDbViewFactory();
    private final TupleSerialFactory factory = this.dbMgr.getFactory();
    
    public AbstractMapper() {
        this.dbMgr.getDatabase(query());
    }

    @Override
    public boolean add(MarshalledTupleKeyEntity entity) {
        return set().add(entity);
    }

    @Override
    public Object remove(MarshalledTupleEntry key) {
        return map().remove(key);
    }

    @Override
    public Object mod(MarshalledTupleEntry key, MarshalledTupleKeyEntity entity) {
        return map().replace(key, entity);
    }

    @Override
    public StoredMap map() {
        return this.dbViewFactory.map(this.factory, this.dbMgr.getDatabase(query()), query().getKeyClass(), query().getValueBaseClass());
    }

    @Override
    public StoredSortedMap sortedMap() {
        return this.dbViewFactory.sortedMap(this.factory, this.dbMgr.getDatabase(query()), query().getKeyClass(), query().getValueBaseClass());
    }

    @Override
    public StoredValueSet set() {
        return this.dbViewFactory.set(this.factory, this.dbMgr.getDatabase(query()), query().getKeyClass(), query().getValueBaseClass());
    }

    @Override
    public StoredSortedValueSet sortedSet() {
        return this.dbViewFactory.sortedSet(this.factory, this.dbMgr.getDatabase(query()), query().getKeyClass(), query().getValueBaseClass());
    }

    @Override
    public IQuery query() {
        return query(new QueryBuilder());
    }

    public abstract IQuery query(QueryBuilder queryBuilder);
}