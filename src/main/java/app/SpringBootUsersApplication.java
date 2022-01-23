package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import app.model.Role;
import app.model.User;
import app.repository.RoleRepository;
import app.repository.UserRepository;

import java.util.HashSet;

@SpringBootApplication
public class SpringBootUsersApplication implements CommandLineRunner {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SpringBootUsersApplication(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUsersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role admin = new Role("ROLE_ADMIN");
		Role user = new Role("ROLE_USER");
		roleRepository.save(admin);
		roleRepository.save(user);

		userRepository.save(new User("Иван", "Иванов", 49, "admin@mail.ru",
				passwordEncoder.encode("admin"),
				new HashSet<>() {{
					add(admin);
					add(user);
				}}));
		userRepository.save(new User("Василий", "Васильев", 36, "user@mail.ru",
				passwordEncoder.encode("user"),
				new HashSet<>() {{
					add(user);
				}}));
	}
}
