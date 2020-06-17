package Rule;

/**
 * @Author humin
 * @Date 2020/1/15 19:22
 */
public class SuffixName extends BaseRule {

    private String suffix;

    public SuffixName(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String getName(String fileName) {
        if (fileName.isEmpty()) {
            return null;
        } else {

            if (!fileName.contains(suffix)) return null;
            int lastIndex = fileName.lastIndexOf(suffix);
            return fileName.substring(lastIndex );
        }
    }
}
