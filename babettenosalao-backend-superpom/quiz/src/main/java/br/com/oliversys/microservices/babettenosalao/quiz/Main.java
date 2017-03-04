package br.com.oliversys.microservices.babettenosalao.quiz;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.oliversys.microservices.babettenosalao.quiz.domain.Pergunta;
import br.com.oliversys.microservices.babettenosalao.quiz.repository.IQuizRepository;

@EnableDiscoveryClient
@SpringBootApplication // short for @Configuration + @EnableAutoConfiguration +
						// @ComponentScan
public class Main {

	@Autowired
	IQuizRepository repositorio;

	@Bean
	CommandLineRunner dummyData(IQuizRepository r) {

		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
								
				Pergunta p = instanciarPergunta("Onde você deseja fazer as unhas ?", "em casa", "perto do trabalho",
						"adoro ir ao salão");
				repositorio.save(p);
				log.info("Pergunta inserida: " + p);

				Pergunta p2 = instanciarPergunta("Como você está hoje ?", "Sofisticada", "Criativa", "Divertida",
						"Baladeira", "Romântica", "Clássica", "Aceito Sugestões");
				repositorio.save(p2);
				log.info("Pergunta inserida: " + p2);

				Pergunta p3 = instanciarPergunta("Você precisa fazer :", "pé", "mão", "pé e mão");
				repositorio.save(p3);
				log.info("Pergunta inserida: " + p3);

				Pergunta p4 = instanciarPergunta("Você prefere...", "mesma profissional", "profisionais diferentes");
				repositorio.save(p4);
				log.info("Pergunta inserida: " + p4);

			}
		};
	}

	static Logger log = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = SpringApplication.run(Main.class, args);
	}
	
	private Pergunta instanciarPergunta(String enunciado, String... respostasArray) {
		List<String> respostas = Arrays.asList(respostasArray);
		Pergunta p = new Pergunta(enunciado, respostas);
		return p;
	}
}