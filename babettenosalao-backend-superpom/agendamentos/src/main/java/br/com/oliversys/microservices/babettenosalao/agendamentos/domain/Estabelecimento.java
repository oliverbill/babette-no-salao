package br.com.oliversys.microservices.babettenosalao.agendamentos.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ESTABELECIMENTOS")
public class Estabelecimento implements Serializable{
	
	private static final long serialVersionUID = 2785986018808117662L;
	
	@Id
	@Column(name="ESTABELECIMENTO_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="NOME")
	private String nome;
	
	@Embedded
	private Endereco endereco;
		
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="COMENTARIO_ID")
	private Set<Comentario> comentarios = new HashSet<Comentario>();
	
	@Column(name="AVALIACAO")
	private Float avaliacao = Float.valueOf(1.0F);

// distanciaEmKM eh calculada em tempo real e nao armazenada	
	
	@Column(name="DIA_HORA_FUNCIONAMENTO")
	private String diaHoraFuncionamento;
		
	@Column(name="LOGO_URL")
	private String logoURL;
		
	@ManyToMany(mappedBy="estabelecimentosEmQueTrabalha",cascade=CascadeType.ALL)
	private List<Profissional> funcionarios;
	
	public Estabelecimento() {}
	
	public Estabelecimento(String nome, Endereco endereco, String diaHoraFuncionamento, String logoURL) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.diaHoraFuncionamento = diaHoraFuncionamento;
		this.logoURL = logoURL;
	}

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Float getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Float avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getDiaHoraFuncionamento() {
		return diaHoraFuncionamento;
	}

	public void setDiaHoraFuncionamento(String diaHoraFuncionamento) {
		this.diaHoraFuncionamento = diaHoraFuncionamento;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	public List<Profissional> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Profissional> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	public String toString() {
		return "Estabelecimento [id=" + id + ", nome=" + nome + ", endereco="
				+ endereco + ", comentarios=" + comentarios + ", avaliacao="
				+ avaliacao + ", diaHoraFuncionamento=" + diaHoraFuncionamento
				+ ", logoURL=" + logoURL + ", + ]";
	}
}
