package per.wsk.study01;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * @Author wb_weishaokang
 * @date 2022/1/28 11:51 上午
 * @description
 *      当字节不够的时候，需要使用 = 进行补齐
 */
public class TestBase64 {

    public static void main(String[] args) {
        // 1 表示一个字节，不够3个字节
        // MQ==：需要注意，在使用base64 ，进行编码的时候，如果字节不够3个字节，需要使用=进行补齐
        System.out.println(Base64.encode("1".getBytes()));
        // 如果是2个字节，就补齐一个=号
        System.out.println(Base64.encode("12".getBytes()));
        System.out.println(Base64.encode("123".getBytes()));
        // 硅谷：是6个字节 6 * 8 = 48 ，刚刚好被整除，所以就没有=号
        System.out.println(Base64.encode("硅谷".getBytes()));
    }

}
