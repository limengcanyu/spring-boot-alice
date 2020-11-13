//package com.spring.boot.security.custom;
//
//import org.junit.jupiter.api.Test;
//import sun.misc.BASE64Encoder;
//
//import java.util.UUID;
//
///**
// * <p>Description: </p>
// *
// * @author Rock Jiang
// * @version 1.0
// * @date 2018/4/19 0019
// */
//public class BASE64EncoderTest {
//    BASE64Encoder encoder = new BASE64Encoder();
//
//    @Test
//    public void encodeTest() {
//        String token = UUID.randomUUID().toString().replaceAll("-", "");
//        System.out.println("token: " + token);
//        String tokenEncode = encoder.encode(token.getBytes());
//        System.out.println("tokenEncode: " + tokenEncode);
//    }
//}
