package devy.hi.berkeley.marshal;

import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

/**
 * Database Entity 생성시 primary key, secondary key의 marshalling에 사용하는 helper class입니다.
 * Berkeley db의 TupleOutput, TupleInput을 사용하여도 무방합니다.
 */
public class MarshalKeyHelper {

    public static MarshalPrimaryKeyHelper marshalPrimaryKeyHelper(TupleOutput keyOutput) {
        return new MarshalPrimaryKeyHelper(keyOutput);
    }

    public static UnMarshalPrimaryKeyHelper unMarshalPrimaryKeyHelper(TupleInput keyInput) {
        return new UnMarshalPrimaryKeyHelper(keyInput);
    }

    public static MarshalSecondaryKeyHelper marshalSecondaryKeyHelper(String keyName, TupleOutput keyOutput) {
        return new MarshalSecondaryKeyHelper(keyName, keyOutput);
    }

}