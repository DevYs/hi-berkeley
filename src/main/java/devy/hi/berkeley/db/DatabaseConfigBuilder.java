package devy.hi.berkeley.db;

import com.sleepycat.je.DatabaseConfig;

/**
 * DatabaseConfig 객체를 생성합니다.
 *
 * 2018. 10. 06. DevY
 * 기본 설정으로 DatabaseConfig 생성
 */
public class DatabaseConfigBuilder {

    private boolean transactional = true;
    private boolean allowCreate = true;

    public boolean isTransactional() {
        return transactional;
    }

    public DatabaseConfigBuilder setTransactional(boolean transactional) {
        this.transactional = transactional;
        return this;
    }

    public boolean isAllowCreate() {
        return allowCreate;
    }

    public DatabaseConfigBuilder setAllowCreate(boolean allowCreate) {
        this.allowCreate = allowCreate;
        return this;
    }

    public DatabaseConfig build() {
        return DatabaseConfig.DEFAULT
                .setAllowCreate(this.allowCreate)
                .setTransactional(this.transactional);
    }
}
