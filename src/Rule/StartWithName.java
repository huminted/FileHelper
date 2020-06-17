package Rule;

/**
 * @Author humin
 * @Date 2020/1/15 21:40
 */
public class StartWithName extends BaseRule {

    private String startWihStr, endWithStr;

    public StartWithName(String stwStr, String endStr) {
        this.startWihStr = stwStr;
        this.endWithStr = endStr;
    }

    public StartWithName(String startWihStr) {
        this.startWihStr = startWihStr;
    }

    @Override
    public String getName(String fileName) {
        // start - end截取
        if (startWihStr != null && endWithStr != null) {
            if (fileName.startsWith(startWihStr)) {
                return fileName.substring(0, fileName.indexOf(endWithStr));
            }
            // 从start截取文件名 不包含后缀名
        } else if (startWihStr != null) {
            if (fileName.startsWith(startWihStr)) {
                return fileName.substring(0, fileName.indexOf("."));
            }
        }
        return null;
    }
}
