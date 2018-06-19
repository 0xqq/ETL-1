package com.payegis.tools.service;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/3/21.
 */
public class HttpUtils {
    private static Logger logger = Logger.getLogger(HttpUtils.class);

    /**
     * description: 发送post请求
     * param: [url, params]
     * return: java.lang.String
     * date: 2018/6/13
     * time: 15:39
     */
    public static String sendPost(String url, Map<String, String> params) {
        String str = "";
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);
            List<NameValuePair> list = getNameValuePairArr(params);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
            httppost.setEntity(entity);
            CloseableHttpResponse response;
            try {
                response = httpclient.execute(httppost);
                HttpEntity httpentity = response.getEntity();
                if (httpentity != null) {
                    str = EntityUtils.toString(httpentity, "UTF-8");
                }
            } catch (Exception e) {
                logger.error("send post request to url: " + url + " with parameters: " + params + " exception!", e);
            }
        } catch (Exception e) {
            logger.error("send post request to url: " + url + " with parameters: " + params + " exception!", e);
        }
        return str;
    }

    private static List<NameValuePair> getNameValuePairArr(
            Map<String, String> parasMap) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> parasEntry : parasMap.entrySet()) {
            String parasName = parasEntry.getKey();
            String parasValue = parasEntry.getValue();
            nvps.add(new BasicNameValuePair(parasName, parasValue));
        }
        return nvps;
    }

}
