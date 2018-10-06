package devy.hi.berkeley.query;

/**
 * ForeignKey constraints를 갖는 SecondaryDatabase를 조회하기 위한 인터페이스입니다.
 * 상위 인터페이스로 IQuery, IIndexSecondaryQuery를 상속하였습니다.
 */
public interface IForeignKeySecondaryQuery extends IIndexSecondaryQuery {

    String getForeignKeyDbName();

}
