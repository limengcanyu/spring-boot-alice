//package com.veu.element.alice.admin.signature;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.StringUtils;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URLDecoder;
//import java.util.Enumeration;
//import java.util.Map;
//import java.util.TreeMap;
//
///**
// * <p>Description: 请求签名验证拦截器 </p>
// * 加签名验签的方法，需要在方法或者类上添加注解@CryptoType(CryptoType.SIGN)<br>
// * 签名方式：md5(timestamp + token + data（字典升序）)
// *
// * @author rock.jiang
// * Date 2019/12/25 17:41
// */
//public class RequestSignVerifyInterceptor extends HandlerInterceptorAdapter {
//    private static Logger logger = LoggerFactory.getLogger(RequestSignVerifyInterceptor.class);
//
//    /**
//     * 时间戳超时设置60s
//     */
//    private static final long TIMEOUT = 60 * 1000L;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        logger.debug("RequestSignVerifyInterceptor -> preHandle");
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod method = (HandlerMethod) handler;
//            // 方法上注解
//            CryptoType type = method.getMethodAnnotation(CryptoType.class);
//            if (null == type) {
//                // 类上是否有注解
//                type = method.getBeanType().getAnnotation(CryptoType.class);
//                if (null == type) {
//                    return true;
//                }
//            }
//            if (type.value() != CryptoType.Type.SIGN) {
//                // 不是签名验证跳过
//                return true;
//            }
//        }
//        logger.debug("Start verifySign ->");
//        if (!verifySign(request)) {
//            // 不通过
//            logger.debug("VerifySign fail");
//            response.setContentType("application/json; charset=UTF-8");
////            response.getWriter().write(JSONObject.toJSONString(Result.failure(Result.FAILURE_VERIFY_SIGN, "验签失败")));
//            return false;
//        }
//        // 验签通过
//        return true;
//    }
//
//    /**
//     * 验证签名是否一致
//     *
//     * @param request
//     */
//    private boolean verifySign(HttpServletRequest request) throws IOException {
//        String timestamp = request.getHeader("timestamp");
//        String signstr = request.getHeader("signstr");
//        String level = request.getHeader("level");
//        logger.debug("VerifySign params -> timestamp:{}, signstr:{}, level:{}", timestamp, signstr, level);
//        if (StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(signstr)) {
//            logger.error("VerifySign head param timestamp or signstr is empty");
//            return false;
//        }
//        // 时间戳是否过期 （60s）
//        if (System.currentTimeMillis() - Long.parseLong(timestamp) > TIMEOUT) {
//            // 超时
//            logger.error("VerifySign timestamp timeout");
//            return false;
//        }
//        // FIXME 从session获取id 作token，session需要做redis共享，不然集群部署每个服务获取sessionID不一致
//        String token = request.getSession().getId();
//        TreeMap<String, String> map = new TreeMap<>();
//        Enumeration<String> names = request.getParameterNames();
//        while (names.hasMoreElements()) {
//            String name = names.nextElement();
//            map.put(name, URLDecoder.decode(request.getParameter(name), "UTF-8"));
//        }
//        return signstr.equals(sign(token, timestamp, map));
//    }
//
//    /**
//     * 签名
//     *
//     * @param token
//     * @param timestamp
//     * @param params
//     */
//    private String sign(String token, String timestamp, TreeMap<String, String> params) {
//        StringBuilder paramValues = new StringBuilder();
//        paramValues.append(timestamp).append(token);
//
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            paramValues.append(entry.getKey()).append(entry.getValue());
//        }
//        return MD5Util.computeMD5(paramValues.toString());
//    }
//}
