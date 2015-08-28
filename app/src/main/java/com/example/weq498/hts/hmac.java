package com.example.weq498.hts;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by weq498 on 2015/8/13.
 */
public class hmac {
    public static String RFC1024(String data, String key) {
        try {
            SecretKeySpec signinkey = new SecretKeySpec(data.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signinkey);
            byte[] result = mac.doFinal(key.getBytes());
            String hmaccode = new String(Hex.encodeHex(result));
            return hmaccode;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
