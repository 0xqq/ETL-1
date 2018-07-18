package com.payegis.tools.db;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/5/17 13:04
 * description: hdfs工具类
 */
public class HDFSUtils implements Serializable{
    private static Logger logger = Logger.getLogger(HDFSUtils.class);

    /**
     * description: 创建新文件
     * param: [dst, contents]
     * return: void
     * date: 2018/5/17 13:06
     */
    public static void createFile(Configuration conf, String dst, byte[] contents) {
        logger.info("create file " + dst);
        try {
            File file = new File(dst);
            if (file.exists()) {
                logger.info("file " + dst + " exists!");
                return;
            }
            FileSystem fs = FileSystem.get(conf);
            Path dstPath = new Path(dst); //目标路径
            FSDataOutputStream outputStream = fs.create(dstPath); //打开一个输出流
            outputStream.write(contents);
            outputStream.close();
            fs.close();
            logger.info("文件创建成功！");
        } catch (Exception e) {
            logger.error("create " + dst + "hdfs file exception!", e);
        }
    }

    /**
     * description: 上传本地文件
     * param: [src, dst]
     * return: void
     * date: 2018/5/17
     * time: 13:06
     */
    public static void uploadFile(Configuration conf, String src, String dst) {
        logger.info("upload file " + src);
        try {
            File file = new File(src);
            if (!file.exists()) {
                logger.error("file " + src + " does not exist!");
                return;
            }
            FileSystem fs = FileSystem.get(conf);
            Path srcPath = new Path(src); //本地上传文件路径
            Path dstPath = new Path(dst); //hdfs目标路径
            //调用文件系统的文件复制函数,前面参数是指是否删除原文件，true为删除，默认为false
            fs.copyFromLocalFile(false, srcPath, dstPath);

            //打印文件路径
            logger.info("Upload to " + conf.get("fs.defaultFS"));
            logger.info("------------list files------------");
            FileStatus[] fileStatus = fs.listStatus(dstPath);
            for (FileStatus fileStat : fileStatus) {
                logger.info(fileStat.getPath());
            }
            fs.close();
        } catch (Exception e) {
            logger.error("upload file " + src + " exception!", e);
        }
    }

    /**
     * description: 文件重命名
     * param: [oldName, newName]
     * return: void
     * date: 2018/5/17
     * time: 13:06
     */
    public static void rename(Configuration conf, String oldName, String newName) {
        logger.info("rename " + oldName + " to " + newName + "!");
        try {
            FileSystem fs = FileSystem.get(conf);
            Path oldPath = new Path(oldName);
            Path newPath = new Path(newName);
            boolean isok = fs.rename(oldPath, newPath);
            if (isok) {
                logger.info("rename ok!");
            } else {
                logger.info("rename failure");
            }
            fs.close();
        } catch (Exception e) {
            logger.error("rename hdfs file or directory " + oldName + " exception!", e);
        }
    }

    /**
     * description: 删除文件
     * param: [filePath]
     * return: void
     * date: 2018/5/17
     * time: 13:06
     */
    public static void delete(Configuration conf, String filePath) {
        logger.info("delete file " + filePath + "!");
        try {
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path(filePath);
            boolean isok = fs.deleteOnExit(path);
            if (isok) {
                logger.info("delete ok!");
            } else {
                logger.info("delete failure");
            }
            fs.close();
        } catch (Exception e) {
            logger.error("delete file " + filePath + " exception!", e);
        }
    }

    /**
     * description: 创建目录
     * param: [path]
     * return: void
     * date: 2018/5/17
     * time: 13:06
     */
    public static boolean mkdir(Configuration conf, String path) {
        logger.info("mkdir " + path + " on hdfs!");
        boolean isok = false;
        try {
            FileSystem fs = FileSystem.get(conf);
            Path srcPath = new Path(path);
            isok = fs.mkdirs(srcPath);
            if (isok) {
                logger.info("create " + path + " dir ok!");
            } else {
                logger.info("create " + path + " dir failure!");
            }
            fs.close();
        } catch (Exception e) {
            logger.error("mkdir " + path + " exception!", e);
        }
        return isok;
    }

    /**
     * description: 读取文件的内容
     * param: [filePath]
     * return: void
     * date: 2018/5/17
     * time: 13:06
     */
    public static void readFile(Configuration conf, String filePath) {
        logger.info("read file " + filePath + " on hdfs!");
        try {
            FileSystem fs = FileSystem.get(conf);
            Path srcPath = new Path(filePath);
            InputStream in = null;
            try {
                in = fs.open(srcPath);
                IOUtils.copyBytes(in, System.out, 4096, false); //复制到标准输出流
            } finally {
                IOUtils.closeStream(in);
            }
            fs.close();
        } catch (Exception e) {
            logger.error("read hdfs file " + filePath + " exception!", e);
        }
    }

    /**
     * description: 遍历指定目录(direPath)下的所有文件
     * param: [direPath]
     * return: void
     * date: 2018/5/17
     * time: 13:07
     */
    public static void getDirectoryFromHdfs(Configuration conf, String dirPath) {
        logger.info("get directory " + dirPath + "from hdfs!");
        try {
            FileSystem fs = FileSystem.get(URI.create(dirPath), conf);
            FileStatus[] filelist = fs.listStatus(new Path(dirPath));
            for (int i = 0; i < filelist.length; i++) {
                logger.info("_________" + dirPath + "目录下所有文件______________");
                FileStatus fileStatus = filelist[i];
                logger.info("Name:" + fileStatus.getPath().getName());
                logger.info("Size:" + fileStatus.getLen());
                logger.info("Path:" + fileStatus.getPath());
            }
            fs.close();
        } catch (Exception e) {
            logger.error("get directory from hdfs path " + dirPath + " exception!", e);
        }
    }

    /**
    * description: 判断hdfs中是否存在该路径
    * param: [conf, hdfsFilePath]
    * return: boolean
    * date: 2018/5/18
    * time: 13:52
    */
    public static boolean hdfsFileExists(Configuration conf, String hdfsFilePath){
        boolean isExists = false;
        logger.info("check hdfs file " + hdfsFilePath + " is exists or not!");
        try {
            FileSystem fs = FileSystem.get(conf);
            isExists = fs.exists(new Path(hdfsFilePath));
        } catch (Exception e){
            logger.error("check hdfs file " + hdfsFilePath + " is exists or not exception!", e);
        }
        return isExists;
    }

}
