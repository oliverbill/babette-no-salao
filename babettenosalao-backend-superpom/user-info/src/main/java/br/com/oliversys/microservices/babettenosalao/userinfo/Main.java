package br.com.oliversys.microservices.babettenosalao.userinfo;

import java.util.function.Consumer;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.oliversys.microservices.babettenosalao.userinfo.domain.EnumRoles;
import br.com.oliversys.microservices.babettenosalao.userinfo.domain.Usuario;
import br.com.oliversys.microservices.babettenosalao.userinfo.repository.IUsuarioRepository;

@EnableDiscoveryClient
@SpringBootApplication
public class Main {

	@Autowired
	IUsuarioRepository repository;
	
	static Logger log = Logger.getLogger(Main.class.getName());

	@Bean
	CommandLineRunner dummyData(IUsuarioRepository r){		
						
		return new CommandLineRunner() {			
			@Override
			public void run(String... args) throws Exception {
				repository.save(new Usuario("nilsinho1983","72214ssd6","windowsPhoneDaCatarina",EnumRoles.USUARIO));
				repository.save(new Usuario("bill19","04338wes2","iphoneDoBill",EnumRoles.SUPER_USUARIO));
				repository.save(new Usuario("catarina22","55648aasd1","androidDoNilsinho",EnumRoles.ADMIN));
				
				r.findAll().forEach(new Consumer<Usuario>() {					
					@Override
					public void accept(Usuario t) {
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