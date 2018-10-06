package devy.hi.berkeley;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;
import devy.hi.berkeley.marshal.MarshalKeyHelper;

/**
 * FkSecondaryQuery와 달리 MarshalledTupleEntry 인터페이스를 구현해주어야 한다.
 * Primary 객체에서 새로운 키를 생성하는 것이기 때문이다.
 */
public class SupplierCityIdx implements MarshalledTupleEntry {

    private String city;

    public SupplierCityIdx() {}

    public SupplierCityIdx(String city) {
        this.city = city;
    }

    @Override
    public void marshalEntry(TupleOutput tupleOutput) {
        MarshalKeyHelper
                .marshalPrimaryKeyHelper(tupleOutput)
                .marshalPrimaryKey(this.city);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        MarshalKeyHelper
                .unMarshalPrimaryKeyHelper(tupleInput)
                .unMarshalPrimaryKey(this.city);
    }
}
