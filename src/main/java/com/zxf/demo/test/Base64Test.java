package com.zxf.demo.test;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @author zxf
 */
public class Base64Test {
    public static void main(String [] args){
        String s = "aHR0cHM6Ly9tLmhlbWFsbC5zaG9wL3Jlc291cmNlcy9oZS1zaG9wL2luZGV4Lmh0bWwjL3ZvdWNoZXI_Y2FyZFB3ZD14eHh4eHg";
        String a = "https://www.baidu.com/search/error.html";
        try {
            //解密
            String base64DecoderUrl = new String(safeUrlBase64Decode(s));
            //加密
            String base64EncoderUrl = safeUrlBase64Encode(a.getBytes());
            System.out.println(base64DecoderUrl);
            System.out.println(base64EncoderUrl);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static String safeUrlBase64Encode(byte[] data){
        String encodeBase64 = new BASE64Encoder().encode(data);
        String safeBase64Str = encodeBase64.replace('+', '-');
        safeBase64Str = safeBase64Str.replace('/', '_');
        safeBase64Str = safeBase64Str.replaceAll("=", "");
        return safeBase64Str;
    }

    public static byte[] safeUrlBase64Decode(final String safeBase64Str) throws IOException {
        String base64Str = safeBase64Str.replace('-', '+');
        base64Str = base64Str.replace('_', '/');
        int mod4 = base64Str.length()%4;
        if(mod4 > 0){
            base64Str = base64Str + "====".substring(mod4);
        }
        return new BASE64Decoder().decodeBuffer(base64Str);
    }
}
