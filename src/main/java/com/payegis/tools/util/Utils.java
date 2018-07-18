package com.payegis.tools.util;

import com.payegis.tools.datetime.DateTimeUtils;
import com.payegis.tools.db.ExternalDBCPUtils;
import com.payegis.tools.encrypt.*;
import com.payegis.tools.string.NumberUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Company: 北京通付盾数据科技有限公司
 * User: 陈作立
 * Date: 2018/3/20
 * Time: 10:17
 * Description: 工具类
 */
public class Utils {
    private static Logger logger = Logger.getLogger(Utils.class);

    /**
     * Description: 确定逾期金额区间，根据逾期天数，确定账龄模糊化
     * Param: [dataMap]
     * return: java.lang.String
     * Author: CHEN ZUOLI
     * Date: 2018/4/4
     * Time: 14:49
     */
    public static String overdueScope(String overdueDaysStr) {
        String scope = "";
        Integer overdueDaysInt = NumberUtils.transformFloatStrToInt(overdueDaysStr);
        scope = getOverdueScope(scope, overdueDaysInt);
        return scope;
    }

    /**
     * 确定逾期金额区间，根据逾期天数，确定账龄模糊化
     * <p>
     * param dataMap 贷款信息
     * return 账龄模糊化结果
     */
    public static String overdueScope(HashMap<String, String> dataMap) {
        String scope = dataMap.get("loanBasicInfo.overdueUnclearedMaxTime");
        Integer overdueDays = NumberUtils.transformFloatStrToInt(scope);
        scope = getOverdueScope(scope, overdueDays);
        return scope;
    }

    /**
     * description: 根据逾期天数获取账龄
     * param: [scope, overdueDays]
     * return: java.lang.String
     * date: 2018/6/13
     * time: 15:47
     */
    public static String getOverdueScope(String scope, Integer overdueDays) {
        if (overdueDays != null) {
            if (overdueDays <= 90 && overdueDays >= 0) {
                scope = "A";
            } else if (overdueDays > 90) {
                scope = "B";
            }
        }
        return scope;
    }

    /**
     * 字符串转换：将空字符串、非数值型字符串转换成0字符串，数值型字符串原样输出
     * <p>
     * param num 输入字符串
     * return 输出字符串
     */
    public static String transformString(String num) {
        if (num == null) {
            return "0";
        }
        if ("".equals(num)) {
            return "0";
        }
        if (!num.matches(RegexUtils.numberRegex)) {
            return "0";
        }
        return num;
    }

    /**
     * 确定金额区间
     * <p>
     * param money float类型金额
     * return 金额区间
     */
    public static String moneyScope(Float money) {
        String scope = null;
        if (money == 0) {
            scope = "0";
        } else if (money > 0 && money <= 500) {
            scope = "a";
        } else if (money > 500 && money <= 1000) {
            scope = "b";
        } else if (money > 1000 && money <= 2000) {
            scope = "c";
        } else if (money > 2000 && money <= 5000) {
            scope = "d";
        } else if (money > 5000 && money <= 10000) {
            scope = "e";
        } else if (money > 10000 && money <= 20000) {
            scope = "f";
        } else if (money > 20000 && money <= 30000) {
            scope = "g";
        } else if (money > 30000 && money <= 50000) {
            scope = "h";
        } else if (money > 50000 && money <= 100000) {
            scope = "i";
        } else if (money > 100000) {
            scope = "j";
        }
        return scope;
    }

    /**
     * 确定金额区间
     * <p>
     * param money String类型金额
     * return 金额区间
     */
    public static String moneyScope(String money) {
        String scope = "";
        if (money != null && money.matches(RegexUtils.numberRegex)) {
            float f = Float.parseFloat(money);
            if (f == 0.0f) {
                scope = "0";
            } else if (f > 0.0f && f <= 500.0f) {
                scope = "a";
            } else if (f > 500.0f && f <= 1000.0f) {
                scope = "b";
            } else if (f > 1000.0f && f <= 2000.0f) {
                scope = "c";
            } else if (f > 2000.0f && f <= 5000.0f) {
                scope = "d";
            } else if (f > 5000.0f && f <= 10000.0f) {
                scope = "e";
            } else if (f > 10000.0f && f <= 20000.0f) {
                scope = "f";
            } else if (f > 20000.0f && f <= 30000.0f) {
                scope = "g";
            } else if (f > 30000.0f && f <= 50000.0f) {
                scope = "h";
            } else if (f > 50000.0f && f <= 100000.0f) {
                scope = "i";
            } else if (f > 100000.0f) {
                scope = "j";
            }
        }
        return scope;
    }

