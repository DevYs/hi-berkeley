package devy.hi.berkeley.mapper;

import devy.hi.berkeley.Part;
import devy.hi.berkeley.PartKey;
import devy.hi.berkeley.query.IQuery;
import devy.hi.berkeley.query.QueryBuilder;
import devy.hi.berkeley.query.QueryType;

public class PartMapper extends AbstractMapper {

    @Override
    public IQuery query(QueryBuilder queryBuilder) {
        return queryBuilder
                .setKeyClass(PartKey.class)
                .setValueBaseClass(Part.class)
                .build(QueryType.PRIMARY);
    }

}