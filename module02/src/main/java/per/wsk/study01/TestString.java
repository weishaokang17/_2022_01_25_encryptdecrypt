package per.wsk.study01;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * @Author wb_weishaokang
 * @date 2022/1/28 5:38 下午
 * @description
 *      toString 和 new String 原理和区别
 */
public class TestString {


    public static void main(String[] args) {
        // 表示密文
        String str="TU0jV0xBTiNVYys5bEdiUjZlNU45aHJ0bTdDQStBPT0jNjQ2NDY1Njk4IzM5OTkwMDAwMzAwMA==";
        // 使用base64进行解码
        String rlt1=new String(Base64.decode(str));
        // 使用base64进行解码
        String rlt2=Base64.decode(str).toString();

        System.out.println("new String===" + rlt1);

        System.out.println("toString==" + rlt2);

        /*
         *  toString和new String() 经过base64解码后结果不同，
         *  应该使用new String()，不是toString()
         * 因为toString方法一般都是调用的Object类中的toString方法，返回的是hash值
         *
         */
    }

}