    /**
     * Description: 确定金额区间，用区间表示
     * Param: [money]
     * return: java.lang.String
     * Author: CHEN ZUOLI
     * Date: 2018/4/19
     * Time: 11:55
     */
    public static String moneyScopeInterval(String money) {
        String scope = "";
        if ("0".equals(money)) {
            scope = "0";
        } else if ("a".equals(money)) {
            scope = "0-500";
        } else if ("b".equals(money)) {
            scope = "500-1000";
        } else if ("c".equals(money)) {
            scope = "1000-2000";
        } else if ("d".equals(money)) {
            scope = "2000-5000";
        } else if ("e".equals(money)) {
            scope = "5000-10000";
        } else if ("f".equals(money)) {
            scope = "10000-20000";
        } else if ("g".equals(money)) {
            scope = "20000-30000";
        } else if ("h".equals(money)) {
            scope = "30000-50000";
        } else if ("i".equals(money)) {
            scope = "50000-100000";
        } else if ("j".equals(money)) {
            scope = "100000+";
        }
        return scope;
    }

    /**
     * Description: 是否命中flag解析
     * Param: [flag]
     * return: java.lang.String
     * Author: CHEN ZUOLI
     * Date: 2018/4/19
     * Time: 11:56
     */
    public static String getIsHit(String flag) {
        String isHit = "false";
        if (flag != null && flag.length() > 0) {
            if ("1".equals(flag)) {
                isHit = "true";
            }
        }
        return isHit;
    }

    /**
     * 区间转换，适用于金融逾期黑名单金额区间的转换
     * <p>
     * param character 字符串区间
     * return 区间分开，其中s代表开始值，不包括该值，e代表结束值，包括该值。如果出现字段m，那么代表该区间为100000+
     */
    public static JSONObject transformDuration(String character) {
        JSONObject se = new JSONObject();
        if ("0".equals(character)) {
            se.put("s", "0");
            se.put("e", "0");
        } else if ("a".equals(character)) {
            se.put("s", "0");
            se.put("e", "500");
        } else if ("b".equals(character)) {
            se.put("s", "500");
            se.put("e", "1000");
        } else if ("c".equals(character)) {
            se.put("s", "1000");
            se.put("e", "2000");
        } else if ("d".equals(character)) {
            se.put("s", "2000");
            se.put("e", "5000");
        } else if ("e".equals(character)) {
            se.put("s", "5000");
            se.put("e", "10000");
        } else if ("f".equals(character)) {
            se.put("s", "10000");
            se.put("e", "20000");
        } else if ("g".equals(character)) {
            se.put("s", "20000");
            se.put("e", "30000");
        } else if ("h".equals(character)) {
            se.put("s", "30000");
            se.put("e", "50000");
        } else if ("i".equals(character)) {
            se.put("s", "50000");
            se.put("e", "100000");
        } else if ("j".equals(character)) {
            se.put("m", "1");
        } else {
            se.put("s", "0");
            se.put("e", "0");
        }
        return se;
    }

    /**
     * Description: 获取两个日期间的相隔天数
     * Param: [time1, time2]
     * Return: java.lang.String
     * Author: chenzuoli
     * Date: 2018/4/27
     * Time: 9:21
     */
    public static String dateInterval(String time1, String time2) {
        String dateInterval = "";
        try {
            if (time1 == null || time1.trim().length() == 0) {
                return "";
            }
            if (time2 == null || time2.trim().length() == 0) {
                return "";
            }
            java.util.Date date1 = DateTimeUtils.parseStringToDate(time1);
            java.util.Date date2 = DateTimeUtils.parseStringToDate(time2);
            String dateStr1 = DateTimeUtils.formatYMD.format(date1);
            String dateStr2 = DateTimeUtils.formatYMD.format(date2);
            java.util.Date date11 = DateTimeUtils.formatYMD.parse(dateStr1);
            java.util.Date date22 = DateTimeUtils.formatYMD.parse(dateStr2);
            dateInterval = String.valueOf((Math.abs(date11.getTime() - date22.getTime()) / (1000 * 60 * 60 * 24)));
        } catch (Exception e) {
            logger.error("date interval calculate exception: " + time1 + ", " + time2, e);
        }
        return dateInterval;
    }

