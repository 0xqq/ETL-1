package com.payegis.tools.personal;

import com.payegis.tools.util.RegexUtils;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/6/13
 * time: 13:16
 * description: 姓名工具类
 */
public class NameUtils {
    private static Logger logger = Logger.getLogger(NameUtils.class);

    /**
     * description: 姓名中除了 汉字和· 应该没有其他字符了，所以删除特殊字符或者字母等
     * param: [name]
     * return: java.lang.String
     * date: 2018/6/13
     * time: 15:38
     */
    public static String etlName(String name) {
        String returnName = name;
        try {
            if (name != null) {
                if (!name.matches(RegexUtils.nameRegex)) {
                    StringBuilder sb = new StringBuilder("");
                    Pattern pattern = Pattern.compile("([·\\u4e00-\\u9fa5]+)");
                    Matcher matcher = pattern.matcher(name);
                    while (matcher.find()) {
                        String group = matcher.group();
                        sb.append(group);
                    }
                    returnName = sb.toString();
                }
            } else {
                returnName = "";
            }
        } catch (Exception e) {
            logger.error("etl name " + name + " exception!", e);
        }
        return returnName;
    }

    /**
     * description: 生成假文字 生成指定个数的随机中文字符
     * param: [num]
     * return: java.lang.String
     * date: 2018/6/13
     * time: 15:38
     */
    public static String getRandomChinese(int num) {
        StringBuilder sb = new StringBuilder("");
        try {
            for (int i = 0; i < num; i++) {
                sb.append((char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))));
            }
        } catch (Exception e) {
            logger.error("exception: " + e + ", get random chinese exception!");
        }
        return sb.toString();
    }

    /**
     * description: 获取随机姓名，2-3位
     * param: []
     * return: java.lang.String
     * date: 2018/6/13
     * time: 15:38
     */
    public static String getRandomName() {
        StringBuilder sb = new StringBuilder("");
        int[] randomNameLength = new int[]{2, 3};
        int index = 0;
        if (Math.random() > 0.5d) index = 1;
        try {
            for (int i = 0; i < randomNameLength[index]; i++) {
                sb.append((char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))));
            }
        } catch (Exception e) {
            logger.error("exception: " + e + ", get random chinese exception!");
        }
        return sb.toString();
    }

}
