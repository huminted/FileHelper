package Tools;

/**
 * @Author humin
 * @Date 2020/1/15 21:16
 */
public class PathTools {

    public static String fixPathWithSlash(String oPath) {
        if (!oPath.endsWith("\\")) {
            return oPath.concat("\\");
        }
        return oPath;
    }

}
