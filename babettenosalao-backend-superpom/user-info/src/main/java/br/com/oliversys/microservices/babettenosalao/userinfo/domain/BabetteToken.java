package br.com.oliversys.microservices.babettenosalao.userinfo.domain;
//package br.com.oliversys.babette.user;
//
//import java.io.Serializable;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//import javax.xml.bind.DatatypeConverter;
//
//import org.apache.log4j.Logger;
//
//
//@Entity
//@Table(name="TOKENS")
//public class BabetteToken implements Serializable{
//	private static final long serialVersionUID = -3161508788208906211L;
//
//	@Transient
//	private static final String HMAC_ALGO = "HmacSHA256";
//
//	@Transient
//	private static final String SEPARATOR = ".";
//
//	@Transient
//	public static final String SEPARATOR_SPLITTER = "\\.";
//
//	@Transient
//	static Logger log = Logger.getLogger(BabetteToken.class.getName());
//
//	@Transient
//	private Mac hmac;
//
//	@ManyToOne
//	private Usuario usuario;
//	
//	@Id
//	private String token;
//	
//	private String username;
//
//	private byte[] chavePrivada;
//	
//	private byte[] assinaturaHash;
//
//	private String dataHoraExpiracao;
//
//	private String dataHoraGeracao;
//	
//	@Transient
//	private boolean valido = false;
//
//	public BabetteToken(){}
//	
//	public BabetteToken(byte[] secretKey, String username) {
//		try {
//			hmac = Mac.getInstance(HMAC_ALGO);
//			hmac.init(new SecretKeySpec(secretKey, HMAC_ALGO));
//			this.chavePrivada = secretKey;
//			this.token = createTokenForUser(username);			
//		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
//			throw new IllegalStateException("failed to initialize HMAC: " + e.getMessage(), e);
//		}
//	}
//
//	public BabetteToken(String tokenGerado, byte[] secretKey) {
//		try {
//			hmac = Mac.getInstance(HMAC_ALGO);
//			hmac.init(new SecretKeySpec(secretKey, HMAC_ALGO));
//		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
//			throw new IllegalStateException("Falha na inicializacao do HMAC: " + e.getMessage(), e);
//		}
//
//		this.token = tokenGerado;
//		// gera um novo hash e compara o hash do token, utilizando a mesma chave privada
//		if (validarToken()) {			
//			this.username = new String(fromBase64StringToByte(token.split(SEPARATOR_SPLITTER)[0]));
//			this.assinaturaHash = fromBase64StringToByte(token.split(SEPARATOR_SPLITTER)[1]);
//			this.dataHoraExpiracao = new String(fromBase64StringToByte(token.split(SEPARATOR_SPLITTER)[2]));
//		}
//	}
//
//	/**
//	 * Valida o token, criando um novo hash(com o mesmo usuario e PRIVATEKEY) e
//	 * comparando com o hash que veio no token. Tambem verifica se o token nao
//	 * esta expirado.
//	 * 
//	 * @param token
//	 * @return username extraído do token
//	 */
//	private boolean validarToken() {
//		if (token == null)
//			return false;
//			
//		final String[] parts = token.split(SEPARATOR_SPLITTER);
//
//		if (isTokenCompleto(parts)) {
//			try {
//				final byte[] usernameInBytes = fromBase64StringToByte(parts[0]);
//				final byte[] hash = fromBase64StringToByte(parts[1]);
//
//				if (!isTokenExpirado(parts[2]) && isHashValido(usernameInBytes, hash)) {
//					this.valido = true;
//				} else {
//					throw new TokenValidationException("Assinatura do Token Invalida");
//				}
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			}
//		}
//		return this.valido;
//	}
//
//	private boolean isTokenCompleto(String[] parts) {
//		boolean isUsuarioNaoVazio = parts[0].length() > 0;
//		boolean isHashNaoVazio = parts[1].length() > 0;
//		boolean isDataExpiracaoNaoVazia = parts[2].length() > 0;
//		boolean hasToken4Partes = parts.length == 3;
//
//		if (hasToken4Partes && isUsuarioNaoVazio && isHashNaoVazio && isDataExpiracaoNaoVazia)
//			return true;
//		return false;
//	}
//
//	/*
//	 * Cria um novo hash e compara com o hash que veio do token
//	 */
//	private boolean isHashValido(byte[] userBytes, byte[] hash) {
//		log.debug("HASH ANTERIOR: " + Arrays.toString(hash));
//		byte[] hashNovo = createHmac(userBytes);
//		log.debug("HASH NOVO: " + Arrays.toString(hashNovo));
//		return Arrays.equals(hashNovo, hash);
//	}
//
//	private boolean isTokenExpirado(String dataFromToken) {
//		boolean resposta = true;
//		final byte[] dataHoraExpiracaoInBytes = fromBase64StringToByte(dataFromToken);
//
//		String dataHoraExpiracaoInString = new String(dataHoraExpiracaoInBytes);
//		LocalDateTime dataHoraExpiracao = LocalDateTime.parse(dataHoraExpiracaoInString);
//
//		// se a data-hora de expiracao do token for antes da data-hora corrente
//		if (LocalDateTime.now().isBefore(dataHoraExpiracao)) {
//			resposta = false;
//		}
//		return resposta;
//	}
//
//	private String byteToBase64String(byte[] content) {
//		return DatatypeConverter.printBase64Binary(content);
//	}
//
//	private byte[] fromBase64StringToByte(String content) {
//		return DatatypeConverter.parseBase64Binary(content);
//	}
//
//	private String createTokenForUser(String username) {
//		byte[] usernameInBytes = username.getBytes();
//		// token expira em 30 minutos
//		String agoraMais30minutos = LocalDateTime.now().plusMinutes(30).toString();
//
//		byte[] hash = createHmac(usernameInBytes);
//
//		final StringBuilder sb = new StringBuilder(170);
//		sb.append(byteToBase64String(usernameInBytes));
//		sb.append(SEPARATOR);
//		sb.append(byteToBase64String(hash));
//		sb.append(SEPARATOR);
//		sb.append(byteToBase64String(agoraMais30minutos.getBytes()));
//
//		this.username = username;
//		this.assinaturaHash = hash;
//		this.dataHoraExpiracao = agoraMais30minutos;
//		this.dataHoraGeracao = LocalDateTime.now().toString();
//		
//		this.valido = true;
//
//		return sb.toString();
//	}
//
//	// synchronized to guard internal hmac object
//	private synchronized byte[] createHmac(byte[] content) {
//		return hmac.doFinal(content);
//	}
//
//	public String getUsername() {	
//		return username;
//	}
//
//	public byte[] getAssinaturaHash() {
//		return assinaturaHash;		
//	}
//
//	public String getDataHoraExpiracao() {
//		return dataHoraExpiracao;
//	}
//
//	public String getDataHoraGeracao() {
//		return dataHoraGeracao;
//	}
//
//	public String getToken() {
//		return token;
//	}
//
//	public boolean isValido() {
//		return valido;
//	}
//
//	public byte[] getChavePrivada() {
//		return chavePrivada;
//	}
//
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//
//	@Override
//	public String toString() {
//		return "BabetteToken [username=" + username + ", chavePrivada=" + Arrays.toString(chavePrivada)
//				+ ", assinaturaHash=" + Arrays.toString(assinaturaHash) + ", dataHoraExpiracao=" + dataHoraExpiracao
//				+ ", dataHoraGeracao=" + dataHoraGeracao + ", token=" + token + ", valido=" + valido + "]";
//	}
//
//}