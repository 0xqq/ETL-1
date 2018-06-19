import com.payegis.tools.file.FileUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/6/15
 * time: 9:18
 * description: 测试文件工具类FileUtils
 */

public class TestFileUtils {
    private static Logger logger = Logger.getLogger(TestFileUtils.class);

    public static void main(String[] args) {
        String filePath = "d:\\pp\\conf\\dbtest.properties";
        List<String> strings = FileUtils.readFile(filePath);
        strings.forEach(line -> System.out.println(line));
        System.out.println("--------------------------");
        Set<String> lineSet = FileUtils.readFileDeduplication(filePath);
        lineSet.forEach(line -> System.out.println(line));
    }

}
