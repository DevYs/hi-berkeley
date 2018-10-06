package devy.hi.berkeley.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.collections.StoredSortedValueSet;
import com.sleepycat.collections.StoredValueSet;
import devy.hi.berkeley.query.IQuery;

/**
 * 해당 라이브러리 사용을 위한 동작을 정의합니다.
 */
public interface IMapper {

    IQuery query();

    boolean add(MarshalledTupleKeyEntity entity);

    Object remove(MarshalledTupleEntry key);

    Object mod(MarshalledTupleEntry key, MarshalledTupleKeyEntity entity);

    StoredMap map();

    StoredSortedMap sortedMap();

    StoredValueSet set();

    StoredSortedValueSet sortedSet();

}
