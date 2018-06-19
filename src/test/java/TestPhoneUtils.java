import com.payegis.tools.personal.PhoneUtils;
import org.apache.log4j.Logger;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/6/15
 * time: 10:09
 * description: 测试PhoneUtils工具类
 */

public class TestPhoneUtils {
    private static Logger logger = Logger.getLogger(TestPhoneUtils.class);

    public static void main(String[] args) {
        String phone = "+86 153 1362 1879";
        System.out.println(PhoneUtils.etlPhone(phone));
    }

}
