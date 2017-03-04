package br.com.oliversys.microservices.babettenosalao.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.oliversys.microservices.babettenosalao.quiz.domain.Pergunta;

public interface IQuizRepository extends JpaRepository<Pergunta, Long>{

	@Query("from Pergunta p where p.enunciado like %:enunciado%")	
	List<Pergunta> findByEnunciado(@Param("enunciado") String enunciado);
}
