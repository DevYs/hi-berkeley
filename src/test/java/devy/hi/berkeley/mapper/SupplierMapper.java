package devy.hi.berkeley.mapper;

import devy.hi.berkeley.Supplier;
import devy.hi.berkeley.SupplierKey;
import devy.hi.berkeley.query.IQuery;
import devy.hi.berkeley.query.QueryBuilder;
import devy.hi.berkeley.query.QueryType;

public class SupplierMapper extends AbstractMapper {

    @Override
    public IQuery query(QueryBuilder queryBuilder) {
        return queryBuilder
                .setKeyClass(SupplierKey.class)
                .setValueBaseClass(Supplier.class)
                .build(QueryType.PRIMARY);
    }

}
