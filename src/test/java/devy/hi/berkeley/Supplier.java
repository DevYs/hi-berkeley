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
 * A Supplier represents the combined key/data pair for a supplier entity.
 *
 * <p> In this sample, Supplier is bound to the stored key/data entry by
 * implementing the MarshalledTupleKeyEntity interface. </p>
 *
 * <p> The binding is "tricky" in that it uses this class for both the stored
 * data entry and the combined entity object.  To do this, the key field(s) are
 * transient and are set by the binding after the data object has been
 * deserialized. This avoids the use of a SupplierData class completely. </p>
 *
 * <p> Since this class is used directly for data storage, it must be
 * Serializable. </p>
 *
 * @author Mark Hayes
 */
public class Supplier implements Serializable, MarshalledTupleKeyEntity {

    private transient String supplierNumber;
    private String name;
    private int status;
    private String city;

    public Supplier(String supplierNumber, String name, int status, String city) {
        this.supplierNumber = supplierNumber;
        this.name = name;
        this.status = status;
        this.city = city;
    }

    public final String getSupplierNumber() {

        return supplierNumber;
    }

    public final String getName() {

        return name;
    }

    public final int getStatus() {

        return status;
    }

    public final String getCity() {

        return city;
    }

    public String toString() {

        return "[Supplier: supplierNumber=" + supplierNumber +
            " name=" + name +
            " status=" + status +
            " city=" + city + ']';
    }

    // --- MarshalledTupleKeyEntity implementation ---

    public void marshalPrimaryKey(TupleOutput keyOutput) {
        MarshalKeyHelper
                .marshalPrimaryKeyHelper(keyOutput)
                .marshalPrimaryKey(this.supplierNumber);
    }

    public void unmarshalPrimaryKey(TupleInput keyInput) {
        MarshalKeyHelper
                .unMarshalPrimaryKeyHelper(keyInput)
                .unMarshalPrimaryKey(this.supplierNumber);
    }

    public boolean marshalSecondaryKey(String keyName, TupleOutput keyOutput) {
        return MarshalKeyHelper
                .marshalSecondaryKeyHelper(keyName, keyOutput)
                .marshalSecondaryKey(SupplierCityIdx.class, this.city)
                .marshal();
    }

    public boolean nullifyForeignKey(String keyName) {
        throw new UnsupportedOperationException(keyName);
    }
}
