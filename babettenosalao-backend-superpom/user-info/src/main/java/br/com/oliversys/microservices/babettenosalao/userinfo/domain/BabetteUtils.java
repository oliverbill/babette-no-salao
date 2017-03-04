package br.com.oliversys.microservices.babettenosalao.userinfo.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class BabetteUtils {

	public static Collection<String> authToStringList(Collection<GrantedAuthority> a){
		List<String> lista = new ArrayList<>();
		for (GrantedAuthority grantedAuthority : a) {
			lista.add(grantedAuthority.getAuthority());
		}
		return lista;
	}
	
	public static List<String> enumToStringList(EnumRoles... r){
		List<String> lista = new ArrayList<>();
		for (EnumRoles e : r) {
			lista.add(e.toString());
		}
		return lista;
	}
	
	public static Collection<GrantedAuthority> enumToAuth(EnumRoles... e){
		Set<GrantedAuthority> g = new HashSet<>();
		for (EnumRoles r : e) {
			g.add(new SimpleGrantedAuthority(r.toString()));
		}
		return g;
	}
	
	public static String getChavePrivadaFromAppPropFile(){
		try {
			Resource resource = new ClassPathResource("/application.properties");
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			return props.getProperty("token.secret");
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;
	}
		
	//	
	// NAO PRECISA. ATUALMENTE, UTILIZANDO API DE CRIPTO DO SPRING
	//	
//		public static byte[] criptografar(String msg){
//			try {			
//				// gera a chave privada (simetrica)
//				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//				keyGen.init(128);
//				SecretKey secretKey = keyGen.generateKey();	
//				byte[] rawkey = secretKey.getEncoded();
//				SecretKeySpec sks = new SecretKeySpec(rawkey, "AES");
//				
//				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//				cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());		
//				byte[] ciphertext = cipher.doFinal(msg.getBytes());
//				
//				return ciphertext;
//			} 
//			catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e){
//				e.printStackTrace();
//			}
//			return null;
//		}
	//	
//		public static String descriptografar(String msg,byte[] key){
//			SecretKeySpec sks = new SecretKeySpec(key, "AES");
//			
//			Cipher cipher;
//			try {
//				cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//				cipher.init(Cipher.DECRYPT_MODE, sks);
//				return Arrays.toString(cipher.doFinal(msg.getBytes()));
//			} 
//			catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {		
//				e.printStackTrace();
//			}
//			return null;
//		}
}

