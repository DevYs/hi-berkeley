package devy.hi.berkeley;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

/**
 * FkSecondaryQuery를 정의할때 사용되는 키 객체의 구현은 MarshalledTupleEntry 인터페이스를 직접 구현하여도 상관없다.
 */
public class ShipmentPartFk implements MarshalledTupleEntry {

    private String number;

    public ShipmentPartFk(String number) {

        this.number = number;
    }

    public final String getNumber() {

        return number;
    }

    public String toString() {

        return "[PartKey: number=" + number + ']';
    }

    // --- MarshalledTupleEntry implementation ---

    public ShipmentPartFk() {

        // A no-argument constructor is necessary only to allow the binding to
        // instantiate objects of this class.
    }

    public void marshalEntry(TupleOutput keyOutput) {

        keyOutput.writeString(this.number);
    }

    public void unmarshalEntry(TupleInput keyInput) {

        this.number = keyInput.readString();
    }

}
