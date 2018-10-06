package devy.hi.berkeley;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;
import devy.hi.berkeley.marshal.MarshalKeyHelper;

public class PartKey implements MarshalledTupleEntry {

    private String number;

    public PartKey(String number) {

        this.number = number;
    }

    public final String getNumber() {

        return number;
    }

    public String toString() {

        return "[PartKey: number=" + number + ']';
    }

    // --- MarshalledTupleEntry implementation ---

    public PartKey() {

        // A no-argument constructor is necessary only to allow the binding to
        // instantiate objects of this class.
    }

    public void marshalEntry(TupleOutput keyOutput) {
        MarshalKeyHelper
                .marshalPrimaryKeyHelper(keyOutput)
                .marshalPrimaryKey(this.number);
    }

    public void unmarshalEntry(TupleInput keyInput) {
        MarshalKeyHelper
                .unMarshalPrimaryKeyHelper(keyInput)
                .unMarshalPrimaryKey(this.number);
    }
}
