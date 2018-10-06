package devy.hi.berkeley.marshal;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleOutput;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * secondary key marshalling helper class
 */
public class MarshalSecondaryKeyHelper {

    private String keyName;
    private TupleOutput keyOutput;
    private List<ISecondaryKey> secondaryKeyList;

    public MarshalSecondaryKeyHelper(String keyName, TupleOutput keyOutput) {
        this.keyName = keyName;
        this.keyOutput = keyOutput;
        this.secondaryKeyList = new ArrayList<>();
    }

    public <T extends MarshalledTupleEntry> MarshalSecondaryKeyHelper marshalSecondaryKey(Class<T> cls, String key) {
        this.secondaryKeyList.add(new StringSecondaryKey(cls, key));
        return this;
    }

    public <T extends MarshalledTupleEntry> MarshalSecondaryKeyHelper marshalSecondaryKey(Class<T> cls, BigInteger key) {
        this.secondaryKeyList.add(new BigIntegerSecondaryKey(cls, key));
        return this;
    }

    public <T extends MarshalledTupleEntry> MarshalSecondaryKeyHelper marshalSecondaryKey(Class<T> cls, BigDecimal key) {
        this.secondaryKeyList.add(new BigDecimalSecondaryKey(cls, key));
        return this;
    }

    public <T extends MarshalledTupleEntry> MarshalSecondaryKeyHelper marshalSecondaryKey(Class<T> cls, double key) {
        this.secondaryKeyList.add(new DoubleSecondaryKey(cls, key));
        return this;
    }

    public <T extends MarshalledTupleEntry> MarshalSecondaryKeyHelper marshalSecondaryKey(Class<T> cls, float key) {
        this.secondaryKeyList.add(new FloatSecondaryKey(cls, key));
        return this;
    }

    public <T extends MarshalledTupleEntry> MarshalSecondaryKeyHelper marshalSecondaryKey(Class<T> cls, int key) {
        this.secondaryKeyList.add(new IntSecondaryKey(cls, key));
        return this;
    }

    public <T extends MarshalledTupleEntry> MarshalSecondaryKeyHelper marshalSecondaryKey(Class<T> cls, long key) {
        this.secondaryKeyList.add(new LongSecondaryKey(cls, key));
        return this;
    }

    /**
     * secondary key를 검색하여 mashalling을 실행합니다.
     * @return boolean true가 아니면 UnsupportedOperationException 발생
     */
    public boolean marshal() {
        for(ISecondaryKey secondaryKey : secondaryKeyList) {

            if(!secondaryKey.equalsKey(this.keyName)) {
                continue;
            }

            if(secondaryKey.isKeyNull()) {
                continue;
            }

            secondaryKey.write(this.keyOutput);

            return true;
        }

        throw new UnsupportedOperationException(this.keyName);
    }

    /**
     * Secondary Key의 동작을 정의합니다.
     */
    private interface ISecondaryKey {
        boolean equalsKey(String keyName);
        boolean isKeyNull();
        void write(TupleOutput keyOutput);
    }

    /**
     * Secondary Key의 class, key 정보를 저장합니다.
     */
    private abstract class SecondaryKey implements ISecondaryKey {
        private Class cls;
        protected Object key;

        public SecondaryKey(Class cls, Object key) {
            this.cls = cls;
            this.key = key;
        }

        /**
         * 키의 일치 여부
         * @param keyName String 키
         * @return boolean 키가 일치하면 true, 일치하지 않으면 false
         */
        @Override
        public boolean equalsKey(String keyName) {
            return keyName.equals(cls.getSimpleName());
        }

        /**
         * 키의 null 여부
         * @return boolean 키가 널이라면 true, 널이 아니면 false
         */
        @Override
        public boolean isKeyNull() {
            return this.key == null;
        }
    }

    /**
     * String
     */
    private class StringSecondaryKey extends SecondaryKey {
        public StringSecondaryKey(Class cls, String key) {
            super(cls, key);
        }

        @Override
        public void write(TupleOutput keyOutput) {
            keyOutput.writeString((String) super.key);
        }
    }

    /**
     * BigInteger
     */
    private class BigIntegerSecondaryKey extends SecondaryKey {
        public BigIntegerSecondaryKey(Class cls, BigInteger key) {
            super(cls, key);
        }

        @Override
        public void write(TupleOutput keyOutput) {
            keyOutput.writeBigInteger((BigInteger) super.key);
        }
    }

    /**
     * BigDecimal
     */
    private class BigDecimalSecondaryKey extends SecondaryKey {
        public BigDecimalSecondaryKey(Class cls, BigDecimal key) {
            super(cls, key);
        }

        @Override
        public void write(TupleOutput keyOutput) {
            keyOutput.writeBigDecimal((BigDecimal) super.key);
        }
    }

    /**
     * double
     */
    private class DoubleSecondaryKey extends SecondaryKey {
        public DoubleSecondaryKey(Class cls, double key) {
            super(cls, key);
        }

        @Override
        public void write(TupleOutput keyOutput) {
            keyOutput.writeDouble((double) super.key);
        }
    }

    /**
     * float
     */
    private class FloatSecondaryKey extends SecondaryKey {
        public FloatSecondaryKey(Class cls, float key) {
            super(cls, key);
        }

        @Override
        public void write(TupleOutput keyOutput) {
            keyOutput.writeFloat((float) super.key);
        }
    }

    /**
     * int
     */
    private class IntSecondaryKey extends SecondaryKey {
        public IntSecondaryKey(Class cls, int key) {
            super(cls, key);
        }

        @Override
        public void write(TupleOutput keyOutput) {
            keyOutput.writeInt((int) super.key);
        }
    }

    /**
     * long
     */
    private class LongSecondaryKey extends SecondaryKey {
        public LongSecondaryKey(Class cls, long key) {
            super(cls, key);
        }

        @Override
        public void write(TupleOutput keyOutput) {
            keyOutput.writeLong((long) super.key);
        }
    }

}
