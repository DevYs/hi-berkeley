package devy.hi.berkeley.marshal;

import com.sleepycat.bind.tuple.TupleInput;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * primary key unmarshalling helper class
 */
public class UnMarshalPrimaryKeyHelper {

    private TupleInput keyInput;

    public UnMarshalPrimaryKeyHelper(TupleInput keyInput) {
        this.keyInput = keyInput;
    }

    public UnMarshalPrimaryKeyHelper unMarshalPrimaryKey(String key) {
        key = this.keyInput.readString();
        return this;
    }

    public UnMarshalPrimaryKeyHelper unMarshalPrimaryKey(BigInteger key) {
        key = this.keyInput.readBigInteger();
        return this;
    }

    public UnMarshalPrimaryKeyHelper unMarshalPrimaryKey(BigDecimal key) {
        key = this.keyInput.readBigDecimal();
        return this;
    }

    public UnMarshalPrimaryKeyHelper unMarshalPrimaryKey(double key) {
        key = this.keyInput.readDouble();
        return this;
    }

    public UnMarshalPrimaryKeyHelper unMarshalPrimaryKey(float key) {
        key = this.keyInput.readFloat();
        return this;
    }

    public UnMarshalPrimaryKeyHelper unMarshalPrimaryKey(int key) {
        key = this.keyInput.readInt();
        return this;
    }

    public UnMarshalPrimaryKeyHelper unMarshalPrimaryKey(long key) {
        key = this.keyInput.readLong();
        return this;
    }
}
