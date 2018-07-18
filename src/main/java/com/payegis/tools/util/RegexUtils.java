package com.payegis.tools.util;

import org.apache.log4j.Logger;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/5/25
 * time: 16:52
 * description: 正则表达式工具类
 */
public class RegexUtils {
    private static Logger logger = Logger.getLogger(RegexUtils.class);

    public final static String blankRegex = "\\s*|\t|\r|\n"; // 空白字符正则

    /**
     * 个人信息正则
     */
    public final static String nameRegex = "[\\u4e00-\\u9fa5]+·?[\\u4e00-\\u9fa5]+"; // 中文姓名正则
    public final static String phoneRegex = "^[+]?(?:\\s*8\\s*6)?\\s*1\\s*[3|4|5|6|7|8|9]\\s*(?:\\d\\s*){9}\\s*$"; // 中国移动手机号正则
    public final static String idCardRegex = "(\\d{15})|(\\d{18})|(\\d{17}[xX]{1})"; // 身份证号正则

    /**
     * 数值正则
     */
    public final static String intRegex = "-?\\d+"; // 整数正则
    public final static String floatRegex = "-?\\d+\\.\\d+"; // 浮点数正则
    public final static String numberRegex = "-?\\d+(\\.\\d+)?"; // 数值正则

    /**
     * 金钱正则
     */
    public final static String pointMoneyRegex = "([\\d\\.]+角)";
    public final static String rmbMoneyRegex = "([\\d\\.]+块)|([\\d\\.]+元)";
    public final static String dollarMoneyRegex = "([\\d\\.]+美元)";
    public final static String tenThousandMoneyRegex = "([\\d\\.]+万)";
    public final static String hundredThousandMoneyRegex = "([\\d\\.]+十万)";
    public final static String millionMoneyRegex = "([\\d\\.]+百万)";
    public final static String tenMillionMoneyRegex = "([\\d\\.]+千万)";
    public final static String billionMoneyRegex = "([\\d\\.]+亿)";

    /**
     * 日期时间正则
     */
    public final static String ymdSlashRegex = "(\\d{4}/\\d{1,2}/\\d{1,2})"; // 年/月/日
    public final static String ymSlashRegex = "(\\d{4}/\\d{1,2})"; // 年/月
    public final static String mdy2SlashRegex = "(\\d{1,2}/\\d{1,2}/\\d{2})"; // 月/日/年
    public final static String mdy4SlashRegex = "(\\d{1,2}/\\d{1,2}/\\d{4})"; // 月/日/年
    public final static String y_m_dRegex = "(\\d{4}-\\d{1,2}-\\d{1,2})"; // 年-月-日
    public final static String y_mRegex = "(\\d{4}-\\d{1,2})"; // 年-月
    public final static String yRegex = "(\\d{4})"; // 年
    public final static String ymdRegex = "(\\d{6,8})"; // 年月日
    public final static String ymRegex = "(\\d{5,6})"; // 年月
    public final static String y_m_d_h_m_sRegex = "(\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2})"; // 年-月-日 时:分:秒
    public final static String y_m_d_h_mRegex = "(\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2})";
    public final static String ymdhmsRegex = "(\\d{9,14})"; // 年月日时分秒
    public final static String y_m_d_h_m_s_SRegex = "(\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3})"; // 年-月-日 时:分:秒.毫秒
    public final static String ymdchineseRegex = "(\\d{4}年\\d{1,2}月\\d{1,2}日)";
    public final static String ymdblankhmschineseRegex = "(\\d{4}年\\d{1,2}月\\d{1,2}日\\s\\d{1,2}时\\d{1,2}分\\d{1,2}秒)";
    public final static String ymdhmschineseRegex = "(\\d{4}年\\d{1,2}月\\d{1,2}日\\d{1,2}时\\d{1,2}分\\d{1,2}秒)";
    public final static String ymdchineseblankhmsRegex = "(\\d{4}年\\d{1,2}月\\d{1,2}日\\s\\d{1,2}:\\d{1,2}:\\d{1,2})";
    public final static String ymdchinesehmsRegex = "(\\d{4}年\\d{1,2}月\\d{1,2}日\\d{1,2}:\\d{1,2}:\\d{1,2})";
    public final static String ymdchineseblankhmRegex = "(\\d{4}年\\d{1,2}月\\d{1,2}日\\s\\d{1,2}:\\d{1,2})";
    public final static String ymdchinesehmRegex = "(\\d{4}年\\d{1,2}月\\d{1,2}日\\d{1,2}:\\d{1,2})";
    public final static String ymchineseRegex = "(\\d{4}年\\d{2}月)";
    public final static String ychineseRegex = "(\\d{4}年)";
    public final static String ymdSlash = "\\d{4}/\\d{1,2}/\\d{1,2}";
    public final static String ymSlash = "\\d{4}/\\d{1,2}";
    public final static String mdy2Slash = "\\d{1,2}/\\d{1,2}/\\d{2}";
    public final static String mdy4Slash = "\\d{1,2}/\\d{1,2}/\\d{4}";
    public final static String y_m_d = "\\d{4}-\\d{1,2}-\\d{1,2}";
    public final static String y_m = "\\d{4}-\\d{1,2}";
    public final static String y = "\\d{4}";
    public final static String ymd = "\\d{6,8}";
    public final static String ym = "\\d{5,6}";
    public final static String y_m_d_h_m_s = "\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}";
    public final static String y_m_d_h_m = "\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}";
    public final static String ymdhms = "\\d{9,14}";
    public final static String y_m_d_h_m_s_S = "\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}";
    public final static String ymdchinese = "\\d{4}年\\d{1,2}月\\d{1,2}日";
    public final static String ymdblankhmschinese = "\\d{4}年\\d{1,2}月\\d{1,2}日\\s\\d{1,2}时\\d{1,2}分\\d{1,2}秒";
    public final static String ymdhmschinese = "\\d{4}年\\d{1,2}月\\d{1,2}日\\d{1,2}时\\d{1,2}分\\d{1,2}秒";
    public final static String ymdchineseblankhms = "\\d{4}年\\d{1,2}月\\d{1,2}日\\s\\d{1,2}:\\d{1,2}:\\d{1,2}";
    public final static String ymdchinesehms = "\\d{4}年\\d{1,2}月\\d{1,2}日\\d{1,2}:\\d{1,2}:\\d{1,2}";
    public final static String ymdchineseblankhm = "\\d{4}年\\d{1,2}月\\d{1,2}日\\s\\d{1,2}:\\d{1,2}";
    public final static String ymdchinesehm = "\\d{4}年\\d{1,2}月\\d{1,2}日\\d{1,2}:\\d{1,2}";
    public final static String ymchinese = "\\d{4}年\\d{2}月";
    public final static String ychinese = "\\d{4}年";

}
