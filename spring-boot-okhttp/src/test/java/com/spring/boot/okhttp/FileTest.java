package com.spring.boot.okhttp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 *
 * </p>
 *
 * @author rock.jxf
 * @date 2020/11/21 19:19
 */
@SpringBootTest
public class FileTest {
    @Test
    void testHttps() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("client/ds.crt");
        InputStream inputStream =classPathResource.getInputStream();
//        System.out.println(new String(inputStream.readAllBytes()));
    }
}
