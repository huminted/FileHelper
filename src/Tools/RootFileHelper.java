package Tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author humin
 * @Date 2020/1/15 17:22
 */
public class RootFileHelper {

    private File[] files;
    private List<String> fileNames = new ArrayList<>();

    private volatile static RootFileHelper instance;

    private RootFileHelper(String rootPath) throws IllegalAccessException {
        // 获取根目录下所有的文件
        // 根目录下所有文件集合
        File rootFile = new File(rootPath);
        if (!rootFile.isDirectory()) {
            throw new IllegalArgumentException("路径错误");
        } else {
            files = rootFile.listFiles();
        }
    }

    public static RootFileHelper getInstance(String path) throws IllegalAccessException {
        if (instance == null)
            synchronized (RootFileHelper.class) {
                if (instance == null) {
                    instance = new RootFileHelper(path);
                }
            }
        return instance;
    }

    public File[] getFiles() {
        return files;
    }

    public List<String> getFileNames() {
        for (File file : files) {
            if (file.isFile())
                fileNames.add(file.getName());
        }
        return fileNames;
    }
}
