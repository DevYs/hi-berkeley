package devy.hi.berkeley;

import com.sleepycat.collections.StoredMap;
import com.sleepycat.collections.StoredValueSet;
import com.sleepycat.collections.TransactionWorker;
import devy.hi.berkeley.db.DatabaseTransaction;
import devy.hi.berkeley.mapper.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

public class BerkeleyDbTest {

    private PartMapper partMapper;
    private SupplierMapper supplierMapper;
    private SupplierCityIndexMapper supplierCityIndexMapper;
    private ShipmentMapper shipmentMapper;
    private ShipmentPartFkMapper shipmentPartFkMapper;
    private ShipmentSupplierFkMapper shipmentSupplierFkMapper;

    @Before
    public void init() {
        this.partMapper = new PartMapper();
        this.supplierMapper = new SupplierMapper();
        this.supplierCityIndexMapper = new SupplierCityIndexMapper();
        this.shipmentMapper = new ShipmentMapper();
        this.shipmentPartFkMapper = new ShipmentPartFkMapper();
        this.shipmentSupplierFkMapper = new ShipmentSupplierFkMapper();
    }

    @Test
    public void test() {
        DatabaseTransaction.run(new TransactionWorker() {
            @Override
            public void doWork() throws Exception {
                addParts();
                addSuppliers();
                addShipments();
                showData();
            }
        });
    }

    private void addParts() {
        final Set parts = this.partMapper.set();
        if (parts.isEmpty()) {
            System.out.println("Adding Parts");
            parts.add(new Part("P1", "Nut", "Red",
                    new Weight(12.0, Weight.GRAMS), "London"));
            parts.add(new Part("P2", "Bolt", "Green",
                    new Weight(17.0, Weight.GRAMS), "Paris"));
            parts.add(new Part("P3", "Screw", "Blue",
                    new Weight(17.0, Weight.GRAMS), "Rome"));
            parts.add(new Part("P4", "Screw", "Red",
                    new Weight(14.0, Weight.GRAMS), "London"));
            parts.add(new Part("P5", "Cam", "Blue",
                    new Weight(12.0, Weight.GRAMS), "Paris"));
            parts.add(new Part("P6", "Cog", "Red",
                    new Weight(19.0, Weight.GRAMS), "London"));
        }
    }

    private void addSuppliers() {
        final Set suppliers = this.supplierMapper.set();
        if (suppliers.isEmpty()) {
            System.out.println("Adding Suppliers");
            suppliers.add(new Supplier("S1", "Smith", 20, "London"));
            suppliers.add(new Supplier("S2", "Jones", 10, "Paris"));
            suppliers.add(new Supplier("S3", "Blake", 30, "Paris"));
            suppliers.add(new Supplier("S4", "Clark", 20, "London"));
            suppliers.add(new Supplier("S5", "Adams", 30, "Athens"));
        }
    }

    private void addShipments() {
        Set shipments = this.shipmentMapper.set();
        if (shipments.isEmpty()) {
            System.out.println("Adding Shipments");
            // 데이터 삽입시 에러가 필요하다면 아래 명령의 주석을 제거하세요.
//            shipments.add(new Shipment("P11", "S11", 300));
            shipments.add(new Shipment("P1", "S1", 300));
            shipments.add(new Shipment("P2", "S1", 200));
            shipments.add(new Shipment("P3", "S1", 400));
            shipments.add(new Shipment("P4", "S1", 200));
            shipments.add(new Shipment("P5", "S1", 100));
            shipments.add(new Shipment("P6", "S1", 100));
            shipments.add(new Shipment("P1", "S2", 300));
            shipments.add(new Shipment("P2", "S2", 400));
            shipments.add(new Shipment("P2", "S3", 200));
            shipments.add(new Shipment("P2", "S4", 200));
            shipments.add(new Shipment("P4", "S4", 300));
            shipments.add(new Shipment("P5", "S4", 400));
        }
    }

    public void showData() {
        StoredValueSet partSet          = this.partMapper.set();
        StoredValueSet supplierSet      = this.supplierMapper.set();
        StoredValueSet shipmentSet      = this.shipmentMapper.set();
        StoredMap supplierCityIndexSet  = this.supplierCityIndexMapper.map();
        StoredMap shipmentMap           = this.shipmentMapper.map();
        StoredMap shipmentPartFk        = this.shipmentPartFkMapper.map();
        StoredMap shipmentSupplierFk    = this.shipmentSupplierFkMapper.map();

        printValues("Parts", partSet.iterator());
        printValues("Suppliers", supplierSet.iterator());
        printValues("Suppliers for City Paris", supplierCityIndexSet.duplicates(new SupplierCityIdx("Paris")).iterator());
        printValues("Shipments", shipmentSet.iterator());
        printValues("Shipments for Part P1, Supplier S1", shipmentMap.duplicates(new ShipmentKey("P1", "S1")).iterator());
        printValues("Shipments for Part P1", shipmentPartFk.duplicates(new ShipmentPartFk("P2")).iterator());
        printValues("Shipments for Supplier S1", shipmentSupplierFk.duplicates(new ShipmentSupplierFk("S2")).iterator());
    }

    /**
     * Print the objects returned by an iterator of entity value objects.
     */
    private void printValues(String label, Iterator iterator) {

        System.out.println("\n--- " + label + " ---");
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }
}