    /**
     * Description: 解密数据
     * Param: [encryptData, publicKey, encryptKey]
     * return: java.lang.String
     * Author: CHEN ZUOLI
     * Date: 2018/4/12
     * Time: 9:31
     */
    public static String decryption(String encryptData, String publicKey, String encryptKey) {
        String returnStr = "";
        try {
            JSONObject jsonResult = JSONObject.fromObject(encryptData);
            String sign = jsonResult.optString("signature");
            String datas = jsonResult.optString("contents");
            boolean isTrue = RSACoder.verify(Coder.decryptBASE64(datas), publicKey, sign);
            if (isTrue) {
                returnStr = new String(AESCoder.decrypt(Coder.decryptBASE64(datas), encryptKey));
            } else {
                logger.error("验签失败!");
            }
        } catch (Exception e) {
            logger.error("decryption failed: encryptData=" + encryptData + ", publicKey=" + publicKey + ", encryptKey=" + encryptKey, e);
        }
        return returnStr;
    }

    /**
     * description: 关闭读文件用到的FileInputStream和BufferedReader流
     * param: [fis, br]
     * return: void
     * author: chenzuoli
     * date: 2018/5/8
     * time: 15:25
     */
    public static void closeFileStream(InputStream is, Reader br, OutputStream os, Writer bw) {
        try {
            if (is != null) {
                is.close();
            }
            if (br != null) {
                br.close();
            }
            if (os != null){
                os.close();
            }
            if (bw != null){
                bw.close();
            }
        } catch (IOException e) {
            logger.error("close stream io exception!", e);
        }
    }

    /**
     * description: 查询mysql表，获取结果条数
     * param: [countSql]
     * return: int
     * date: 2018/5/14
     * time: 9:19
     */
    public static long countNum(ExternalDBCPUtils externalDBCPUtils, String countSql, Object... params) {
        long count = 0;
        try {
            List<Map<String, Object>> queryResult = externalDBCPUtils.executeQuery(countSql, params);
            if (queryResult != null && queryResult.size() > 0) {
                Map<String, Object> record = queryResult.get(0);
                Collection<Object> values = record.values();
                Iterator<Object> iterator = values.iterator();
                if (iterator.hasNext()) {
                    Object next = iterator.next();
                    count = (Long) next;
                }
            }
        } catch (Exception e) {
            logger.error("count sql execute query exception!", e);
        }
        return count;
    }

    /**
     * description: 获取状态码含义
     * param: [statCode]
     * return: java.lang.String
     * date: 2018/5/24
     * time: 10:47
     */
    public static String getStatCodeMean(String statCode) {
        String codeMeaning = "";
        if ("1100".equals(statCode)) {
            codeMeaning = "身份证结果一致";
        } else if ("1101".equals(statCode)) {
            codeMeaning = "结果不一致：身份证号码不一致";
        } else if ("1103".equals(statCode)) {
            codeMeaning = "无此号，请到户籍所在地核查";
        } else if ("1104".equals(statCode)) {
            codeMeaning = "数据处理中，请重新查询";
        } else if ("1190".equals(statCode)) {
            codeMeaning = "身份证验证网络超时，请联系运营人员";
        } else if ("4000".equals(statCode)) {
            codeMeaning = "余额不足，请充值";
        } else if ("4001".equals(statCode)) {
            codeMeaning = "参数错误或参数不能为空";
        } else if ("4002".equals(statCode)) {
            codeMeaning = "签名校验失败";
        } else if ("4003".equals(statCode)) {
            codeMeaning = "网络异常，请重试";
        }
        return codeMeaning;
    }

    /**
     * JsonObject中的时间比较，取出最近的一条多平台借贷结果
     * <p>
     * param list 包含有JsonObject的list
     * return 最近的一个JsonObject
     */
    public static JSONObject getRecentMutiLoanJsonObject(ArrayList<JSONObject> list) {
        JSONObject jsonObject = new JSONObject();
        if (list.size() > 0) {
            jsonObject = list.get(0);
        }
        if (list.size() > 1) {
            for (int i = 1; i < list.size(); i++) {
                JSONObject jsonObject1 = list.get(i);
                jsonObject = getRecentMutiLoanTime(jsonObject, jsonObject1);
            }
        }
        return jsonObject;
    }

