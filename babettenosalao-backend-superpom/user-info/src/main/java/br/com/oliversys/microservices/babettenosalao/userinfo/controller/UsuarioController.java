package br.com.oliversys.microservices.babettenosalao.userinfo.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.oliversys.microservices.babettenosalao.userinfo.domain.Usuario;
import br.com.oliversys.microservices.babettenosalao.userinfo.repository.IUsuarioRepository;

@RestController
@RequestMapping("/user-info")
public class UsuarioController {
	
	static Logger log = Logger.getLogger(IUsuarioRepository.class.getName());
	
	@Autowired
	private IUsuarioRepository repository;
	
	@RequestMapping("/")
	public String home(){
		return "Bem Vindo ao Babette Usuario Info microservice !!";
	}
		
	@RequestMapping(path="/usuario-logado",method={RequestMethod.GET})
	public @ResponseBody Map<String, Object> user(Principal user) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("name", user.getName());
		map.put("roles", AuthorityUtils.authorityListToSet(((Authentication) user).getAuthorities()));
		return map;
	}
	
	@RequestMapping(path="/todos",method={RequestMethod.GET})
	public @ResponseBody Collection<Usuario> todosUsuarios() {
		return repository.findAll();		
	}
	
	@RequestMapping(path="/username",method={RequestMethod.GET})
	public @ResponseBody Usuario usuarioPorUsername(@PathVariable("username") String u) {		
		return repository.findByUsername(u);		
	}
	
	@RequestMapping(path="/client-id",method={RequestMethod.GET})
	public @ResponseBody Usuario usuarioPorClientId(@PathVariable("id") String id) {		
		return repository.findByClientId(id);		
	}
	
	@RequestMapping(path="/criar",method={RequestMethod.POST})
	public @ResponseBody Usuario criarUsuario(@RequestParam("usuario") Usuario u) {
		return repository.save(u);		
	}
	
	@RequestMapping(path="/remover",method={RequestMethod.DELETE})
	public void removerUsuario(@RequestParam("usuario") Usuario u) {
		repository.delete(u);
	}
}
