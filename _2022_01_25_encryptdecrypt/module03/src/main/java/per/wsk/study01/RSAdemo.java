package per.wsk.study01;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wb_weishaokang
 * @date 2022/2/7 4:16 下午
 * @description
 */
public class RSAdemo {


    public static void main(String[] args) throws Exception {
        // ------------------------------- 生成公钥、私钥 -----------------------------
        //加密算法
        String algorithm = "RSA";
        //创建密钥生成器对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        //生成公钥
        PublicKey publicKey = keyPair.getPublic();
        //获取私钥字节数据
        byte[] privateKeyEncoded = privateKey.getEncoded();
        //获取公钥字节数据
        byte[] publicKeyEncoded = publicKey.getEncoded();
        //对公私钥进行base64编码
        String privateKeyString = Base64.encode(privateKeyEncoded);
        String publicKeyString = Base64.encode(publicKeyEncoded);


        // --------------------- 加密 ----------------------
        String input = "加密原文";
        //创建加密对象
        Cipher cipher = Cipher.getInstance("RSA");
        //对加密进行初始化
        //第一个参数：表示加密还是解密  第二个参数：表示加密用的密钥（用公钥加密就用私钥解密，反之用私钥加密就用公钥解密）
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        //加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        System.out.println(Base64.encode(bytes));

        //------------------- 解密 --------------------
        //初始化
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] bytes1 = cipher.doFinal(bytes);
        System.out.println(new String(bytes1));

    }



}
