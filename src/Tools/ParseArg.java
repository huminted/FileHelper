package Tools;

import Rule.BaseRule;
import Rule.PrefixName;
import Rule.StartWithName;
import Rule.SuffixName;

/**
 * @Author humin
 * @Date 2020/1/15 16:56
 */
public class ParseArg {
    private String arg;
    private String RootPath;
    private String classifyWord;
    private String classifyWordEnd;

    public ParseArg(String arg) {
        this.arg = arg;
    }

    public ParseArg(String arg, String classifyWord, String Path) {
        this.arg = arg;
        this.classifyWord = classifyWord;
        this.RootPath = PathTools.fixPathWithSlash(Path);
    }

    public ParseArg(String arg, String classifyWord, String classifyWordEnd, String Path) {
        this.arg = arg;
        this.classifyWord = classifyWord;
        this.RootPath = PathTools.fixPathWithSlash(Path);
        this.classifyWordEnd = classifyWordEnd;
    }


    public void Parse() throws IllegalAccessException {
        BaseRule baseRule = null;
        switch (arg) {
            case "-h":
                System.out.println("[后缀分类] -s suffix 'Your Path' " + "\n" +
                        "[前缀分类] -p prefix 'Your Path' " + "\n" +
                        "[startWith分类] -st 'Your Path'");
                break;
            case "-s":
                baseRule = new SuffixName(classifyWord);
                break;
            case "-p":
                baseRule = new PrefixName(classifyWord);
                break;
            case "-st":
                baseRule = new StartWithName(classifyWord);
                break;
            case "-stw":
                baseRule = new StartWithName(classifyWord, classifyWordEnd);
                break;
            default:
                System.out.println("参数无效");
                break;

        }

        new Classify(RootPath, baseRule);

    }


}
