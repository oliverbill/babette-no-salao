package br.com.oliversys.microservices.babettenosalao.userinfo.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="USUARIOS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario implements UserDetails{
	private static final long serialVersionUID = -5945305443634905264L;
		
	@Column(name="ROLE")
	private String role;
	
	@Id // por causa da autenticacao no spring
	private String username;
			
	@Column(name="CLIENT_ID")
	private String clientId;
	
//	@Id	
//	@Column(name="USUARIO_ID")
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_DE_CRIACAO")
	private Date dataDeCriacao;
	
	@Embedded
	private Endereco endereco;
	
	private String nome;
	
	@Column(name = "URL_FOTO")
	private String urlFoto;
	
	@Column(name = "DATA_NASCIMENTO")
	private String dataNascimento;
	         
    private String email;
   
    private Character sexo;
    
	@Column(name = "FACEBOOK_ID")
    private String facebookId;
	
	public Usuario(){}
	
	public Usuario(String username,String p,String cId,EnumRoles r){
		this.username = username;
		this.password = p;
		this.authorities = new HashSet(AuthorityUtils.createAuthorityList(r.toString()));
		this.role = r.toString();
		this.clientId = cId;
	}
	
	public Usuario(Endereco endereco, String nome, String dataNascimento, String email, Character sexo,String username,String senha,String cId,String r) {		
		this.endereco = endereco;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.sexo = sexo;
		this.password = senha;
		this.username = username;
		this.role = r.toString();
		this.authorities = new HashSet(AuthorityUtils.createAuthorityList(r.toString()));
		this.clientId = cId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeUsuario) {
		this.nome = nomeUsuario;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public String getRoles() {
		return role;
	}

	public void setRoles(String r) {
		this.role = r;
		this.authorities = new HashSet(AuthorityUtils.createAuthorityList(r.toString()));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

/*********************** atributos/metodos exigidos pela interface ********/
	@Transient
	private LocalDateTime expires;

	@NotNull
	private boolean accountExpired;

	@NotNull
	private boolean accountLocked;

	@NotNull
	private boolean credentialsExpired;

	@NotNull
	private boolean accountEnabled = true;
	
	@NotNull
	private String password;
	
	private String newPassword;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<GrantedAuthority> authorities;

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNewPassword() {
		return newPassword;
	}

	@JsonProperty
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void grantRole(GrantedAuthority role) {
		if (authorities == null) {
			authorities = new HashSet<GrantedAuthority>();
		}
		authorities.add(role);
	}

	public void revokeRole(GrantedAuthority role) {
		if (authorities != null) {
			authorities.remove(role);
		}
	}

	public boolean hasRole(GrantedAuthority role) {
		return authorities.contains(role);
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return accountEnabled;
	}

	public LocalDateTime getExpires() {
		return expires;
	}

	public void setExpires(LocalDateTime expires) {
		this.expires = expires;
	}
	
	@Override
	public String toString() {
		return "Usuario [roles=" + role + ", username=" + username + ", clientId=" + clientId + ", dataDeCriacao="
				+ dataDeCriacao + ", endereco=" + endereco + ", nome=" + nome + ", urlFoto=" + urlFoto + ", dataNascimento="
				+ dataNascimento + ", email=" + email + ", sexo=" + sexo + ", facebookId=" + facebookId + ", expires="
				+ expires + ", accountExpired=" + accountExpired + ", accountLocked=" + accountLocked
				+ ", credentialsExpired=" + credentialsExpired + ", accountEnabled=" + accountEnabled + ", password="
				+ password + ", newPassword=" + newPassword + ", authorities=" + authorities + "]";
	}	
}