package com.payegis.tools.file;

import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by admin on 2018/04/02.
 */
public class AppendToFile {
    private static Logger logger = Logger.getLogger(AppendToFile.class);

    /**
     * A方法追加文件：使用RandomAccessFile
     */
    public static void appendMethodA(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (Exception e) {
            logger.error("append content " + content + " to file " + fileName + " exception!", e);
        }
    }

    /**
     * B方法追加文件：使用FileWriter
     */
    public static void appendMethodB(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            logger.error("append content " + content + " to file " + fileName + " exception!", e);
        }
    }

}
