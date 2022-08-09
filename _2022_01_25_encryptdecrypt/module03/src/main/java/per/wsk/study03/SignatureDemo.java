package per.wsk.study03;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import per.wsk.study02.RSADemo2;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * @Author wb_weishaokang
 * @date 2022/2/8 3:44 下午
 * @description
 */
public class SignatureDemo {

    public static void main(String[] args) throws Exception {
        String input = "华为";

        PublicKey publicKey = RSADemo2.getPublicKey("a.pub","RSA");
        PrivateKey privateKey = RSADemo2.getPrivateKey("a.pri","RSA");

        String signatureData = getSignature(input, "sha256withrsa", privateKey);

        System.out.println(signatureData);

        boolean result = verifySignature(input, "sha256withrsa", publicKey, signatureData);

        System.out.println(result);
    }


    /**
     * 生成数字签名
     * @param input  表示原文
     * @param algorithm  表示算法
     * @param privateKey  私钥key
     * @return
     * @throws Exception
     */
    private static String getSignature(String input,String algorithm,PrivateKey privateKey) throws Exception {
        //获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        //初始化签名
        signature.initSign(privateKey);
        //传入原文
        signature.update(input.getBytes());
        //开始签名
        byte[] sign = signature.sign();
        //使用base64进行编码
        return Base64.encode(sign);

    }


    /**
     * 验证数字签名
     * @param input  表示原文
     * @param algorithm 表示算法
     * @param publicKey 表示公钥
     * @param signaturedData 表示要验证的签名
     * @return
     * @throws Exception
     */
    private static boolean verifySignature(String input,String algorithm,PublicKey publicKey,String signaturedData) throws Exception{
        //获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        //初始化校验
        signature.initVerify(publicKey);
        //传入原文
        signature.update(input.getBytes());
        //校验数据
        return signature.verify(Base64.decode(signaturedData));
    }



}
