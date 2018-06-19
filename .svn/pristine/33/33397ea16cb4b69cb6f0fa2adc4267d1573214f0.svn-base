import com.payegis.tools.string.StringUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/6/15
 * time: 13:46
 * description: 测试StringUtils工具类
 */
public class TestStringUtils {
    private static Logger logger = Logger.getLogger(TestStringUtils.class);

    public static void main(String[] args) {
        t1();
    }

    private static void t1(){
        String str1 = "ｓｅｆｅａ１２３５daefw23r3";
        String str2 = "null";
        String str3 = "ｎｕｌl";
        String s1 = StringUtils.etlJsonStr(str1);
        String s2 = StringUtils.etlJsonStr(str2);
        String s3 = StringUtils.etlJsonStr(str3);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }

    private static void t2(){
        String str = "null";
        boolean b = StringUtils.checkValidJsonObjectStr(str);
        System.out.println(b);
        System.out.println("---------");
        JSONObject jsonObject = JSONObject.fromObject(str);
        System.out.println(jsonObject);
        System.out.println("----------");
        JSONObject jsonObject1 = JSONObject.fromObject("{}");
        System.out.println(jsonObject1);
        System.out.println("-------");
        JSONObject jsonObject2 = JSONObject.fromObject(null);
        System.out.println(jsonObject2);
    }

}
