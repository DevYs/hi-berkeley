package devy.hi.berkeley.db;

import com.sleepycat.je.SecondaryConfig;

/**
 * SecondaryConfig 객체를 생성합니다.
 * transactional, allowCreate, sortedDuplicates 등의 기본값으로 true로 설정합니다.
 */
public class SecondaryConfigBuilder {

    private boolean transactional = true;
    private boolean allowCreate = true;
    private boolean sortedDuplicates = true;

    public SecondaryConfig build() {
        return (SecondaryConfig) new SecondaryConfig()
                                    .setTransactional(this.transactional)
                                    .setAllowCreate(this.allowCreate)
                                    .setSortedDuplicates(this.sortedDuplicates);
    }

    public boolean isTransactional() {
        return transactional;
    }

    public SecondaryConfigBuilder setTransactional(boolean transactional) {
        this.transactional = transactional;
        return this;
    }

    public boolean isAllowCreate() {
        return allowCreate;
    }

    public SecondaryConfigBuilder setAllowCreate(boolean allowCreate) {
        this.allowCreate = allowCreate;
        return this;
    }

    public boolean isSortedDuplicates() {
        return sortedDuplicates;
    }

    public SecondaryConfigBuilder setSortedDuplicates(boolean sortedDuplicates) {
        this.sortedDuplicates = sortedDuplicates;
        return this;
    }
}