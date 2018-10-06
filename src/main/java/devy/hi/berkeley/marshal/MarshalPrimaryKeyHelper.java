package devy.hi.berkeley.marshal;

import com.sleepycat.bind.tuple.TupleOutput;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *  primary key marshalling helper class
 */
public class MarshalPrimaryKeyHelper {

    private TupleOutput keyOutput;

    public MarshalPrimaryKeyHelper(TupleOutput keyOutput) {
        this.keyOutput = keyOutput;
    }

    public MarshalPrimaryKeyHelper marshalPrimaryKey(String key) {
        this.keyOutput.writeString(key);
        return this;
    }

    public MarshalPrimaryKeyHelper marshalPrimaryKey(BigInteger key) {
        this.keyOutput.writeBigInteger(key);
        return this;
    }

    public MarshalPrimaryKeyHelper marshalPrimaryKey(BigDecimal key) {
        this.keyOutput.writeBigDecimal(key);
        return this;
    }

    public MarshalPrimaryKeyHelper marshalPrimaryKey(double key) {
        this.keyOutput.writeDouble(key);
        return this;
    }

    public MarshalPrimaryKeyHelper marshalPrimaryKey(float key) {
        this.keyOutput.writeFloat(key);
        return this;
    }

    public MarshalPrimaryKeyHelper marshalPrimaryKey(int key) {
        this.keyOutput.writeInt(key);
        return this;
    }

    public MarshalPrimaryKeyHelper marshalPrimaryKey(long key) {
        this.keyOutput.writeLong(key);
        return this;
    }

}