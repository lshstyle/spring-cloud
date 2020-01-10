package com.example.gateway.util.jwt;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import sun.misc.BASE64Encoder;


public class SecretUtil {

	public static final String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    private static Map<String,Object> keyMap = initKey();
    
	private static Map<String,Object> initKey(){
			Map<String,Object> keyMap = new HashMap<String, Object>(2);
			KeyPairGenerator keyPairGenerator;
			try {
				keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
				keyPairGenerator.initialize(1024);
				KeyPair keyPair = keyPairGenerator.generateKeyPair();
				RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
				RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
				keyMap.put(PUBLIC_KEY, publicKey);
				keyMap.put(PRIVATE_KEY, privateKey);
				storeKey(encryptBASE64(publicKey.getEncoded()), PUBLIC_KEY);
				storeKey(encryptBASE64(privateKey.getEncoded()), PRIVATE_KEY);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return keyMap;
	}
	
	private static String encryptBASE64(byte[] bytes) throws Exception{
		return new BASE64Encoder().encodeBuffer(bytes);
	}
	
	private static void storeKey(String content, String fileName) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName + ".txt");
			
			fos.write(content.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getPublicKeyStr() throws Exception {
		Key key = (Key)keyMap.get(PUBLIC_KEY);
		return encryptBASE64(key.getEncoded());
	}
	
	public static String getPrivateKeyStr() throws Exception {
		Key key = (Key)keyMap.get(PRIVATE_KEY);
		return encryptBASE64(key.getEncoded());
	}
	
	public static RSAPublicKey getPublicKey() throws Exception{
		return (RSAPublicKey)keyMap.get(PUBLIC_KEY);
	}
	
	public static RSAPrivateKey getPrivateKey() throws Exception {
		return (RSAPrivateKey)keyMap.get(PRIVATE_KEY);
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		initKey();
	}
	
	

}
