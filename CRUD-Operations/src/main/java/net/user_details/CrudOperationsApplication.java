package net.user_details;

import net.user_details.userrepository.Repository.UserRepository;
import net.user_details.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudOperationsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CrudOperationsApplication.class, args);
	}
    @Autowired
	private UserRepository userrepository;
	@Override
	public void run(String... args) throws Exception {
		User user=new User();
		user.setFirstname("Tanvi");
		user.setLastname("Maheshwari");
		user.setPassword("abcdefgh");
		user.setEmailid("tanvimaheshwari67@gmail.com");
		user.setPhonenumber("1234567890");
		user.setCity("Udaipur");
		userrepository.save(user);



	}
}
