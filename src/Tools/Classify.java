package Tools;

import Rule.BaseRule;
import Rule.SuffixName;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author humin
 * @Date 2020/1/15 17:16
 */
public class Classify {

    private BaseRule Rule;
    private String RootPath = "";
    private RootFileHelper helper;
    private ConcurrentLinkedQueue<String> classifyQueue = new ConcurrentLinkedQueue<>();
    private ExecutorService executor = Executors.newFixedThreadPool(1000);

    private StringBuffer ClassifyWordBf = new StringBuffer();
    int ClassifyWordNum = 0;

    public Classify(String RootPath, BaseRule baseRule) throws IllegalAccessException {
        this.Rule = baseRule;
        this.RootPath = RootPath;
        this.helper = RootFileHelper.getInstance(RootPath);

        ClassifyWordBf.append("已创建文件夹:");

        setClassifyWord(helper.getFileNames());
        makeDir();
        System.out.println("(" + ClassifyWordNum + ")" + ClassifyWordBf.toString());

    }

    // 生成分类队列
    private void setClassifyWord(List<String> files) {
        for (String Filename : files) {
            String classifyWord = Rule.getName(Filename);
            if (classifyWord != null && !classifyQueue.contains(classifyWord)) {
                classifyQueue.add(classifyWord);
            }
        }
    }

    // 创建文件夹
    private void makeDir() {
        while (!classifyQueue.isEmpty()) {
            String classifyWord = classifyQueue.peek();

            //拼接提示语
            ClassifyWordBf.append(" ").append(classifyWord).append(" ");
            ClassifyWordNum++;

            File newFileFold = new File(RootPath + classifyWord);
            if (!newFileFold.exists()) {
                if (newFileFold.mkdirs()) {
                    classifyQueue.poll();
                }
            } else {
                classifyQueue.poll();
            }
            executor.submit(TransFile(helper.getFiles(), classifyWord));
        }
        executor.shutdown();

    }


    //异步移动文件
    private Runnable TransFile(File[] files, String ClassifyWord) {

        return () -> {
            for (File oldFile : files) {
                if (oldFile.isFile()) {
                    if (ClassifyWord.equals(Rule.getName(oldFile.getName()))) {
                        String newFilePath = RootPath + ClassifyWord + "\\" + oldFile.getName();
                        boolean result = oldFile.renameTo(new File(newFilePath));
                        if (result) {
                            System.out.println(newFilePath);
                        }
                    }
                }
            }
        };
    }
}
