import Rule.BaseRule;
import Rule.PrefixName;
import Rule.SuffixName;
import Tools.Classify;
import Tools.ParseArg;
import Tools.PathTools;

/**
 * @Author humin
 * @Date 2020/1/15 16:53
 */
public class NewHelper {

    public static void main(String[] args) throws IllegalAccessException {

        ParseArg parseArg = null;

//        parseArg=new ParseArg("-s","doc","C:\\Users\\humin\\Desktop");
        if (args.length <= 1) {
            parseArg = new ParseArg(args[0]);
        }
        if (args.length == 3) {
            parseArg = new ParseArg(args[0], args[1], args[2]);
        }
        if (args.length == 4) {
            parseArg = new ParseArg(args[0], args[1], args[2], args[3]);
        }

        parseArg.Parse();


    }
}
