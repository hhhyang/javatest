
package com.javatest.encrypt;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptExam {

    public static final Logger LOG = LoggerFactory.getLogger(EncryptExam.class);


    public static void doTest() {
        try {
            String plainText = "123456";

            LOG.error("==========base64Encoder==============");

            BASE64Encoder base64Encoder = new BASE64Encoder();
            String encodeResult = base64Encoder.encode(plainText.getBytes());
            LOG.error("base64Encoder result: {}", encodeResult);

            LOG.error("==========BASE64Decoder==============");
            BASE64Decoder base64Decoder = new BASE64Decoder();
            String decodeResult = new String(base64Decoder.decodeBuffer(encodeResult));
            LOG.error("base64Decoder result: {}", decodeResult);

            LOG.error("==========MessageDigest MD5==============");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update("123456".getBytes());
            md5.update("78910".getBytes());
            // 计算 12345678910 的md5值
            byte[] md5EncodeResult = md5.digest();
            LOG.error("md5Encoder result: {}", base64Encoder.encode(md5EncodeResult));


            LOG.error("==========MessageDigest SHA-1==============");
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            md5.update("123456".getBytes());
            md5.update("78910".getBytes());
            byte[] sha1EncodeResult = sha1.digest();
            LOG.error("sha1Encoder result: {}", base64Encoder.encode(sha1EncodeResult));

            LOG.error("==========HmacMD5==============");
            SecretKey secretKey = new SecretKeySpec("abcdef".getBytes(), "HmacMD5");
            Mac macMd5 = Mac.getInstance(secretKey.getAlgorithm());
            macMd5.init(secretKey);
            macMd5.update("123456".getBytes());
            macMd5.update("78910".getBytes());
            byte[] macMd5EncodeResult = macMd5.doFinal();
            LOG.error("macMd5EncodeResult result: {}", base64Encoder.encode(macMd5EncodeResult));

            LOG.error("==========HmacSHA1==============");
            SecretKey secretKey2 = new SecretKeySpec("abcdef".getBytes(), "HmacSHA1");
            Mac hmacSha1 = Mac.getInstance(secretKey2.getAlgorithm());
            hmacSha1.init(secretKey2);
            hmacSha1.update("123456".getBytes());
            hmacSha1.update("78910".getBytes());
            byte[] macSha1EncodeResult = hmacSha1.doFinal();

            LOG.error("macSha1EncodeResult result: {}", base64Encoder.encode(macSha1EncodeResult));

            LOG.error("==========DES==============");
            byte[] desEncodeResult = desEncrypt("Hello, World!xxxxxx", "abcdefghik");
            LOG.error("desEncodeResult: {}", base64Encoder.encode(desEncodeResult));

            String desDecodeResult = desDecrypt(desEncodeResult, "abcdefghik");
            LOG.error("desDecodeResult: {}", desDecodeResult);


            LOG.error("==========AES==============");
            // 加密
            byte[] encrypt = aesEncrypt("Hello, World!xxxxxx", "abcdefghik");
            System.out.println("加密后的内容：" + new String(encrypt));

            // 解密
            byte[] decrypt = aesDecrypt(encrypt, "abcdefghik");
            System.out.println("解密后的内容：" + new String(decrypt));

            LOG.error("==========AES==============");

            KeyPair keyPair = genRsaKeyPair();
            // 得到私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 得到公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

            byte[] rsaEncodeResult = rsaEncrypt(publicKey, "Hello, World!xxxxxx".getBytes());

            byte[] rsaDecodeResult = rsaDecrypt(privateKey, rsaEncodeResult);

            System.out.println("解密后的内容：" + new String(rsaDecodeResult));

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);

            e.printStackTrace(pw);

            LOG.error(sw.toString());
        }


    }

    public static byte[] desEncrypt(String content, String key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            byte[] result = cipher.doFinal(content.getBytes());
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String desDecrypt(byte[] content, String key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            byte[] result = cipher.doFinal(content);
            return new String(result);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] aesEncrypt(String content, String password) {

        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者

            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kgen.init(128, random);// 利用用户密码作为随机数初始化出
            // 128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            // null。

            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance("AES");// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] aesDecrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            byte[] result = cipher.doFinal(content);
            return result; // 明文

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] rsaEncrypt(RSAPublicKey publicKey, byte[] plainTextData)
            throws Exception {
        if (publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static byte[] rsaDecrypt(RSAPrivateKey privateKey, byte[] cipherData)
            throws Exception {
        if (privateKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(cipherData);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static KeyPair genRsaKeyPair() {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();

        return keyPair;

        // 得到私钥
        //RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        //RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

    }


    public static void main(String []args) {


    }

}
