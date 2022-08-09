package per.wsk.study01;



import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author wb_weishaokang
 * @date 2022/1/27 11:23 上午
 * @description
 */
public class DesTest {


    public static void main(String[] args) throws Exception {
        //原文  如果填充模式使用的是NoPadding,即使用的是不填充的模式，那么原文必须是8个字节的整数倍
        String input = "华为";
        //定义key 如果使用des进行加密，那么密钥必须是8个字节
        String key = "12345678";
        //算法
//        String transformation = "DES";

        // ECB 表示加密模式  PKCS5Padding表示填充模式   如果没有写填充模式和加密模式，那么默认就使用DES/ECB/PKCS5Padding
//        String transformation = "DES/ECB/PKCS5Padding";
        String transformation = "DES/CBC/PKCS5Padding";

        //加密类型
        String algorithm = "DES";
        //加密方法
        String encode = encryptDES(input, key, transformation, algorithm);

        System.out.println("加密后的密文是： " + encode);

        String result = decryptDES(encode, key, transformation, algorithm);

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
    private static String encryptDES(String input, String key, String transformation, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        //创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        //创建加密规则    1.第一个参数表示key的字节 2.第二个参数表示加密的类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);

        //CBC这种加密模式，加密和解密时都要创建iv向量
        // 创建iv向量
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());

        //进行加密初始化 1.第一个参数表示模式 分加密模式和解密模式 2. 第二个参数表示加密规则
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,iv);
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
    private static String decryptDES(String encryptDES, String key, String transformation, String algorithm) throws Exception{
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),algorithm);
        //CBC这种加密模式，加密和解密时都要创建iv向量
        // 创建iv向量
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        //Cipher.DECRYPT_MODE:表示解密
        // 解密规则
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,iv);
        //解密 传入密文
        byte[] bytes = cipher.doFinal(Base64.decode(encryptDES));

        return new String(bytes);
    }



}
