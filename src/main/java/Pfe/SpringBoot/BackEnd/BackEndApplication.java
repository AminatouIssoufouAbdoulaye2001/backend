package Pfe.SpringBoot.BackEnd;

import Pfe.SpringBoot.BackEnd.constantes.ERole;
import Pfe.SpringBoot.BackEnd.entities.User;
import Pfe.SpringBoot.BackEnd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;

@SpringBootApplication
public class BackEndApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Collection<User> users = (Collection<User>)userRepository.findAll();
		if (users.isEmpty()) {
			// le compte de l'administrateur est le premier
			User admin = new User();
			admin.setFirstName("administrator");
			admin.setLastName("root");
			admin.setEmail("admin.nghost@tn.com");
			admin.setPassword("adminù@!785nghost");
			admin.setRole(ERole.ROLE_ADMIN);

			userRepository.save(admin);
		}

	}
}
