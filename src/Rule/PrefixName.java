package Rule;

/**
 * @Author humin
 * @Date 2020/1/15 20:01
 */
public class PrefixName extends BaseRule {


    private String prefix;

    public PrefixName(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getName(String fileName) {

        if (!fileName.contains(prefix)) return null;
        return fileName.substring(0, fileName.indexOf(prefix));
    }
}
