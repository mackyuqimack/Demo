package com.yuqi.demo.common.interceptor;

import com.yuqi.demo.common.filter.RequestWrapper;
import com.yuqi.demo.common.util.CommonUtil;
import com.yuqi.demo.common.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 验证权限拦截器
 *
 * @author yuqi
 * @date 2018-12-26 14:33:28
 */
public class ValidationInterceptor implements HandlerInterceptor {
    /**
     * 存配置文件里的数据，避免每次读取配置文件
     */
    private static final Map<String, String> PROPERTIES_MAP = new ConcurrentHashMap<>(16);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestWrapper cacheParam = new RequestWrapper(request);
        String body = cacheParam.getBodyString();
        //防止中文乱码
        response.setCharacterEncoding("utf-8");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String source = request.getHeader("source");
        if (!StringUtils.isNumeric(timestamp)) {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("{\"code\":401,\"message\":\"时间戳格式不正确\"}");
            return false;
        }
        Long timestampLong = Long.valueOf(timestamp);
        Date date = new Date(timestampLong);
        //当前时间一小时前
        Date beforeTime = CommonUtil.getSomeHourDate(-1);
        //当前时间一小时后
        Date afterTime = CommonUtil.getSomeHourDate(1);
        if (date.after(afterTime) || date.before(beforeTime)) {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("{\"code\":401,\"message\":\"时间戳不在指定范围内\"}");
            return false;
        }
        String key = "source." + source;
        //先查询map是否有值,无值则读取配置文件
        String safeCode = PROPERTIES_MAP.get(key);
        if (safeCode == null) {
            PropertiesUtil propertiesUtil = new PropertiesUtil("md5.properties");
            safeCode = propertiesUtil.getProperty(key);
            if (safeCode == null) {
                PrintWriter printWriter = response.getWriter();
                printWriter.write("{\"code\":401,\"message\":\"查询不到此来源信息\"}");
                return false;
            }
            PROPERTIES_MAP.put(key, safeCode);
        }
        String md5 = CommonUtil.parseStrToMd5L32(timestamp + body + safeCode);
        if (!md5.equals(sign)) {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("{\"code\":401,\"message\":\"sign验证失败\"}");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
