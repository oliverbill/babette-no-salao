package br.com.oliversys.microservices.babettenosalao.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.oliversys.microservices.babettenosalao.quiz.domain.ListaPergunta;
import br.com.oliversys.microservices.babettenosalao.quiz.domain.Pergunta;
import br.com.oliversys.microservices.babettenosalao.quiz.repository.IQuizRepository;

@RestController
@RequestMapping("/quiz")
public class QuizEngineController {

	@Autowired
	private IQuizRepository repositorio;
	
	public QuizEngineController() {}
	
	@RequestMapping("/")
	public String home(){
		return "Bem Vindo ao QuizEngine microservice !!";
	}
	
	@RequestMapping(path="/todas",method={RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ListaPergunta todas() {
		ListaPergunta lista= new ListaPergunta(repositorio.findAll());
		return lista;		
	}
	
	@RequestMapping(path="/id/{id}",method={RequestMethod.GET})
	public @ResponseBody Pergunta porId(@PathVariable("id") long id){
		return repositorio.findOne(id);
	}

	@RequestMapping(path="/enunciado",method={RequestMethod.GET})
	public @ResponseBody ListaPergunta porEnunciado(@RequestParam(name="e") String e) {
		return new ListaPergunta(repositorio.findByEnunciado(e));		
	}	
}
