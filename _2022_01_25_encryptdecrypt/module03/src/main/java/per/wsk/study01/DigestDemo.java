package per.wsk.study01;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author wb_weishaokang
 * @date 2022/2/7 3:03 下午
 * @description
 *
 *              消息摘要算法，为了防止篡改
 *              常见的加密算法：md5，sha1 ，sha256，sha512
 */
public class DigestDemo {


    public static void main(String[] args) throws Exception {
        //原文
        String input = "aa";
/*        //算法
        String algorithm = "MD5";
        //创建消息摘要对象
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        //执行消息摘要算法 得到加密后的数组
        byte[] digest1 = digest.digest(input.getBytes());*/


        // 算法
        String algorithm = "MD5";
        String md5 = getDigest(input, algorithm);
        System.out.println("md5===" + md5);

        String sha1 = getDigest(input, "SHA-1");
        System.out.println("sha1==="+sha1);

        String sha256 = getDigest(input, "SHA-256");
        System.out.println("sha256==="+sha256);

        String sha512 = getDigest(input, "SHA-512");
        System.out.println("sha512==="+sha512);

    }


    /**
     * 获取数字摘要
     * @param input 原文
     * @param algorithm 算法
     * @return
     * @throws Exception
     */
    private static String getDigest(String input, String algorithm) throws Exception {
        // 创建消息摘要对象
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        // 执行消息摘要算法
        byte[] digest1 = digest.digest(input.getBytes());

        return toHex(digest1);
    }

    private static String toHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        // 对密文进行迭代
        for (byte b : digest) {
            // 把密文转换成16进制
            String s = Integer.toHexString(b & 0xff);
            // 判断如果密文的长度是1，需要在高位进行补0
            if (s.length() == 1){
                s = "0"+s;
            }
            sb.append(s);
//            System.out.print(s);
        }
        // 使用base64进行转码
        return sb.toString();
//        System.out.println(sb.toString());
    }



}
