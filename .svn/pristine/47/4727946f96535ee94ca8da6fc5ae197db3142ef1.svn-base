#!/bin/sh
#awk model
#awk -F 'delimiter default blank character' 'BEGIN{} {} END{}'

#读取当前文件夹下的test文件，按照冒号切分每一行，对第一列进行排序并去重，去重结果追加到distinctResult文件中
#下述两种方法均实现了去重功能，第一种方法增加了统计重复值数量的功能
awk -F ':' '{print $1}' test | sort | uniq -c >> distinctResult
awk -F ':' '{print $1}' test | sort -u >> distinctResult

#将articles.txt文件用空白字符分隔得到第二列的值进行匹配，如果满足全为英文字符，则输出该值，不满足，则输出空字符串
awk '{print $2 ~ /[a-zA-Z]+/?$2:""}' articles.txt
awk '{print $2 !~ /[a-zA-Z]+/?$2:""}' articles.txt #同理：!~表示不匹配

#姓名校验，不合理的姓名输出到文件
awk -F ',' '{print $2 !~ /[\u4e00-\u9fa5]+·?[\u4e00-\u9fa5]*/ $2}' articles.txt | sort -u >> abnormalName
