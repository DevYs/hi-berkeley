/*-
 * Copyright (C) 2002, 2017, Oracle and/or its affiliates. All rights reserved.
 *
 * This file was distributed by Oracle as part of a version of Oracle Berkeley
 * DB Java Edition made available at:
 *
 * http://www.oracle.com/technetwork/database/database-technologies/berkeleydb/downloads/index.html
 *
 * Please see the LICENSE file included in the top-level directory of the
 * appropriate version of Oracle Berkeley DB Java Edition for a copy of the
 * license and additional information.
 */

package devy.hi.berkeley;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;
import devy.hi.berkeley.marshal.MarshalKeyHelper;

import java.io.Serializable;

/**
 * A Part represents the combined key/data pair for a part entity.
 *
 * <p> In this sample, Part is bound to the stored key/data entry by
 * implementing the MarshalledTupleKeyEntity interface. </p>
 *
 * <p> The binding is "tricky" in that it uses this class for both the stored
 * data entry and the combined entity object.  To do this, the key field(s)
 * are transient and are set by the binding after the data object has been
 * deserialized. This avoids the use of a PartData class completely. </p>
 *
 * <p> Since this class is used directly for data storage, it must be
 * Serializable. </p>
 *
 * @author Mark Hayes
 */
public class Part implements Serializable, MarshalledTupleKeyEntity {

    private transient String partNumber;
    private String name;
    private String color;
    private Weight weight;
    private String city;

    public Part(String partNumber, String name, String color, Weight weight, String city) {
        this.partNumber = partNumber;
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.city = city;
    }

    public final String getPartNumber() {

        return partNumber;
    }

    public final String getName() {

        return name;
    }

    public final String getColor() {

        return color;
    }

    public final Weight getWeight() {

        return weight;
    }

    public final String getCity() {

        return city;
    }

    public String toString() {

        return "[Part: partNumber=" + partNumber +
            " name=" + name +
            " color=" + color +
            " weight=" + weight +
            " city=" + city + ']';
    }

    // --- MarshalledTupleKeyEntity implementation ---

    public void marshalPrimaryKey(TupleOutput keyOutput) {
        MarshalKeyHelper
                .marshalPrimaryKeyHelper(keyOutput)
                .marshalPrimaryKey(this.partNumber);
    }

    public void unmarshalPrimaryKey(TupleInput keyInput) {
        MarshalKeyHelper
                .unMarshalPrimaryKeyHelper(keyInput)
                .unMarshalPrimaryKey(this.partNumber);
    }

    public boolean marshalSecondaryKey(String keyName, TupleOutput keyOutput) {
        /*
            IdxSecondaryQuery 또는 FkSecondaryQuery를 정의하지 않기때문에
            이 메서드는 사용하지 않습니다.
         */
        throw new UnsupportedOperationException(keyName);
    }

    public boolean nullifyForeignKey(String keyName) {
        throw new UnsupportedOperationException(keyName);
    }

}
