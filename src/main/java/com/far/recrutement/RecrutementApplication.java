
package com.far.recrutement;

import com.far.recrutement.models.Candidat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class RecrutementApplication {

	public static void main(String[] args) {
		ApplicationContext
				context = SpringApplication.run(RecrutementApplication.class, args);
		Environment env = context.getBean(Environment.class);
		Candidat candidat = new Candidat();
		candidat.setNom("nom");
		candidat.setPrenom("prenom");
		if (env.getProperty("spring.jpa.hibernate.ddl-auto").equals("create")) {
			Seed.seed(context);
		}

//        Script script = context.getBean(Script.class);
//        script.inscriptionIncomplet();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
 