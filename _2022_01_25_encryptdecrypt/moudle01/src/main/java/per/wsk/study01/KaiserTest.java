package per.wsk.study01;

/**
 * @Author wb_weishaokang
 * @date 2022/1/26 10:32 上午
 * @description
 *  凯撒加密
 */
public class KaiserTest {


    public static void main(String[] args) {
        //原文
        String input = "Hello World";
        //秘钥 (向右移动4位)
        int key = 4;
        //加密
        String encryptStr = encryptKaiser(input, key);
        System.out.println("加密后: " + encryptStr);
        //解密
        String decryptStr = decryptKaiser(encryptStr, key);
        System.out.println("解密后: " + decryptStr);
    }

    /**
     * 凯撒加密
     * @param orignal
     * @param key
     * @return
     */
    public static String encryptKaiser(String orignal, int key) {
        if (orignal == null || orignal.length() == 0){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = orignal.toCharArray();

        for (char aChar : chars) {
            aChar+=key;
            sb.append((char)aChar);
        }

        return sb.toString();
    }

    /**
     * 凯撒解密
     * @param encryptedData
     * @param key
     * @return
     */
    public static String decryptKaiser(String encryptedData, int key) {
        if (encryptedData == null || encryptedData.length() == 0){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = encryptedData.toCharArray();
        for (char aChar : chars) {
            aChar-=key;
            sb.append((char)aChar);
        }
        return sb.toString();
    }
}
