package per.wsk.study01;

import java.util.Arrays;
import java.util.List;

/**
 * @Author wb_weishaokang
 * @date 2022/1/25 6:11 下午
 * @description
 */
public class AsciiTest {


    public static void main(String[] args) {
        char a = 'A';
        int b = a;
        System.out.println("A 对应的ascii码是：" + b);


        String str = "AbRD";
        char[] chars = str.toCharArray();

        for (char aChar : chars) {
            int intAChar = aChar;
            System.out.println(intAChar);
        }


    }


}
