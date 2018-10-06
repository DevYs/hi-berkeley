package devy.hi.berkeley.query;

/**
 * Query.class, IndexSecondaryQuery.class, ForeignKeySecondaryQuery.class 등의 멤버변수가 null인지 검사합니다.
 * Query.class, IndexSecondaryQuery.class, ForeignKeySecondaryQuery.class 클래스의 멤버변수들을 사용하기 위해서는 null 값을 갖는
 * 멤버변수가 없어야 합니다.
 */
public class NullChecker {

    public static boolean check(Object... mArr) {
        for(Object m : mArr) {
            if(m == null) {
                return false;
            }
        }

        return true;
    }
}