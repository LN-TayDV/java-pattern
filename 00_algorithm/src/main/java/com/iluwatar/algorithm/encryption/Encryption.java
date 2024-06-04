package com.iluwatar.algorithm.encryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class Encryption {

    public static void main(String[] args) throws Exception {
        // Dữ liệu cần mã hóa
        String data = "This is a secret message";

        // Mã hóa và giải mã bằng AES
        String aesEncryptedData = encryptAES(data, "AES_Key");
        System.out.println("AES Encrypted Data: " + aesEncryptedData);
        String aesDecryptedData = decryptAES(aesEncryptedData, "AES_Key");
        System.out.println("AES Decrypted Data: " + aesDecryptedData);

        // Mã hóa và giải mã bằng RSA
        KeyPair rsaKeyPair = generateRSAKeyPair();
        String rsaEncryptedData = encryptRSA(data, rsaKeyPair.getPublic());
        System.out.println("RSA Encrypted Data: " + rsaEncryptedData);
        String rsaDecryptedData = decryptRSA(rsaEncryptedData, rsaKeyPair.getPrivate());
        System.out.println("RSA Decrypted Data: " + rsaDecryptedData);

        // Mã hóa và giải mã bằng DES
        String desEncryptedData = encryptDES(data, "DES_Key");
        System.out.println("DES Encrypted Data: " + desEncryptedData);
        String desDecryptedData = decryptDES(desEncryptedData, "DES_Key");
        System.out.println("DES Decrypted Data: " + desDecryptedData);
    }

    // Mã hóa AES
    public static String encryptAES(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Giải mã AES
    public static String decryptAES(String encryptedData, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

    // Tạo cặp khóa RSA
    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    // Mã hóa RSA
    public static String encryptRSA(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Giải mã RSA
    public static String decryptRSA(String encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

    // Mã hóa DES
    public static String encryptDES(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Giải mã DES
    public static String decryptDES(String encryptedData, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }
}

