package com.spring.boot.security.custom.cluster.config;//package com.spring.boot.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionListener;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * <p>Description: 监听器，用于监听处理session创建、销毁事件</p>
// *
// * @author Rock Jiang
// * @version 1.0
// * @date 2018/4/18 0018
// */
//public class HttpSessionMonitorListener implements HttpSessionListener {
//    private static final Logger logger = LoggerFactory.getLogger(HttpSessionMonitorListener.class);
//
//    /**
//     * session创建时处理
//     *
//     * @param se
//     */
//    @Override
//    public void sessionCreated(HttpSessionEvent se) {
//        HttpSession httpSession = se.getSession();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//
//        logger.debug("----------session创建时处理 开始----------");
//        logger.debug("httpSession.getId: {} getCreationTime: {} getLastAccessedTime: {} getMaxInactiveInterval: {} seconds", httpSession.getId(), simpleDateFormat.format(new Date(httpSession.getCreationTime())), simpleDateFormat.format(new Date(httpSession.getLastAccessedTime())), httpSession.getMaxInactiveInterval());
//        logger.debug("----------session创建时处理 结束----------");
//    }
//
//    /**
//     * session销毁时处理
//     *
//     * @param se
//     */
//    @Override
//    public void sessionDestroyed(HttpSessionEvent se) {
//        HttpSession httpSession = se.getSession();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//
//        logger.debug("----------session销毁时处理 开始----------");
//        logger.debug("httpSession.getId: {} getCreationTime: {} getLastAccessedTime: {} getMaxInactiveInterval: {} seconds", httpSession.getId(), simpleDateFormat.format(new Date(httpSession.getCreationTime())), simpleDateFormat.format(new Date(httpSession.getLastAccessedTime())), httpSession.getMaxInactiveInterval());
//        logger.debug("----------session销毁时处理 结束----------");
//    }
//}
