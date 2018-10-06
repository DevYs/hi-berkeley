package devy.hi.berkeley.mapper;

import devy.hi.berkeley.Shipment;
import devy.hi.berkeley.ShipmentKey;
import devy.hi.berkeley.query.IQuery;
import devy.hi.berkeley.query.QueryBuilder;
import devy.hi.berkeley.query.QueryType;

public class ShipmentMapper extends AbstractMapper {

    @Override
    public IQuery query(QueryBuilder queryBuilder) {
        return queryBuilder
                .setKeyClass(ShipmentKey.class)
                .setValueBaseClass(Shipment.class)
                .build(QueryType.PRIMARY);
    }

}