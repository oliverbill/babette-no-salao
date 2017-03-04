package br.com.oliversys.microservices.babettenosalao.userinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.oliversys.microservices.babettenosalao.userinfo.domain.Usuario;



public interface IUsuarioRepository extends JpaRepository<Usuario, String>{
	Usuario findByUsername(String u);
	Usuario findByClientId(String id);
}
