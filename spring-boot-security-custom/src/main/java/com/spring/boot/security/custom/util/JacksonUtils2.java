//package com.spring.boot.security.custom.util;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * <p>Description: Jackson 工具类</p>
// *
// * @author Rock Jiang
// * @version 1.0
// * @date 2018/4/20 0020
// */
//public class JacksonUtils2 {
//    private static Logger logger = LoggerFactory.getLogger(JacksonUtils2.class);
//
//    private static final ObjectMapper objectMapper;
//
//    /**
//     * 序列化反序列化参数设置
//     */
//    static {
//        objectMapper = new ObjectMapper();
//
//        //去掉默认的时间戳格式
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        //空值不序列化
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        //反序列化时，属性不存在的兼容处理
//        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        //序列化时，日期的统一格式
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        //单引号处理
//        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//    }
//
//    /**
//     * Json字符串反序列化为Object
//     *
//     * @param json
//     * @param clazz
//     * @param <T>
//     * @return
//     */
//    public static <T> T json2Object(String json, Class<T> clazz) {
//        try {
//            return objectMapper.readValue(json, clazz);
//        } catch (JsonParseException e) {
//            logger.error(e.getMessage(), e);
//        } catch (JsonMappingException e) {
//            logger.error(e.getMessage(), e);
//        } catch (IOException e) {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//    /**
//     * Object序列化为Json字符串
//     *
//     * @param entity
//     * @param <T>
//     * @return
//     */
//    public static <T> String object2Json(T entity) {
//        try {
//            return objectMapper.writeValueAsString(entity);
//        } catch (JsonGenerationException e) {
//            logger.error(e.getMessage(), e);
//        } catch (JsonMappingException e) {
//            logger.error(e.getMessage(), e);
//        } catch (IOException e) {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//    /**
//     * Json字符串反序列化为集合对象
//     *
//     * @param json
//     * @param typeReference
//     * @param <T>
//     * @return
//     */
//    public static <T> T json2Collection(String json, TypeReference<T> typeReference) {
//        try {
//            return objectMapper.readValue(json, typeReference);
//        } catch (JsonParseException e) {
//            logger.error(e.getMessage(), e);
//        } catch (JsonMappingException e) {
//            logger.error(e.getMessage(), e);
//        } catch (IOException e) {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//    /**
//     * Json字符串反序列化为Map对象
//     *
//     * @param jsonStr
//     * @return
//     * @throws Exception
//     */
//    public static Map<String, Object> json2map(String jsonStr)
//            throws Exception {
//        return objectMapper.readValue(jsonStr, Map.class);
//    }
//
//    /**
//     * Json字符串反序列化为Map对象-泛型支持
//     *
//     * @param jsonStr
//     * @param clazz
//     * @param <T>
//     * @return
//     * @throws Exception
//     */
//    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz)
//            throws Exception {
//        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr,
//                new TypeReference<Map<String, T>>() {
//                });
//        Map<String, T> result = new HashMap<>();
//        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
//            result.put(entry.getKey(), map2Object(entry.getValue(), clazz));
//        }
//        return result;
//    }
//
//    /**
//     * Json字符串反序列化为List集合-泛型支持
//     *
//     * @param jsonArrayStr
//     * @param clazz
//     * @param <T>
//     * @return
//     * @throws Exception
//     */
//    public static <T> List<T> json2List(String jsonArrayStr, Class<T> clazz)
//            throws Exception {
//        List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr,
//                new TypeReference<List<T>>() {
//                });
//        List<T> result = new ArrayList<>();
//        for (Map<String, Object> map : list) {
//            result.add(map2Object(map, clazz));
//        }
//        return result;
//    }
//
//    /**
//     * map convert to javaBean
//     */
//    /**
//     * Map集合中对象转换为具体对象-泛型支持
//     *
//     * @param map
//     * @param clazz
//     * @param <T>
//     * @return
//     */
//    public static <T> T map2Object(Map map, Class<T> clazz) {
//        return objectMapper.convertValue(map, clazz);
//    }
//}