    /**
     * 金融黑名单结果中比较时间，取最近的结果
     * <p>
     * param timeJ1 时间1
     * param timeJ2 时间2
     * return 最近的时间的Json串，如果时间相等，那么去逾期金额大的那个
     */
    public static JSONObject getRecentMutiLoanTime(JSONObject timeJ1, JSONObject timeJ2) {
        JSONObject recentTimeJ = new JSONObject();
        try{
            String time1 = timeJ1.optString("b");
            String time2 = timeJ2.optString("b");
            String range1 = timeJ1.optString("k");
            String range2 = timeJ2.optString("k");
            if ("".equals(range1) || "z".equals(range1)) {
                range1 = " ";
            }
            if ("".equals(range2) || "z".equals(range2)) {
                range2 = " ";
            }
            try {
                java.util.Date date1 = DateTimeUtils.parseStringToDate(time1);
                java.util.Date date2 = DateTimeUtils.parseStringToDate(time2);
                if (date1 != null && date2 != null) {
                    if (date1.getTime() > date2.getTime()) {
                        recentTimeJ = timeJ1;
                    } else if (date1.getTime() < date2.getTime()) {
                        recentTimeJ = timeJ2;
                    } else if (range1.charAt(0) >= range2.charAt(0)) {
                        recentTimeJ = timeJ1;
                    } else {
                        recentTimeJ = timeJ2;
                    }
                } else if (date1 != null) {
                    recentTimeJ = timeJ1;
                } else if (date2 != null) {
                    recentTimeJ = timeJ2;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e){
            logger.error("get recent time exception, one is " + timeJ1 + ", another is " + timeJ2, e);
        }
        return recentTimeJ;
    }

    /**
     * JsonObject中的时间比较，取出最近的一条数据
     * <p>
     * param list 包含有JsonObject的list
     * return 最近的一个JsonObject
     */
    public static JSONObject getRecentOverdueJsonObject(ArrayList<JSONObject> list) {
        JSONObject jsonObject = new JSONObject();
        if (list.size() > 0) {
            jsonObject = list.get(0);
        }
        if (list.size() > 1) {
            for (int i = 1; i < list.size(); i++) {
                JSONObject jsonObject1 = list.get(i);
                jsonObject = getRecentOverdueTime(jsonObject, jsonObject1);
            }
        }
        return jsonObject;
    }

    /**
     * 比较时间，取最近的时间
     * <p>
     * param timeJ1 时间1，包含逾期金额
     * param timeJ2 时间2，包含逾期金额
     * return 最近的时间的Json串，如果时间相等，那么去逾期金额大的那个
     */
    public static JSONObject getRecentOverdueTime(JSONObject timeJ1, JSONObject timeJ2) {
        JSONObject recentTimeJ = new JSONObject();
        try{
            String time1 = timeJ1.optString("g");
            String time2 = timeJ2.optString("g");
            String range1 = timeJ1.optString("l");
            String range2 = timeJ2.optString("l");
            if ("".equals(range1) || "z".equals(range1)) {
                range1 = " ";
            }
            if ("".equals(range2) || "z".equals(range2)) {
                range2 = " ";
            }
            try {
                java.util.Date date1 = DateTimeUtils.parseStringToDate(time1);
                java.util.Date date2 = DateTimeUtils.parseStringToDate(time2);
                if (date1 != null && date2 != null) {
                    if (date1.getTime() > date2.getTime()) {
                        recentTimeJ = timeJ1;
                    } else if (date1.getTime() < date2.getTime()) {
                        recentTimeJ = timeJ2;
                    } else if (range1.charAt(0) >= range2.charAt(0)) {
                        recentTimeJ = timeJ1;
                    } else {
                        recentTimeJ = timeJ2;
                    }
                } else if (date1 != null) {
                    recentTimeJ = timeJ1;
                } else if (date2 != null) {
                    recentTimeJ = timeJ2;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if ("".equals(recentTimeJ.optString("l"))) {
                recentTimeJ.put("l", "z");
            }
        } catch (Exception e){
            logger.error("get recent time exception, one is " + timeJ1 + ", another is " + timeJ2, e);
        }
        return recentTimeJ;
    }

    /**
     * MD5+加盐加密
     * <p>
     * param inData 输入字符串
     * return 加密后结果
     */
    public static String encryptData(String inData) {
        String outData = "";
        if (inData != null && !"".equals(inData)) {
            String md5 = MD5Utils.strToMd5_32(inData);
            String str9 = md5.substring(8, 9);
            char[] chars = str9.toCharArray();
            int num = chars[0];
            int saltLength = num % 10 + 6;
            String saltCode = md5.substring(0, saltLength);
            outData = ShaUtils.SHA512(inData, saltCode);
        }
        return outData;
    }

}
