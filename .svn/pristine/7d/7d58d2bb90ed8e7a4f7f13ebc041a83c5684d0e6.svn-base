import com.payegis.tools.file.FileDivisionETL;
import com.payegis.tools.string.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/6/15
 * time: 11:55
 * description: 测试FileDivision工具类
 */

public class TestFileDivision {
    private static Logger logger = Logger.getLogger(TestFileDivision.class);

    public static void main(String[] args) {
        StringUtils.init(args, 1, "please enter one data file path!");
        ArrayList<String[]> etled = FileDivisionETL.etl(args[0], ",", new String[]{"int", "name", "phone", "idCard", "default", "phone", "default", "dateTime", "dateTime"});
        etled.forEach(line -> {
            for (String column : line) {
                System.out.println(column);
            }
            System.out.println("============");
        });
    }

}
