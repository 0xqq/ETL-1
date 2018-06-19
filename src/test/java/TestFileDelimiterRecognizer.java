import com.payegis.tools.file.FileDelimiterRecognizer;
import com.payegis.tools.file.FileDivisionETL;
import com.payegis.tools.string.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/6/19
 * time: 11:40
 * description: 测试文件行分隔符自动识别工具
 */
public class TestFileDelimiterRecognizer {
    private static Logger logger = Logger.getLogger(TestFileDelimiterRecognizer.class);

    public static void main(String[] args) {
        manualSpecifiedDelimiter(args, "$");
    }

    /**
     * description: 从文件行分隔符库中自动判断文件分隔符
     * param: [args]
     * return: void
     * time: 2018/6/19 11:55
     */
    private static void autoDetectDelimiter(String[] args) {
        StringUtils.init(args, 2, "please enter two file path: 1.delimiter db file path;\n2.data file path!");
        String bestDelimiter = FileDelimiterRecognizer.bestDelimiter(args[0], args[1]);
        System.out.println(bestDelimiter);
    }

    /**
     * description: 手动指定文件行分隔符，如果该行分隔符不存在于分隔符库中，那么添加进去
     * param: [args, delimiter]
     * return: void
     * time: 2018/6/19 12:01
     */
    private static void manualSpecifiedDelimiter(String[] args, String delimiter) {
        StringUtils.init(args, 2, "please enter two file path: 1.delimiter db file path;\n2.data file path!");
        ArrayList<String[]> etled = FileDivisionETL.etl(args[0], args[1], delimiter, new String[]{"int", "name", "phone", "idCard", "default", "phone", "default", "dateTime", "dateTime"});
        etled.forEach(line -> {
            for (int i = 0; i < line.length; i++) {
                System.out.print(line[i] + "\t");
            }
            System.out.println();
        });
    }

}
