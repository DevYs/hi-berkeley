package devy.hi.berkeley.db;

import java.io.File;

/**
 * EnvironmentBuilder에서 Database의 파일시스템상의 위치를 지정하여 디렉터리를 생성합니다.
 */
public class DatabasePath {

    private String path = "./bdb";

    public DatabasePath() {}

    public DatabasePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File create() {
        File file = new File(this.path);
        if(!file.exists()) {
            file.mkdir();
        }

        return file;
    }

}
