package br.com.oliversys.microservices.babettenosalao.agendamentos.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="AGENDAMENTOS")
public class Agendamento implements Serializable{
	private static final long serialVersionUID = -3161822376769615137L;

	@Id
	@Column(name="AGENDAMENTO_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="AGENDAMENTO_DATA")
	private Date data;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@Column(name="HORARIOS_DISPONIVEIS")
	private List<String> horariosDisponiveis;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Profissional profissional;
	
	// Servico(s) eh(sao) selecionado(s) na classe Profissional
	
	public Agendamento() {}
	
	public Agendamento(String u,Date data) {	
		this.username = u;
		this.data = data;		
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Long getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<String> getHorariosDisponiveis() {
		return horariosDisponiveis;
	}

	public void setHorariosDisponiveis(List<String> horariosDisponiveis) {
		this.horariosDisponiveis = horariosDisponiveis;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Agendamento [id=" + id + ", username=" + username + ", data=" + data + ", horariosDisponiveis="
				+ horariosDisponiveis + ", profissional=" + profissional + "]";
	}
}
