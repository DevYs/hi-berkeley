package devy.hi.berkeley.mapper;

import devy.hi.berkeley.Supplier;
import devy.hi.berkeley.SupplierCityIdx;
import devy.hi.berkeley.query.IQuery;
import devy.hi.berkeley.query.QueryBuilder;
import devy.hi.berkeley.query.QueryType;

public class SupplierCityIndexMapper extends AbstractMapper {

    @Override
    public IQuery query(QueryBuilder queryBuilder) {
        return queryBuilder
                .setKeyClass(SupplierCityIdx.class)
                .setValueBaseClass(Supplier.class)
                .build(QueryType.INDEX);
    }

}