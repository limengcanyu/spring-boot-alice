package com.spring.boot.security.custom.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;

/**
 * <p>Description: Jackson 工具类</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/20 0020
 */
public class JacksonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    private static ObjectMapper mapper = new ObjectMapper();

    public static String bean2Json(Object obj) {
        StringWriter sw = null;
        try {
            sw = new StringWriter();
            JsonGenerator gen = new JsonFactory().createGenerator(sw);
            // using provided JsonGenerator serialize any Java value as JSON output
            mapper.writeValue(gen, obj);
            gen.close();
        } catch (IOException e) {
            logger.debug("bean2Json 转换异常");
        }
        return sw.toString();
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        try {
            return mapper.readValue(jsonStr, objClass);
        } catch (IOException e) {
            logger.debug("json2Bean 转换异常");
        }

        return null;
    }
}
