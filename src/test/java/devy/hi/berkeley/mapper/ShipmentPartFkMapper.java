package devy.hi.berkeley.mapper;

import devy.hi.berkeley.Part;
import devy.hi.berkeley.Shipment;
import devy.hi.berkeley.ShipmentPartFk;
import devy.hi.berkeley.query.IQuery;
import devy.hi.berkeley.query.QueryBuilder;
import devy.hi.berkeley.query.QueryType;

public class ShipmentPartFkMapper extends AbstractMapper {
    @Override
    public IQuery query(QueryBuilder queryBuilder) {
        return queryBuilder
                .setKeyClass(ShipmentPartFk.class)
                .setValueBaseClass(Shipment.class)
                .setForeignKeyDbClass(Part.class)
                .build(QueryType.FOREIGNKEY);
    }
}
