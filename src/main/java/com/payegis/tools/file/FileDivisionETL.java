package com.payegis.tools.file;

import com.payegis.tools.util.ColumnValueEtl;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Set;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/6/15
 * time: 8:59
 * description: 文件行按指定分隔符切分，并对切分后的字段做不同的方法进行清洗，并写出文件
 */
public class FileDivisionETL {
    private static Logger logger = Logger.getLogger(FileDivisionETL.class);

    /**
     * description: 按照指定文件行分隔符切分，并对每个字段进行清洗
     * param: [filePath, delimiter, etlMethods]
     * return: java.util.ArrayList<java.lang.String[]>
     * date: 2018/6/15
     * time: 13:12
     */
    public static ArrayList<String[]> etl(String filePath, String delimiter, String... etlMethods) {
        ArrayList<String[]> division = division(filePath, delimiter); // 按照分隔符对文件行进行切分
        etlColumn(division, etlMethods); // 清洗每个字段
        return division;
    }

    /**
     * description: 按照指定文件行分隔符切分，对每个字段进行清洗，并保存分隔符到分隔符库文件中
     * param: [dataFilePath, delimiterDbPath, delimiter, etlMethods]
     * return: java.util.ArrayList<java.lang.String[]>
     * time: 2018/6/19 10:41
     */
    public static ArrayList<String[]> etl(String delimiterDbPath, String dataFilePath, String delimiter, String... etlMethods) {
        ArrayList<String[]> division = division(dataFilePath, delimiter); // 按照分隔符对文件行进行切分
        etlColumn(division, etlMethods); // 清洗每个字段
        saveDelimiter(delimiterDbPath, delimiter); // 保存分隔符到分隔符库
        return division;
    }

    /**
     * description: 按照指定分隔符切分文件行，返回一个集合
     * param: [filePath, delimiter]
     * return: java.util.ArrayList<java.lang.String[]>
     * date: 2018/6/15
     * time: 9:41
     */
    public static ArrayList<String[]> division(String filePath, String delimiter) {
        ArrayList<String[]> returnCollection = new ArrayList<>();
        Set<String> lineSet = FileUtils.readFileDeduplication(filePath);
        lineSet.forEach(line -> {
            String[] columns;
            if ("$".equals(delimiter)){
                columns = line.split("[$]");
            } else {
                columns = line.split(delimiter);
            }
            returnCollection.add(columns);
        });
        return returnCollection;
    }

    /**
     * description: 对分隔后的文件的列进行不同方式的清洗
     * param: [divisionResult]
     * return: java.util.ArrayList<java.lang.String[]>
     * date: 2018/6/15
     * time: 9:47
     */
    public static void etlColumn(ArrayList<String[]> divisionResult, String... etlMethods) {
        divisionResult.forEach(columns -> {
            for (int i = 0; i < columns.length; i++) {
                String columnValue = ColumnValueEtl.etl(columns[i], "default"); // 默认方式清洗
                columns[i] = columnValue;
                if (i < etlMethods.length) {
                    String columnEtlMethod = etlMethods[i];
                    columns[i] = ColumnValueEtl.etl(columnValue, columnEtlMethod); // 指定方式清洗
                }
            }
        });
    }

    /**
     * description: 保存分隔符到分隔符库
     * param: [delimiter]
     * return: void
     * time: 2018/6/19 10:10
     */
    public static void saveDelimiter(String delimiterDbPath, String delimiter) {
        logger.info("save delimiter " + delimiter + " to file " + delimiterDbPath + "!");
        Set<String> delimiters = FileUtils.readFileDeduplication(delimiterDbPath);
        delimiters.add(delimiter);
        StringBuilder delimiterStr = new StringBuilder("");
        delimiters.forEach(del -> delimiterStr.append(del + "\n"));
        FileUtils.coverFile(delimiterDbPath, delimiterStr.toString());
    }

}
