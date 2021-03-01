package com.spring.boot.qingcloud;

import com.qingcloud.sdk.constants.QCConstant;
import com.qingcloud.sdk.exception.QCException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class QCSignatureUtils {

    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    static String computeIaasSignature(String httpMethod, String endPoint, String uri, String accSecret) {
        String strToSign = String.format("%s\n/%s/\n%s", httpMethod, endPoint, uri);
        String signature = "";
        try {
            signature = calculateSignature(accSecret, strToSign);
        } catch (UnsupportedEncodingException | QCException e) {
            e.printStackTrace();
        }

        return signature;
    }

    static String formatIso8601Date(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return df.format(date);
    }

    private static String calculateSignature(String secret, String stringToSign) throws UnsupportedEncodingException, QCException {
        final String ALGORITHM = "HmacSHA256";
        byte[] signData = null;
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            mac.init(new SecretKeySpec(secret.getBytes(QCConstant.ENCODING_UTF8), ALGORITHM));
            signData = mac.doFinal(stringToSign.getBytes(QCConstant.ENCODING_UTF8));
        } catch (InvalidKeyException | IllegalStateException | UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] base64Encoded = Base64.encodeBase64(signData);
        if (base64Encoded == null) {
            throw new QCException("no signature generated!");
        }

        return URLEncoder.encode(new String(base64Encoded, QCConstant.ENCODING_UTF8), QCConstant.ENCODING_UTF8);
    }
}
