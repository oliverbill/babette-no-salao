package br.com.oliversys.microservices.babettenosalao.agendamentos.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.oliversys.microservices.babettenosalao.agendamentos.domain.Agendamento;



public interface IAgendamentoRepository extends JpaRepository<Agendamento, Long>{

	Collection<Agendamento> findByProfissionalNome(String n);
	Collection<Agendamento> findByProfissionalServicosOferecidosNome(String s);
	Collection<Agendamento> findByUsername(String n);
}
