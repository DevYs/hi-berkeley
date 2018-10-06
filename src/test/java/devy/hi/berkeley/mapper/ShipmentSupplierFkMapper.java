package devy.hi.berkeley.mapper;

import devy.hi.berkeley.Shipment;
import devy.hi.berkeley.ShipmentSupplierFk;
import devy.hi.berkeley.Supplier;
import devy.hi.berkeley.query.IQuery;
import devy.hi.berkeley.query.QueryBuilder;
import devy.hi.berkeley.query.QueryType;

public class ShipmentSupplierFkMapper extends AbstractMapper {

    @Override
    public IQuery query(QueryBuilder queryBuilder) {
        return queryBuilder
                .setKeyClass(ShipmentSupplierFk.class)
                .setValueBaseClass(Shipment.class)
                .setForeignKeyDbClass(Supplier.class)
                .build(QueryType.FOREIGNKEY);
    }

}