package br.com.oliversys.microservices.babettenosalao.agendamentos;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Consumer;
import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.oliversys.microservices.babettenosalao.agendamentos.domain.Agendamento;
import br.com.oliversys.microservices.babettenosalao.agendamentos.repository.IAgendamentoRepository;

@EnableDiscoveryClient
@SpringBootApplication
public class Main{
	
	static Logger log = Logger.getLogger(Main.class.getName());
	
	@Bean
	CommandLineRunner dummyData(IAgendamentoRepository r){
		Date agora = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
						
		return new CommandLineRunner() {			
			@Override
			public void run(String... args) throws Exception {
				Arrays.asList("nilsinho1983,bill19,catarina22")
					.forEach(n -> r.save(new Agendamento(n,agora)));
				
				r.findAll().forEach(new Consumer<Agendamento>() {					
					@Override
					public void accept(Agendamento t) {
						System.out.println(t);
					}
				});
			}
		};
	}
	
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
    }	
}