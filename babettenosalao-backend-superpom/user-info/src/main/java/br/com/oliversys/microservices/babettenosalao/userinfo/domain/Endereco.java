 package br.com.oliversys.microservices.babettenosalao.userinfo.domain;
 
 import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

 @Embeddable
 @JsonIgnoreProperties(ignoreUnknown = true)
 public class Endereco implements Serializable
 {
   private static final long serialVersionUID = -550443407755905508L;
   
   static Logger log = Logger.getLogger(Endereco.class.getName());
   
   @Column(name="END_CEP")
   private String CEP;
   
   @Enumerated(EnumType.STRING)
   @Column(name="END_LOGRADOURO")
   private EnumLogradouro logradouro;
   
   @Column(name="END_NOME_RUA")
   private String nomeRua;
   
   @Column(name="END_NUM_RUA")
   private Integer numero;
   
   @Column(name="END_COMPLEMENTO")
   private String complemento;
   
   @Column(name="END_BAIRRO")
   private String bairro;
   
   @Column(name="END_CIDADE")
   private String cidade;
   
   @Column(name="END_ESTADO")
   private String estado;

   public Endereco() {}
   
   public Endereco(@NotEmpty String end)
   {
	   // Rua Imbiras,305,ap 24A,Vila Mazzei,Sao Paulo,SP
	   String[] endString = end.split(",");
	   if (endString.length == 7){		 
		   this.logradouro = EnumLogradouro.aproximado(endString[0]);
		   this.nomeRua = endString[0];
		   this.numero = Integer.parseInt(endString[1]);
		   this.complemento = endString[2];
		   this.bairro = endString[3];
		   this.cidade = endString[4];
		   this.estado = endString[5];
		   this.CEP = endString[6];
	   }
   }
   
   public Endereco(String CEP, EnumLogradouro log, String rua, Integer num, 
		   String complemento, String bairro, String cidade, String estado)
   {
     this.CEP = CEP;this.logradouro = log;this.nomeRua = rua;this.numero = num;this.complemento = complemento;
     this.bairro = bairro;this.cidade = cidade;this.estado = estado;
   }
   
   public EnumLogradouro getLogradouro() {
     return this.logradouro;
   }
   
   public void setLogradouro(EnumLogradouro logradouro) { this.logradouro = logradouro; }
   
   public String getNomeRua() {
     return this.nomeRua;
   }
   
   public void setNomeRua(String nomeRua) { this.nomeRua = nomeRua; }
   
   public Integer getNumero() {
     return this.numero;
   }
   
   public void setNumero(Integer numero) { this.numero = numero; }
   
   public String getComplemento() {
     return this.complemento;
   }
   
   public void setComplemento(String complemento) { this.complemento = complemento; }
   
   public String getBairro() {
     return this.bairro;
   }
   
   public void setBairro(String bairro) { this.bairro = bairro; }
   
   public String getCidade() {
     return this.cidade;
   }
   
   public void setCidade(String cidade) { this.cidade = cidade; }
   
   public String getEstado() {
     return this.estado;
   }
   
   public void setEstado(String estado) { this.estado = estado; }
   
   public String getCEP() {
     return this.CEP;
   }
   
   public void setCEP(String cEP) { this.CEP = cEP; }
    
   public String toString()
   {
     return "Endereco [CEP=" + this.CEP + ", logradouro=" + this.logradouro + ", nomeRua=" + this.nomeRua + ", numero=" + this.numero + ", complemento=" + this.complemento + ", bairro=" + this.bairro + ", cidade=" + this.cidade + ", estado=" + this.estado + "]";
   }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CEP == null) ? 0 : CEP.hashCode());
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result
				+ ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((nomeRua == null) ? 0 : nomeRua.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (CEP == null) {
			if (other.CEP != null)
				return false;
		} else if (!CEP.equals(other.CEP))
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (logradouro != other.logradouro)
			return false;
		if (nomeRua == null) {
			if (other.nomeRua != null)
				return false;
		} else if (!nomeRua.equals(other.nomeRua))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

 }