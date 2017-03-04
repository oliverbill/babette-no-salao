package br.com.oliversys.microservices.babettenosalao.agendamentos.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SERVICOS")
public class Servico implements Serializable{

	private static final long serialVersionUID = 2953820420650238150L;

	@Id	
	@Column(name="SERVICO_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="DURACAO_MEDIA_EM_MINUTOS")
	private Byte duracaoMediaEmMinutos;
		
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PROFISSIONAL") // BI-DIRECIONAL
	private Profissional profissional;
	
	@Column(name="PRECO")
	private BigDecimal preco;
		
	public Servico() {
	}

	public Servico(String nome, Byte duracaoMediaEmMinutos) {
		this.nome = nome;
		this.duracaoMediaEmMinutos = duracaoMediaEmMinutos;
	}
	
	public Servico(EnumServico nome, Byte duracaoMediaEmMinutos) {
		this.nome = nome.toString();
		this.duracaoMediaEmMinutos = duracaoMediaEmMinutos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Byte getDuracaoMediaEmMinutos() {
		return duracaoMediaEmMinutos;
	}

	public void setDuracaoMediaEmMinutos(Byte duracaoMediaEmMinutos) {
		this.duracaoMediaEmMinutos = duracaoMediaEmMinutos;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	@Override
	public String toString() {
		return "Servico [id=" + id + ", nome=" + nome + ", duracaoMediaEmMinutos=" + duracaoMediaEmMinutos
				+ ", profissional=" + profissional + ", preco=" + preco + "]";
	}
}
