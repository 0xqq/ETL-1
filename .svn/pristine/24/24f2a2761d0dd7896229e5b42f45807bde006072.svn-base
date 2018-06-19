package com.payegis.tools.file;

import com.payegis.tools.util.Utils;
import org.apache.log4j.Logger;
import org.apache.tika.Tika;

import java.io.*;
import java.util.*;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/5/15
 * time: 11:38
 * description: 操作文件工具类
 */
public class FileUtils {
    private static Logger logger = Logger.getLogger(FileUtils.class);

    /**
     * description: 读取文件，到list中，一行为一个字符串，未去重
     * param: [filePath]
     * return: java.util.List<java.lang.String>
     * date: 2018/5/15
     * time: 11:42
     */
    public static List<String> readFile(String filePath) {
        List<String> lineList = new ArrayList<>();
        readFile(filePath, lineList);
        return lineList;
    }

    /**
     * description: 读取文件，到Set中，一行为一个字符串，并去重
     * param: [filePath]
     * return: java.util.Set<java.lang.String>
     * date: 2018/6/15
     * time: 9:07
     */
    public static Set<String> readFileDeduplication(String filePath) {
        Set<String> lineSet = new HashSet<>();
        readFile(filePath, lineSet);
        return lineSet;
    }

    /**
    * description: tika读取文件，返回行集合
    * param: [filePath]
    * return: java.util.Set<java.lang.String>
    * time: 2018/6/19 11:27
    */
    public static Set<String> readFileDeDuplByTika(String filePath) {
        Set<String> lineSet = new HashSet<>();
        readFileByTika(filePath, lineSet);
        return lineSet;
    }

    /**
     * description: 读取文件，加载到集合中，一行为一个对象，有可能有去重的功能，这得取决于你传入的集合对象是List还是Set
     * param: [filePath, collection]
     * return: void
     * date: 2018/6/15
     * time: 9:15
     */
    private static void readFile(String filePath, Collection<String> collection) {
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(filePath);
            br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            while (line != null) {
                collection.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            logger.error("read file " + filePath + " exception!", e);
        } finally {
            Utils.closeFileStream(fis, br, null, null);
        }
    }

    /**
     * description: tika读取数据文件，返回行集合
     * param: [filePath, collection]
     * return: void
     * time: 2018/6/19 11:26
     */
    private static void readFileByTika(String filePath, Collection<String> collection) {
        try {
            Tika tika = new Tika();
            String plainText = tika.parseToString(new File(filePath));
            String[] split = plainText.split("\n");
            for (String line : split) {
                collection.add(line);
            }
        } catch (Exception e) {
            logger.error("file line delimeter recognize exception!", e);
        }
    }

    /**
     * description: 在系统当前路径下创建dir文件夹，如果已存在，那么添加自然数后缀，直到创建成功
     * param: [dir]
     * return: java.lang.String
     * date: 2018/5/15
     * time: 18:29
     */
    public static String mkdir(String dir) {
        String dirName = "";
        try {
            String currentPath = System.getProperty("user.dir");
            File file = new File(currentPath + File.separator + dir);
            int i = 1;
            if (!file.exists()) {
                file.mkdir();
                dirName = file.getAbsolutePath();
            } else {
                file = new File(currentPath + File.separator + dir + i);
                while (file.exists()) {
                    i++;
                    file = new File(currentPath + File.separator + dir + i);
                }
                file.mkdir();
                dirName = file.getAbsolutePath();
            }
            logger.info("make directory " + dirName + "!");
        } catch (Exception e) {
            logger.error("make directory exception!", e);
        }
        return dirName;
    }

    /**
     * description: 判断指定路径是否存在，如果不存在，则创建该文件夹
     * param: [filePath]
     * return: java.lang.String
     * date: 2018/5/15
     * time: 18:30
     */
    public static String isExistOrMkdir(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return filePath;
    }

    /**
     * description: 获取文件所在文件夹绝对路径，如果该文件为一个文件夹，那么返回该文件夹绝对路径，如果该文件不存在，那么返回空字符串
     * param: [filePath]
     * return: java.lang.String
     * date: 2018/6/6
     * time: 11:57
     */
    public static String getFileDir(String filePath) {
        String fileDir = "";
        try {
            File file = new File(filePath);
            if (file.isFile()) {
                fileDir = file.getParent();
            } else if (file.isDirectory()) {
                fileDir = filePath;
            } else {
                logger.error("file path " + filePath + " is not a file or directory!");
            }
        } catch (Exception e) {
            logger.error("get file directory from file " + filePath + " exception!", e);
        }
        return fileDir;
    }

    /**
     * description: 覆盖文件，如果文件不存在，则创建
     * param: [filePath, content]
     * return: void
     * time: 2018/6/19 9:55
     */
    public static void coverFile(String filePath, String content) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(filePath);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(content + "\n");
            bw.flush();
        } catch (Exception e) {
            logger.error("cover file " + filePath + " exception!", e);
        } finally {
            Utils.closeFileStream(null, null, fos, bw);
        }
    }

}
