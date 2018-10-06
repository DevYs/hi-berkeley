package devy.hi.berkeley.query;

/**
 * Index constraints를 갖는 SecondaryDatabase를 조회하기 위한 인터페이스입니다.
 * 상위 인터페이스로 IQuery를 상속하였습니다.
 */
public interface IIndexSecondaryQuery extends IQuery {

    String getPrimaryDbName();

    String getKeyName();

}
