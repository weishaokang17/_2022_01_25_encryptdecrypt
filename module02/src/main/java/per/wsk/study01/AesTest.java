package per.wsk.study01;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author wb_weishaokang
 * @date 2022/1/28 6:02 下午
 * @description
 */
public class AesTest {



    public static void main(String[] args) throws Exception {
        //原文
        String input = "华为";
        //定义key 如果使用aes进行加密，那么密钥必须是16个字节
        String key = "1234567812345678";
        //算法
        String transformation = "AES";
        //加密类型
        String algorithm = "AES";
        //加密方法
        String encode = encryptAES(input, key, transformation, algorithm);

        System.out.println("加密后的密文是： " + encode);

        String result = decryptAES(encode, key, transformation, algorithm);

        System.out.println("解密后： "+result);
    }

    /**
     * 加密方法
     * @param input
     * @param key
     * @param transformation
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static String encryptAES(String input, String key, String transformation, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        //创建加密规则    1.第一个参数表示key的字节 2.第二个参数表示加密的类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        //进行加密初始化 1.第一个参数表示模式 分加密模式和解密模式 2. 第二个参数表示加密规则
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
        //调用加密方法  参数传原文的字节数组
        byte[] bytes = cipher.doFinal(input.getBytes());

        /*for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        //如果直接打印密文会出现乱码，因为密文中的bytes数组里面，在编码表上存在找不到的字符
        System.out.println(new String(bytes));*/

        //创建base64对象
        return Base64.encode(bytes);
    }


    /**
     * 解密
     * @param encryptDES
     * @param key
     * @param transformation
     * @param algorithm
     * @return
     * @throws Exception
     */
    private static String decryptAES(String encryptDES, String key, String transformation, String algorithm) throws Exception{
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),algorithm);
        // 创建iv向量
//        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        //Cipher.DECRYPT_MODE:表示解密
        // 解密规则
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        //解密 传入密文
        byte[] bytes = cipher.doFinal(Base64.decode(encryptDES));

        return new String(bytes);
    }
}
