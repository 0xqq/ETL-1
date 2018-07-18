package com.payegis.tools.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.payegis.tools.util.ExternalPropertyUtils;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/5/16
 * time: 10:35
 * description: 连接mongodb工具类，通过传入配置文件获得MongoDatabase
 */
public class MongoDBUtils implements Serializable{
    private static Logger logger = Logger.getLogger(MongoDBUtils.class);
    public static Properties props;
    private static MongoClient client;
    public static MongoDatabase db;

    private MongoDBUtils(String filePath) {
        try {
            ExternalPropertyUtils propertyUtil = ExternalPropertyUtils.getInstance(filePath);
            props = propertyUtil.props;
            String host = props.getProperty("host");
            String portStr = props.getProperty("port");
            int port = 27017;
            if (portStr.matches("\\d+")) {
                port = Integer.parseInt(portStr);
            }
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            String authDb = props.getProperty("authDb");
            String dbName = props.getProperty("db");
            ServerAddress seed = new ServerAddress(host, port);
            createMongoDBClient(seed, username, authDb, password);
            db = client.getDatabase(dbName);
        } catch (Exception e) {
            logger.error("create MongoDBUtils instance exception!", e);
        }
    }

    /**
     * description: 获取MongoDBUtils工具类实例，传递连接mongodb参数配置文件
     * param: [filePath]
     * return: com.payegis.tools.db.MongoDBUtils
     * date: 2018/6/13
     * time: 15:16
     */
    public static MongoDBUtils getInstance(String filePath) {
        return new MongoDBUtils(filePath);
    }

    /**
     * description: 创建mongodb client实例
     * param: [seed, username, authDb, password]
     * return: com.mongodb.MongoClient
     * date: 2018/6/13
     * time: 15:16
     */
    private MongoClient createMongoDBClient(ServerAddress seed, String username, String authDb, String password) {
        if (client == null) {
            try {
                List<MongoCredential> credentials = new ArrayList<>();
                credentials.add(MongoCredential.createScramSha1Credential(username, authDb, password.toCharArray()));
                MongoClientOptions options = MongoClientOptions.builder().socketTimeout(2000).connectionsPerHost(1).build();
                client = new MongoClient(seed, credentials, options);
            } catch (Exception e) {
                logger.error("create mongodb client exception!", e);
            }
        }
        return client;
    }

}
