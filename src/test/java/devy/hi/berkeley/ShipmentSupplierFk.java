package devy.hi.berkeley;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

/**
 * FkSecondaryQuery를 정의할때 사용되는 키 객체의 구현은 Primary 객체의 키 클래스를 상속받는것으로 대체할 수 있다.
 * 다만 반드시 상위 클래스의 변수에 값을 할당하는 생성자를 구현해주어야한다.
 */
public class ShipmentSupplierFk implements MarshalledTupleEntry {

    private String number;

    public ShipmentSupplierFk(String number) {

        this.number = number;
    }

    public final String getNumber() {

        return number;
    }

    public String toString() {

        return "[PartKey: number=" + number + ']';
    }

    // --- MarshalledTupleEntry implementation ---

    public ShipmentSupplierFk() {

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
