package devy.hi.berkeley.db;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

/**
 * 데이터베이스의 환경을 설정한 후 Environment 객체를 생성합니다.
 *
 * 데이터베이스 파일이 저장되는 기본 경로는 './bdb'입니다.
 * DatabasePath는 설정한 경로에 디렉터리가 없으면 생성한 후 File 객체를 생성합니다.
 *
 * transactional고 allowCreate는 기본값으로 true로 설정됩니다.
 * 더 많은 설정을 위해서는 EnvironmentConfig를 외부에서 생성하여 주입하여야 합니다.
 *
 */
public class EnvironmentBuilder {

    private DatabasePath databasePath;
    private EnvironmentConfig environmentConfig;
    private boolean defaultTransactional = true;
    private boolean defaultAllowCreate = true;

    /**
     * Environment 생성
     * @return Environment
     */
    public Environment build() {

        if(this.databasePath == null) {
            this.databasePath = new DatabasePath();
        }

        if(this.environmentConfig == null) {
            this.environmentConfig =
                    EnvironmentConfig.DEFAULT
                        .setAllowCreate(this.defaultAllowCreate)
                        .setTransactional(this.defaultTransactional);
        }

        return new Environment(this.databasePath.create(), this.environmentConfig);
    }

    /**
     * 데이터베이스의 경로를 지정합니다.
     * @param path String 데이터베이스 경로
     * @return EnvironmentBuilder
     */
    public EnvironmentBuilder setDatabasePath(String path) {
        this.databasePath = new DatabasePath(path);
        return this;
    }

    /**
     * EnvironmentConfig의 객체를 주입합니다.
     *
     * EnvironmentBuilder는 allowCreate, transactional 등의 최소한의 값만 설정합니다.
     * 더 많은 설정을 가진 EnvironmentConfig 주입하려면 EnvironmentBuilder 외부에서 EnvironmentConfig 객체를 생성하여 설정값을 세팅한 후에
     * 해당 메서드를 사용하여야 합니다.
     *
     * @param environmentConfig EnvironmentConfig 데이터베이스 설정 객체
     * @return EnvironmentBuilder
     */
    public EnvironmentBuilder setEnvironmentConfig(EnvironmentConfig environmentConfig) {
        this.environmentConfig = environmentConfig;
        return this;
    }

}