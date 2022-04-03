package Pfe.SpringBoot.BackEnd;

import Pfe.SpringBoot.BackEnd.constantes.ERole;
import Pfe.SpringBoot.BackEnd.entities.User;
import Pfe.SpringBoot.BackEnd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

@SpringBootApplication
public class BackEndApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;

	@Value(value ="${root.username}")
	private  String adminUserName;

	@Value(value = "${root.password}")
	private  String adminPassword;

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Collection<User> users = (Collection<User>)userRepository.findAll();
		if (users.isEmpty()) {
			// le compte de l'administrateur est le premier
			User admin = new User();
			admin.setUserName(adminUserName);
			admin.setFirstName("administrator");
			admin.setLastName("root");
			admin.setEmail("admin.nghost@tn.com");
			admin.setPassword(adminPassword);
			admin.setRole(ERole.ROLE_ADMIN);

			userRepository.save(admin);
		}

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
