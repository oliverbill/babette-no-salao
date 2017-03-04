package br.com.oliversys.microservices.babettenosalao.quiz.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaPergunta implements Serializable{
	private static final long serialVersionUID = 650188324687592394L;
	
	private List<Pergunta> lista = new ArrayList<>();
	
	public ListaPergunta() {}
	
	public ListaPergunta(List<Pergunta> l) {
		this.lista = l;
	}
	
	public ListaPergunta(Iterable<Pergunta> p){
		this.lista = (List<Pergunta>) p;
	}

	public void addPergunta(Pergunta p){
		this.lista.add(p);
	}

	public boolean containsEnunciado(String enunciado){
		for (Pergunta p : this.lista) {
			if (p.getEnunciado().contains(enunciado))
				return true;
		}
		return false;
	}
	
	public List<Pergunta> getLista() {
		return lista;
	}

	public Pergunta getUsuarioEm(int pos){
		if (!this.lista.isEmpty() && pos <= this.lista.size()){
			return this.lista.get(pos);
		}else{
			return null;
		}
	}
	
	public List<Pergunta> getAll(){
		return lista;
	}
	
	public boolean isEmpty(){
		return lista.isEmpty();
	}
	
	public int getSize(){
		return this.lista.size();
	}
	
	@Override
	public String toString() {
		return "ListaPergunta [lista=" + lista + "]";
	}
}
