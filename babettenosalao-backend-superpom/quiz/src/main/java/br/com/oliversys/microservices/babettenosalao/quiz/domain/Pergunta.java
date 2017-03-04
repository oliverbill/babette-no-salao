package br.com.oliversys.microservices.babettenosalao.quiz.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="PERGUNTAS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pergunta implements Serializable{
	private static final long serialVersionUID = -5671464962720065756L;

	@Id
	@Column(name="PERGUNTA_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String enunciado;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private List<String> respostas;
	
	public Pergunta() {}
	
	public Pergunta(String e,List<String> resp) {
		this.enunciado = e;
		this.respostas = resp;				
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public List<String> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<String> respostas) {
		this.respostas = respostas;
	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", enunciado=" + enunciado + ", respostas=" + respostas + "]";
	}
	
}
