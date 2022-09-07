package net.user_details;


import net.user_details.repository.ProductRepository;
import net.user_details.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudOperationsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CrudOperationsApplication.class, args);
	}
    @Autowired
	private UserRepository userrepository;

	@Autowired
	private ProductRepository productRepository;
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		// Starter function
	}



}
