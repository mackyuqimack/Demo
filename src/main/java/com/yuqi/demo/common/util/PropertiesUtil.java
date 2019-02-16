package com.yuqi.demo.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取properties帮助类
 *
 * @author yuqi
 * @date 2019-01-11 14:16:17
 */
public class PropertiesUtil {
    private String propertiesName = "";

    public PropertiesUtil() {
    }

    public PropertiesUtil(String fileName) {
        this.propertiesName = fileName;
    }

    /**
     * 按key获取值
     *
     * @param key 关键字
     * @return 值
     */
    public String getProperty(String key) {
        String value = null;
        InputStream is = null;
        try {
            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesName);
            Properties p = new Properties();
            p.load(is);
            value = p.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return value;
    }

    /**
     * 获取整个配置信息
     *
     * @return Properties
     */
    public Properties getProperties() {
        Properties p = new Properties();
        InputStream is = null;
        try {
            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesName);
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return p;
    }
}
