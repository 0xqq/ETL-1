package com.payegis.tools.db;

import net.spy.memcached.MemcachedClient;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MemcachedUtils implements Serializable{
    private static Logger logger = Logger.getLogger(MemcachedUtils.class);
    private MemcachedClient memcachedClient = null;

    public void destroyMemcached() {
        if (memcachedClient != null) {
            memcachedClient.shutdown();
        }
        logger.info("destroy memcached success!");
    }

    /**
     * description: 设置kv
     * param: [key, value, expTime]
     * return: boolean
     * date: 2018/6/7
     * time: 18:02
     */
    public boolean setMemcached(String key, Object value, int expTime) {
        try {
            if (memcachedClient == null) {
                logger.error("please connect memcached first!");
                return false;
            }
            Future<Boolean> b = memcachedClient.set(key, expTime, value);
            if (b.get().booleanValue()) {
                logger.info("set value:" + value + " key:" + key);
                return true;
            }
            return false;
        } catch (Exception ex) {
            logger.error("set memcached error!", ex);
            return false;
        }
    }

    /**
     * description: 获取到缓冲的数据或对象
     * param: [key]
     * return: java.lang.Object
     * date: 2018/6/7
     * time: 18:02
     */
    public Object getMemcached(String key) {
        Object result = null;
        Future<Object> f = null;
        try {
            if (memcachedClient == null) {
                logger.error("please connect memcached first!");
                return null;
            }
            f = memcachedClient.asyncGet(key);
            result = f.get(5, TimeUnit.SECONDS);
            logger.info("get value:" + result + " key:" + key);
        } catch (TimeoutException e) {
            if (f != null) {
                f.cancel(false);
            }
        } catch (Exception ex) {
            logger.error("get memcached error!", ex);
        } finally {
            destroyMemcached();
        }
        return result;
    }

    /**
     * description: 清除所有缓存的数据
     **/
    public void flushMemcached() {
        try {
            if (memcachedClient == null) {
                logger.error("please connect memcached first!");
                return;
            }
            memcachedClient.flush();
        } catch (Exception e) {
            logger.error("flush memcached error!", e);
        } finally {
            destroyMemcached();
        }
    }

    private MemcachedClient getMemcachedClient(String host, int port) {
        if (memcachedClient == null) {
            try {
                memcachedClient = new MemcachedClient(new InetSocketAddress(host, port));
            } catch (Exception e) {
                logger.error("get memcached client exception!", e);
            }
        }
        return memcachedClient;
    }

    public MemcachedUtils(String host, int port) {
        getMemcachedClient(host, port);
    }

    public void delete(String key) {
        try {
            if (memcachedClient == null) {
                logger.error("please connect memcached first!");
                return;
            }
            memcachedClient.delete(key);
        } catch (Exception e) {
            logger.error("delete memcached key " + key + " exception!");
        } finally {
            destroyMemcached();
        }
    }

}
