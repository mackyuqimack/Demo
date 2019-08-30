package com.yuqi.demo.common.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 公共帮助类
 *
 * @author yuqi
 * @date 2018-12-06 18:04:16
 */
public class CommonUtil {

    /**
     * 输出文本信息
     *
     * @param msg  文本内容
     * @param name 文本名称（后面会自动带上时间）
     * @throws IOException exception
     */
    public static void writeTxt(String msg, String name) throws IOException {
        String fileName = name + FastDateFormat.getInstance("yyyyMMddHHmmssSSS").format(new Date());
        File f = new File("../" + fileName + ".txt");
        FileUtils.writeStringToFile(f, msg);
    }

    /**
     * 四舍五入保留小数
     *
     * @param sourceNumber double小数
     * @param num          保留小数位
     * @return 小数
     */
    public static double parseDouble(double sourceNumber, int num) {
        BigDecimal b = new BigDecimal(sourceNumber);
        return b.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将一个list拆分成多个list
     *
     * @param list 待拆分的list
     * @param len  拆分后每个list长度
     * @return list
     */
    public static <T> List<List<T>> splitList(List<T> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<T>> result = new LinkedList<>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

    /**
     * md5加密方法
     *
     * @param str 被加密字符串
     * @return 32位小写MD5
     */
    public static String parseStrToMd5L32(String str) {
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : bytes) {
                int bt = b & 0xff;
                if (bt < 16) {
                    stringBuilder.append(0);
                }
                stringBuilder.append(Integer.toHexString(bt));
            }
            reStr = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }

    /**
     * 获取几小时后的时间
     *
     * @param hour 小时数
     * @return Date
     */
    public static Date getSomeHourDate(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }
}
