package devy.hi.berkeley.query;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;

/**
 * Primary Database를 조회하기 위한 인터페이스 입니다.
 */
public interface IQuery {

    String getDbName();

    <V extends MarshalledTupleEntry> Class<V> getKeyClass();

    <V extends MarshalledTupleKeyEntity> Class<V> getValueBaseClass();

    boolean getWriteAllowed();

    boolean isValid();

}
