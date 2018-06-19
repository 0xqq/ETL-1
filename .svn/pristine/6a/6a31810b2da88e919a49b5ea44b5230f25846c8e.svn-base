package com.payegis.tools.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Properties;

/**
 * Company: 北京通付盾数据科技有限公司
 * User: chenzuoli
 * Date: 2018/4/27
 * Time: 10:13
 * Description: 动态读取外部指定配置文件参数
 */
public class ExternalPropertyUtils implements Serializable {
    private static Logger logger = Logger.getLogger(ExternalPropertyUtils.class);
    public Properties props;

    private ExternalPropertyUtils(String filePath) {
        props = new Properties();
        readProperties(filePath);
    }

    /**
     * Description: 读取配置文件
     * Param: [fileName]
     * Return: void
     * Author: chenzuoli
     * Date: 2018/4/27
     * Time: 10:16
     */
    private void readProperties(String fileName) {
        logger.info("load property file " + fileName + " start!");
        InputStreamReader inputStream = null;
        try {
            inputStream = new InputStreamReader(new FileInputStream(fileName), "utf8");
            props.load(inputStream);
            logger.info("load property file success!");
        } catch (Exception e) {
            logger.error("load property file exception!");
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("close inputstream exception!");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * description: 获取ExternalPropertyUtils工具类实例，传递配置文件路径
     * param: [filePath]
     * return: com.payegis.tools.util.ExternalPropertyUtils
     * date: 2018/6/13
     * time: 15:47
     */
    public static ExternalPropertyUtils getInstance(String filePath) {
        ExternalPropertyUtils externalPropertyUtils = new ExternalPropertyUtils(filePath);
        return externalPropertyUtils;
    }

}
