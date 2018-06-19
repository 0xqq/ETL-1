package com.payegis.tools.db;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.apache.log4j.Logger;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/6/7
 * time: 18:42
 * description: memcached 连接池工具类
 */
public class MemcachedPoolUtils {
    private static Logger logger = Logger.getLogger(MemcachedPoolUtils.class);
    private static MemCachedClient cachedClient;

    private MemcachedPoolUtils(String[] host, String[] port) {
        try {
            //获取连接池的实例
            SockIOPool pool = SockIOPool.getInstance();

            //服务器列表及其权重
            String[] servers = new String[host.length];
            for (int i = 0; i < host.length; i++) {
                servers[i] = host[i] + ":" + port[i];
            }
            Integer[] weights = {3};

            //设置服务器信息
            pool.setServers(servers);
            pool.setWeights(weights);

            //设置初始连接数、最小连接数、最大连接数、最大处理时间
            pool.setInitConn(10);
            pool.setMinConn(10);
            pool.setMaxConn(1000);
            pool.setMaxIdle(1000 * 60 * 60);

            //设置连接池守护线程的睡眠时间
            pool.setMaintSleep(60);

            //设置TCP参数，连接超时
            pool.setNagle(false);
            pool.setSocketTO(60);
            pool.setSocketConnectTO(0);

            //初始化并启动连接池
            pool.initialize();

            //压缩设置，超过指定大小的都压缩
//      cachedClient.setCompressEnable(true);
//      cachedClient.setCompressThreshold(1024*1024);

            cachedClient = new MemCachedClient();
        } catch (Exception e) {
            logger.error("initialize memcached pool exception, the host is " + host + ", port is " + port, e);
        }
    }

    /**
     * description: 获取MemcachedPoolUtils工具类实例，传递host数组和port数组作为参数
     * param: [host, port]
     * return: com.payegis.tools.db.MemcachedPoolUtils
     * date: 2018/6/13
     * time: 14:26
     */
    public static MemcachedPoolUtils getInstance(String[] host, String[] port) {
        return new MemcachedPoolUtils(host, port);
    }

    /**
     * description: 添加key value到memcached，永久保存，如果key存在，则不添加
     * param: [key, value]
     * return: boolean
     * date: 2018/6/13
     * time: 14:33
     */
    public boolean add(String key, Object value) {
        if (cachedClient == null) {
            cachedClient = new MemCachedClient();
        }
        return cachedClient.add(key, value);
    }

    /**
     * description: 过期时间单位s，如果过期时间为0，则永久保存，不为0，如果key存在，则不添加
     * param: [key, value, expire]
     * return: boolean
     * date: 2018/6/13
     * time: 14:36
     */
    public boolean add(String key, Object value, Integer expire) {
        if (cachedClient == null) {
            cachedClient = new MemCachedClient();
        }
        return cachedClient.add(key, value, expire);
    }

    /**
     * description: 永久保存，若key存在，则替换value，如果不存在，则添加
     * param: [key, value]
     * return: boolean
     * date: 2018/6/13
     * time: 14:37
     */
    public boolean set(String key, Object value) {
        if (cachedClient == null) {
            cachedClient = new MemCachedClient();
        }
        return cachedClient.set(key, value);
    }

    /**
     * description: 如果过期时间为0，则永久保存，如果key存在，则替换value，如果不存在，则添加
     * param: [key, value, expire]
     * return: boolean
     * date: 2018/6/13
     * time: 14:39
     */
    public boolean set(String key, Object value, Integer expire) {
        if (cachedClient == null) {
            cachedClient = new MemCachedClient();
        }
        return cachedClient.set(key, value, expire);
    }

    /**
     * description: 如果key存在，则替换value，如果不存在，则不做任何操作
     * param: [key, value]
     * return: boolean
     * date: 2018/6/13
     * time: 14:40
     */
    public boolean replace(String key, Object value) {
        if (cachedClient == null) {
            cachedClient = new MemCachedClient();
        }
        return cachedClient.replace(key, value);
    }

    /**
     * description: 如果过期时间为0，则永久保存，如果key存在，则替换value，如果不存在，则不做任何操作
     * param: [key, value, expire]
     * return: boolean
     * date: 2018/6/13
     * time: 14:41
     */
    public boolean replace(String key, Object value, Integer expire) {
        if (cachedClient == null) {
            cachedClient = new MemCachedClient();
        }
        return cachedClient.replace(key, value, expire);
    }

    /**
     * description: 获取key的值
     * param: [key]
     * return: java.lang.Object
     * date: 2018/6/13
     * time: 15:00
     */
    public Object get(String key) {
        if (cachedClient == null) {
            cachedClient = new MemCachedClient();
        }
        return cachedClient.get(key);
    }

    /**
     * description: 清除所有缓存的数据
     * param: []
     * return: void
     * date: 2018/6/13
     * time: 15:05
     */
    public boolean flush() {
        boolean flag = false;
        try {
            if (cachedClient == null) {
                cachedClient = new MemCachedClient();
            }
            flag = cachedClient.flushAll();
        } catch (Exception e) {
            logger.error("flush memcached exception!", e);
        }
        return flag;
    }

    /**
     * description: 删除memcached中的key
     * param: [key]
     * return: boolean
     * date: 2018/6/13
     * time: 15:11
     */
    public boolean delete(String key) {
        boolean flag = false;
        try {
            if (cachedClient == null) {
                cachedClient = new MemCachedClient();
            }
            flag = cachedClient.delete(key);
        } catch (Exception e) {
            logger.error("delete key " + key + " exception!", e);
        }
        return flag;
    }

}
