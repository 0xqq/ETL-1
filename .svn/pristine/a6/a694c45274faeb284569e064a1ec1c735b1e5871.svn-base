package com.payegis.tools.personal;

import com.payegis.tools.datetime.DateTimeUtils;
import com.payegis.tools.string.NumberUtils;
import com.payegis.tools.util.RegexUtils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.*;

/**
 * 身份证验证的工具（支持5位或18位省份证）
 * 身份证号码结构：
 * 17位数字和1位校验码：6位地址码数字，8位生日数字，3位出生时间顺序号，1位校验码。
 * 地址码（前6位）：表示对象常住户口所在县（市、镇、区）的行政区划代码，按GB/T2260的规定执行。
 * 出生日期码，（第七位 至十四位）：表示编码对象出生年、月、日，按GB按GB/T7408的规定执行，年、月、日代码之间不用分隔符。
 * 顺序码（第十五位至十七位）：表示在同一地址码所标示的区域范围内，对同年、同月、同日出生的人编订的顺序号，
 * 顺序码的奇数分配给男性，偶数分配给女性。
 * 校验码（第十八位数）：
 * 十七位数字本体码加权求和公式 s = sum(Ai*Wi), i = 0,,16，先对前17位数字的权求和；
 * Ai:表示第i位置上的身份证号码数字值.Wi:表示第i位置上的加权因.Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2；
 * 计算模 Y = mod(S, 11)
 * 通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2
 */
public class IdCardUtils {
    public static Map<Integer, String> zoneNum = new HashMap<>();
    private static Logger logger = Logger.getLogger(IdCardUtils.class);

    static {
        zoneNum.put(11, "北京");
        zoneNum.put(12, "天津");
        zoneNum.put(13, "河北");
        zoneNum.put(14, "山西");
        zoneNum.put(15, "内蒙古");
        zoneNum.put(21, "辽宁");
        zoneNum.put(22, "吉林");
        zoneNum.put(23, "黑龙江");
        zoneNum.put(31, "上海");
        zoneNum.put(32, "江苏");
        zoneNum.put(33, "浙江");
        zoneNum.put(34, "安徽");
        zoneNum.put(35, "福建");
        zoneNum.put(36, "江西");
        zoneNum.put(37, "山东");
        zoneNum.put(41, "河南");
        zoneNum.put(42, "湖北");
        zoneNum.put(43, "湖南");
        zoneNum.put(44, "广东");
        zoneNum.put(45, "广西");
        zoneNum.put(46, "海南");
        zoneNum.put(50, "重庆");
        zoneNum.put(51, "四川");
        zoneNum.put(52, "贵州");
        zoneNum.put(53, "云南");
        zoneNum.put(54, "西藏");
        zoneNum.put(61, "陕西");
        zoneNum.put(62, "甘肃");
        zoneNum.put(63, "青海");
        zoneNum.put(64, "宁夏");
        zoneNum.put(65, "新疆");
        zoneNum.put(71, "台湾");
        zoneNum.put(81, "香港");
        zoneNum.put(82, "澳门");
        zoneNum.put(91, "外国");
    }

