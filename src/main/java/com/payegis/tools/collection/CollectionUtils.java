package com.payegis.tools.collection;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/7/4 9:52
 * description: 集合工具类，包含但不限于List Set Map
 */
public class CollectionUtils {
    private static Logger logger = Logger.getLogger(CollectionUtils.class);

    /**
     * description: word count
     * param: [words]
     * return: void
     * time: 2018/7/3 14:03
     */
    public static HashMap<String, Integer> wordcount(List<String> words) {
        HashMap<String, Integer> wordcount = new HashMap<>();
        words.forEach(word -> {
            if (wordcount.containsKey(word)) {
                wordcount.put(word, wordcount.get(word) + 1);
            } else {
                wordcount.put(word, 1);
            }
        });
        return wordcount;
    }

    /**
     * description: 从word count中取出topN
     * param: [wordcount, n]
     * return: java.lang.String[]
     * time: 2018/7/3 14:52
     */
    public static String[] getTopN(HashMap<String, Integer> wordcount, int n) {
        String[] topNWord = new String[n];
        int[] topNCount = new int[n];
        wordcount.forEach((word, count) -> {
            for (int i = topNCount.length - 1; i >= 0; i--) {
                if (count <= topNCount[i]) {
                    break;
                } else {
                    int temp = topNCount[i];
                    topNCount[i] = count;
                    if (i + 1 < topNCount.length) {
                        topNCount[i + 1] = temp;
                    }
                    String tempWord = topNWord[i];
                    topNWord[i] = word;
                    if (i + 1 < topNCount.length) {
                        topNWord[i + 1] = tempWord;
                    }
                }
            }
        });
        return topNWord;
    }

    /**
     * description: list分页
     * param: [source, n]
     * return: java.util.List<java.util.List<T>>
     * date: 2018/5/15
     * time: 19:04
     */
    public static <T> List<List<T>> averageAssignList(List<T> source, int n) {
        List<List<T>> result = new ArrayList<>();
        try {
            int remaider = source.size() % n;  // 先计算出余数
            int number = source.size() / n;  // 然后是商
            int offset = 0; // 偏移量
            for (int i = 0; i < n; i++) {
                List<T> value;
                if (remaider > 0) {
                    value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                    remaider--;
                    offset++;
                } else {
                    value = source.subList(i * number + offset, (i + 1) * number + offset);
                }
                result.add(value);
            }
        } catch (Exception e) {
            logger.error("split list to " + n + " partitions exception!", e);
        }
        return result;
    }

}
