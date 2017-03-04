package br.com.oliversys.microservices.babettenosalao.agendamentos.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.oliversys.microservices.babettenosalao.agendamentos.domain.Agendamento;
import br.com.oliversys.microservices.babettenosalao.agendamentos.domain.EnumServico;
import br.com.oliversys.microservices.babettenosalao.agendamentos.domain.ListaAgendamentos;
import br.com.oliversys.microservices.babettenosalao.agendamentos.repository.IAgendamentoRepository;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentosController {
	
	static Logger log = Logger.getLogger(AgendamentosController.class.getName());
	
	@Autowired
	private IAgendamentoRepository repository;
	
	@RequestMapping("/")
	public String home(){
		return "Bem Vindo ao Babette Agendamento microservice !!";
	}
	
	@RequestMapping(path="/todos",method={RequestMethod.GET})
	public @ResponseBody Collection<Agendamento> todosAgendamentos() {
		return repository.findAll();		
	}
	
	@RequestMapping(path="/profissional/nome",method={RequestMethod.GET})
	public @ResponseBody Collection<Agendamento> agendamentosPorProfissional(@PathVariable("nome") String n) {		
		return repository.findByProfissionalNome(n);		
	}
	
	@RequestMapping(path="/usuario/nome",method={RequestMethod.GET})
	public @ResponseBody Collection<Agendamento> agendamentosPorUsuario(@PathVariable("nome") String n) {
		return repository.findByUsername(n);		
	}
	
	@RequestMapping(path="/servico/nome",method={RequestMethod.GET})
	public @ResponseBody Collection<Agendamento> agendamentosPorServico(@RequestParam("nome_svc") EnumServico n) {
		return (List<Agendamento>)repository.findByProfissionalServicosOferecidosNome(n.toString());		
	}
	
	@RequestMapping(path="/criar",method={RequestMethod.POST})
	public @ResponseBody Agendamento criarAgendamento(@RequestParam("agendamento") Agendamento a) {
		return repository.save(a);		
	}
	
	@RequestMapping(path="/criar-lista",method={RequestMethod.POST})
	public @ResponseBody ListaAgendamentos criarListaAgendamento(@RequestParam("agendamentos") List<Agendamento> lista) {
		List<Agendamento> listaSalva = new ArrayList<>();
		for(Agendamento a:lista){
			listaSalva.add(repository.save(a));			
		}
		ListaAgendamentos l = new ListaAgendamentos(listaSalva);
		return l;		
	}
}