    public static int[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    public static int[] POWER_LIST = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * Description: 身份证验证，null和"" 都是false
     * Param: [idCard]
     * return: boolean
     * Author: CHEN ZUOLI
     * Date: 2018/4/23
     * Time: 15:12
     */
    public static boolean checkIDCard(String idCard) {
        if (idCard == null || (idCard.length() != 15 && idCard.length() != 18))
            return false;
        final char[] cs = idCard.toUpperCase().toCharArray();

        //校验位数
        int power = 0;
        for (int i = 0; i < cs.length; i++) {
            if (i == cs.length - 1 && cs[i] == 'X')
                break;//最后一位可以 是X或x
            if (cs[i] < '0' || cs[i] > '9')
                return false;
            if (i < cs.length - 1) {
                power += (cs[i] - '0') * POWER_LIST[i];
            }
        }

        //校验区位码
        if (!zoneNum.containsKey(Integer.valueOf(idCard.substring(0, 2)))) {
            return false;
        }
        //校验年份
        String year = idCard.length() == 15 ? getIdCardCalendar() + idCard.substring(6, 8) : idCard.substring(6, 10);

        final int iyear = Integer.parseInt(year);
        if (iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR))
            return false;//1900年以前的PASS，超过今年的PASS

        //校验月份
        String month = idCard.length() == 15 ? idCard.substring(8, 10) : idCard.substring(10, 12);
        final int imonth = Integer.parseInt(month);
        if (imonth < 1 || imonth > 12) {
            return false;
        }

        //校验天数
        String day = idCard.length() == 15 ? idCard.substring(10, 12) : idCard.substring(12, 14);
        final int iday = Integer.parseInt(day);
        if (iday < 1 || iday > 31)
            return false;

        //校验"校验码"
        if (idCard.length() == 15)
            return true;
        return cs[cs.length - 1] == PARITYBIT[power % 11];
    }

    /**
     * description: 获取年份前缀
     * param: []
     * return: int
     * date: 2018/6/13
     * time: 15:36
     */
    private static int getIdCardCalendar() {
        GregorianCalendar curDay = new GregorianCalendar();
        int curYear = curDay.get(Calendar.YEAR);
        return Integer.parseInt(String.valueOf(curYear).substring(2));
    }

    /**
     * description: 清洗身份证号：小写x转大写X，不满足身份证号的不作处理
     * param: [idCard]
     * return: java.lang.String
     * author: chenzuoli
     * date: 2018/5/8
     * time: 14:04
     */
    public static String etlIdCard(String idCard) {
        String returnIdCard = idCard;
        try {
            if (idCard.matches(RegexUtils.idCardRegex)) {
                returnIdCard = returnIdCard.toUpperCase();
            }
        } catch (Exception e) {
            logger.error("etl idCard " + idCard + " exception!", e);
        }
        return returnIdCard;
    }

    /**
     * Description: 随机生成18为身份证号，要求格式正确，并合法，但不一定存在该身份证号
     * Param: []
     * return: java.lang.String
     * Author: CHEN ZUOLI
     * Date: 2018/4/23
     * Time: 15:32
     */
    public static String getRandomIdCard() {
        StringBuilder idCard = new StringBuilder("");
        try {
            Set<Integer> zoneNumSet = IdCardUtils.zoneNum.keySet(); // 省位码
            int random = (int) (Math.random() * (IdCardUtils.zoneNum.size()));
            Object[] zoneNums = zoneNumSet.toArray();
            Object zoneCode = zoneNums[random];
//        logger.info("省位码：" + zoneCode);
            String cityCode = NumberUtils.getRandomNum(4); // 区位码
            long mills;
            String birthday = "20180423";
            try {
                Date since = DateTimeUtils.formatY.parse("1900");
                Date now = new Date();
                mills = since.getTime() + (long) (Math.random() * (now.getTime() - since.getTime()));
                birthday = DateTimeUtils.formatYMD.format(new Date(mills)); // 出生年月日
            } catch (ParseException e) {
                e.printStackTrace();
            }
//        logger.info("生日：" + birthday);
            String id = NumberUtils.getRandomNum(3); // 出生顺序号
//        logger.info("顺序号：" + id);
            idCard.append(zoneCode).append(cityCode).append(birthday).append(id);
            char[] chars = idCard.toString().toCharArray();
            int checkCodeNum = 0;
            for (int i = 0; i < chars.length; i++) {
                checkCodeNum += (chars[i] - '0') * IdCardUtils.POWER_LIST[i];
            }
            char checkCodeChar = (char) IdCardUtils.PARITYBIT[checkCodeNum % 11];
//        logger.info("校验码：" + checkCodeChar);
            idCard.append(checkCodeChar);
        } catch (Exception e) {
            logger.error("exception: " + e + ", get random id card exception!");
        }
        return idCard.toString();
    }

}