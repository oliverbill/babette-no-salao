package br.com.oliversys.microservices.babettenosalao.agendamentos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaAgendamentos implements Serializable{
	private static final long serialVersionUID = 4294697911382267357L;

	private List<Agendamento> lista = new ArrayList<>(); 
	
	public ListaAgendamentos() {}
	
	public ListaAgendamentos(List<Agendamento> l) {
		this.lista = l;
	}
	
	public ListaAgendamentos(Iterable<Agendamento> l) {
		this.lista = (List<Agendamento>)l;
	}
	
	public void addAgendamento(Agendamento u){
		this.lista.add(u);
	}
	
	public Agendamento getAgendamentoEm(int pos){
		if (!this.lista.isEmpty() && pos <= this.lista.size()){
			return this.lista.get(pos);
		}else{
			return null;
		}
	}
	
	public List<Agendamento> getAll(){
		return this.lista;
	}
	
	public boolean isEmpty(){
		return this.lista.isEmpty();
	}
}
